package space.atnibam.steamedu.service;

import cn.dev33.satoken.util.SaResult;
import space.atnibam.common.core.domain.R;

import java.io.IOException;

/**
 * @InterfaceName: AuthService
 * @Description: 用户认证服务
 * @Author: AtnibamAitay
 * @CreateTime: 2024-02-06 14:09
 **/
public interface AuthService {

    /**
     * 发送验证码
     *
     * @param accountNumber 邮箱/手机号
     * @param codeType      验证码类型，1-手机号登录验证码、2-邮箱登录验证码、3-绑定手机号验证码、4-绑定邮箱验证码
     * @return Result 发送结果，成功发送则返回成功信息，否则返回失败原因
     */
    R sendCodeByAccount(String accountNumber, Integer codeType);

    /**
     * 登陆
     *
     * @param accountNumber 邮箱/手机号
     * @param verifyCode    验证码
     * @param loginMethod   登录方式 1-手机号+验证码 2-邮箱+验证码
     * @return 登录结果
     */
    SaResult login(String accountNumber, String verifyCode, Integer loginMethod) throws IOException;
}
