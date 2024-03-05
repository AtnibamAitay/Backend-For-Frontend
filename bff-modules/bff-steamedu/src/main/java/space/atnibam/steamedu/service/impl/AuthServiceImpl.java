package space.atnibam.steamedu.service.impl;

import cn.dev33.satoken.sso.SaSsoProcessor;
import org.springframework.stereotype.Service;
import space.atnibam.api.auth.RemoteAuthService;
import space.atnibam.api.auth.dto.AccountVerificationDTO;
import space.atnibam.api.auth.dto.LoginDTO;
import space.atnibam.api.auth.dto.LoginRequestDTO;
import space.atnibam.common.core.domain.R;
import space.atnibam.steamedu.config.ProjectConfig;
import space.atnibam.steamedu.service.AuthService;

import javax.annotation.Resource;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

import static space.atnibam.steamedu.constant.AuthConstants.EMAIL_TITLE;
import static space.atnibam.steamedu.constant.AuthConstants.TEMPLATE_PATH;

/**
 * @ClassName: AuthServiceImpl
 * @Description: 认证服务实现类
 * @Author: AtnibamAitay
 * @CreateTime: 2024-02-06 14:09
 **/
@Service
public class AuthServiceImpl implements AuthService {
    @Resource
    private RemoteAuthService remoteAuthService;
    @Resource
    private ProjectConfig projectConfig;

    /**
     * 发送验证码
     *
     * @param accountNumber 邮箱/手机号
     * @param codeType      验证码类型，1-手机号登录验证码、2-邮箱登录验证码、3-绑定手机号验证码、4-绑定邮箱验证码
     * @return Result 发送结果，成功发送则返回成功信息，否则返回失败原因
     */
    @Override
    public R sendCodeByAccount(String accountNumber, Integer codeType) {
        // 获取HTML模板内容
        String content = readTemplateContent();

        AccountVerificationDTO accountVerificationDTO = AccountVerificationDTO.builder()
                .accountNumber(accountNumber)
                .codeType(codeType)
                // TODO:邮件标题需要根据验证码类型选用不同的标题
                .title(EMAIL_TITLE)
                .content(content)
                .appId(projectConfig.getAppId())
                .build();

        return remoteAuthService.sendCodeByAccount(accountVerificationDTO);
    }

    /**
     * 从资源文件中读取HTML模板内容
     *
     * @return 模板内容字符串
     */
    private String readTemplateContent() {
        StringBuilder contentBuilder = new StringBuilder();

        try (InputStream is = getClass().getResourceAsStream(TEMPLATE_PATH);
             BufferedReader reader = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8))) {

            String line;
            while ((line = reader.readLine()) != null) {
                contentBuilder.append(line).append(System.lineSeparator());
            }

        } catch (IOException e) {
            // TODO:更换为自定义异常
            throw new RuntimeException("Failed to read loginCode.html template", e);
        }

        return contentBuilder.toString();
    }

    /**
     * 登陆
     *
     * @param accountNumber 邮箱/手机号
     * @param verifyCode    验证码
     * @param loginMethod   登录方式 1-手机号+验证码 2-邮箱+验证码
     * @return 登录结果
     */
    @Override
    public LoginDTO login(String accountNumber, String verifyCode, Integer loginMethod) throws IOException {
        LoginRequestDTO loginRequestDTO = LoginRequestDTO.builder()
                .accountNumber(accountNumber)
                .verifyCode(verifyCode)
                .loginMethod(loginMethod)
                .appId(projectConfig.getAppId())
                .build();
        return remoteAuthService.ssoLogin(loginRequestDTO);
    }

    /**
     * 退出登录
     *
     * @return 退出登录结果
     */
    @Override
    public Object logout() {
        return SaSsoProcessor.instance.ssoSignout();
    }
}
