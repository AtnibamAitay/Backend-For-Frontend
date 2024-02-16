package space.atnibam.steamedu.model.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * @ClassName: NearbyCourseDTO
 * @Description: 附近的课程
 * @Author: AtnibamAitay
 * @CreateTime: 2024-02-16 18:38
 **/
@Data
public class NearbyCourseDTO {
    /**
     * 课程id
     */
    private Integer courseId;
    /**
     * 课程类型
     */
    private Integer courseType;
    /**
     * 课程名
     */
    private String name;
    /**
     * 价格
     */
    private BigDecimal price;
    /**
     * 距离
     */
    private String distanceFromUser;
    /**
     * 上课时间
     */
    private String schoolTime;
    /**
     * 教师列表
     */
    private List<UserBaseInfoDTO> teacher;
}
