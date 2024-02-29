package space.atnibam.steamedu.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import space.atnibam.common.core.domain.R;
import space.atnibam.steamedu.model.dto.LoginDTO;
import space.atnibam.steamedu.model.dto.SendCodeDTO;
import space.atnibam.steamedu.service.AuthService;

import javax.annotation.Resource;
import java.io.IOException;

/**
 * @ClassName: AuthController
 * @Description: 认证接口
 * @Author: AtnibamAitay
 * @CreateTime: 2024-02-06 13:17
 **/
@Api(tags = "认证模块")
@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Resource
    private AuthService authService;

    /**
     * 发送验证码
     *
     * @param sendCodeDTO 包含账号和验证码类型的DTO
     * @return Result 发送结果，成功发送则返回成功信息，否则返回失败原因
     */
    @ApiOperation(value = "发送验证码")
    @PostMapping("/verification-codes")
    public R sendCodeByAccount(@RequestBody @Validated SendCodeDTO sendCodeDTO) {
        return authService.sendCodeByAccount(sendCodeDTO.getAccountNumber(), sendCodeDTO.getCodeType());
    }

    /**
     * 登陆接口
     *
     * @param loginDTO 包含账号、验证码和登录方式的DTO
     * @return 认证结果
     */
    @ApiOperation("登陆")
    @PostMapping("/login")
    public R login(@Validated @RequestBody LoginDTO loginDTO) throws IOException {
        return R.ok(authService.login(loginDTO.getAccountNumber(), loginDTO.getVerifyCode(), loginDTO.getLoginMethod()));
    }

    // TODO:退出登录
}
