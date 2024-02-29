package space.atnibam.steamedu.model.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @ClassName: LoginRequestDTO
 * @Description: 登录注册请求类
 * @Author: AtnibamAitay
 * @CreateTime: 2024-02-06 17:27
 **/
@Data
@Builder
public class LoginDTO {
    /**
     * 凭证 手机号登录凭证为手机号码，邮箱登录凭证为邮箱
     */
    @ApiModelProperty("邮箱或手机号")
    @NotNull(message = "账号不能为空")
    @NotBlank(message = "账号不能为空")
    private String accountNumber;

    /**
     * 验证码
     */
    @ApiModelProperty("验证码")
    @NotNull(message = "验证码不能为空")
    @NotBlank(message = "验证码不能为空")
    private String verifyCode;

    /**
     * 登录方式
     * 1-手机号+验证码
     * 2-邮箱+验证码
     */
    @ApiModelProperty("登录方式 1-手机号+验证码 2-邮箱+验证码")
    @NotNull(message = "登录方式不能为空")
    private Integer loginMethod;
}
