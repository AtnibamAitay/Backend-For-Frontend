package space.atnibam.steamedu.model.dto;

import lombok.Data;

/**
 * @ClassName: AdaptiveCourseBaseInfoDTO
 * @Description: 用户的自适应课程DTO
 * @Author: AtnibamAitay
 * @CreateTime: 2024-2-23 09:56
 **/
@Data
public class AdaptiveCourseBaseInfoDTO {
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
}
