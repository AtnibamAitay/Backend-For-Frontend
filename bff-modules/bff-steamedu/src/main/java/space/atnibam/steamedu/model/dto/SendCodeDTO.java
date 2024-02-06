package space.atnibam.steamedu.model.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @ClassName: SendCodeDTO
 * @Description: 发送验证码DTO
 * @Author: AtnibamAitay
 * @CreateTime: 2024-02-06 17:17
 **/
@Data
public class SendCodeDTO {
    @NotBlank(message = "账号不能为空")
    @ApiModelProperty("邮箱/手机号")
    private String accountNumber;

    @NotNull(message = "验证码类型不能为空")
    @ApiModelProperty("1-手机号登录验证码、2-邮箱登录验证码、3-绑定手机号验证码、4-绑定邮箱验证码")
    private Integer codeType;
}