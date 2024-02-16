package space.atnibam.steamedu.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @ClassName: ExtendedUserBaseInfoDTO
 * @Description: 扩展的基本用户信息，增加了用户所在区名字段
 * @Author: AtnibamAitay
 * @CreateTime: 2024-2-16 20:58（假设这是新的创建时间）
 **/
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExtendedUserBaseInfoDTO extends UserBaseInfoDTO implements Serializable {
    private static final long serialVersionUID = 2L;

    /**
     * 用户所在区名
     */
    private String userLocationRegion;
}