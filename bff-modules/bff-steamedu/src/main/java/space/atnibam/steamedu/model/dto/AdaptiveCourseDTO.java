package space.atnibam.steamedu.model.dto;

import lombok.Data;

/**
 * @ClassName: AdaptiveCourseDTO
 * @Description: 自适应课程DTO
 * @Author: AtnibamAitay
 * @CreateTime: 2024-02-16 18:52
 **/
@Data
public class AdaptiveCourseDTO {
    /**
     * 课程id
     */
    private Integer courseId;
    /**
     * 课程名
     */
    private String name;
    /**
     * 作者/机构名
     */
    private String userName;
    /**
     * 总报名人数（累计销量）
     */
    private Integer salesVolume;
}
