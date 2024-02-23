package space.atnibam.steamedu.model.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @ClassName: UserCourseDTO
 * @Description: 用户的课程DTO
 * @Author: AtnibamAitay
 * @CreateTime: 2024-02-23 10:05
 **/
@EqualsAndHashCode(callSuper = true)
@Data
public class UserCourseDTO extends CourseBriefInfoDTO {
    /**
     * 上课地点
     */
    private String location;
    /**
     * 每节课时长
     */
    private Integer classDuration;
}