package space.atnibam.steamedu.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @ClassName: UserBaseInfoDTO
 * @Description: 教师的基本用户信息
 * @Author: AtnibamAitay
 * @CreateTime: 2024-2-16 20:58
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserBaseInfoDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 头像
     */
    private String userAvatar;
    /**
     * 用户名
     */
    private String userName;
}