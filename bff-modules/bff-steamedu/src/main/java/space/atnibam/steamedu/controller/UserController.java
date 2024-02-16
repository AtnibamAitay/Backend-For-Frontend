package space.atnibam.steamedu.controller;

import cn.dev33.satoken.util.SaResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import space.atnibam.common.core.domain.R;
import space.atnibam.steamedu.service.AuthService;
import space.atnibam.steamedu.service.UserService;

import javax.annotation.Resource;
import java.io.IOException;

/**
 * @ClassName: UserController
 * @Description: 用户相关接口
 * @Author: AtnibamAitay
 * @CreateTime: 2024-02-06 20:08
 **/
@Api(tags = "用户接口")
@RestController
@RequestMapping("/api/user")
public class UserController {
    @Resource
    private AuthService authService;
    @Resource
    private UserService userService;

    /**
     * 获取用户信息
     *
     * @param userId 用户ID
     * @return 用户信息
     */
    @ApiOperation("获取用户头像、用户名、用户所在区")
    @GetMapping("/{userId}/info")
    public R getUserInfo(@PathVariable("userId") String userId) {
        return R.ok(userService.getUserInfo(userId));
    }

    /**
     * 发送验证码API
     *
     * @param accountNumber 邮箱/手机号
     * @param codeType      验证码类型，1-手机号登录验证码、2-邮箱登录验证码、3-绑定手机号验证码、4-绑定邮箱验证码
     * @return 发送结果，成功发送则返回成功信息，否则返回失败原因
     */
    @ApiOperation("发送验证码")
    @PostMapping("/auth/codes")
    public R sendCode(@RequestParam String accountNumber, @RequestParam Integer codeType) {
        return authService.sendCodeByAccount(accountNumber, codeType);
    }

    /**
     * 用户登录API
     *
     * @param accountNumber 邮箱/手机号
     * @param verifyCode    验证码
     * @param loginMethod   登录方式 1-手机号+验证码 2-邮箱+验证码
     * @return 登录结果
     */
    @ApiOperation("用户登录")
    @PostMapping("/auth/login")
    public SaResult login(@RequestParam String accountNumber, @RequestParam String verifyCode, @RequestParam Integer loginMethod) throws IOException {
        return authService.login(accountNumber, verifyCode, loginMethod);
    }
}