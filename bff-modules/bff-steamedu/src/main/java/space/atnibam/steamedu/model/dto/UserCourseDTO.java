package space.atnibam.steamedu.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @ClassName: UserCourseDTO
 * @Description: 用户的课程DTO
 * @Author: AtnibamAitay
 * @CreateTime: 2024-02-23 10:05
 **/
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
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