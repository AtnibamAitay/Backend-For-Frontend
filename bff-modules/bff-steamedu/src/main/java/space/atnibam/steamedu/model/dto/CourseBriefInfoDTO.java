package space.atnibam.steamedu.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @ClassName: CourseBriefInfoDTO
 * @Description: 简短的课程信息
 * @Author: AtnibamAitay
 * @CreateTime: 2024-02-18 17:07
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CourseBriefInfoDTO {
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
     * 上课时间
     */
    private String schoolTime;
    /**
     * 教师列表
     */
    private List<UserBaseInfoDTO> teacher;
}
