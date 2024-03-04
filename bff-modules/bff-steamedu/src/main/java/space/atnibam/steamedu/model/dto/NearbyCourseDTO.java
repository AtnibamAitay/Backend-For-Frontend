package space.atnibam.steamedu.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * @ClassName: NearbyCourseDTO
 * @Description: 附近的课程
 * @Author: AtnibamAitay
 * @CreateTime: 2024-02-16 18:38
 **/
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class NearbyCourseDTO extends CourseBriefInfoDTO {
    /**
     * 价格
     * TODO:暂时设定一个固定值用于前端测试使用，后面有规格了再删掉
     */
    private BigDecimal price = BigDecimal.valueOf(100);
    /**
     * 距离
     */
    private String distanceFromUser;
}
