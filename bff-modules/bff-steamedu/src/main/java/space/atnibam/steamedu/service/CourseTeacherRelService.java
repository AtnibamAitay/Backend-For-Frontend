package space.atnibam.steamedu.service;

import com.baomidou.mybatisplus.extension.service.IService;
import space.atnibam.steamedu.model.dto.CourseDetailDTO;
import space.atnibam.steamedu.model.dto.TeacherDTO;
import space.atnibam.steamedu.model.entity.CourseTeacherRel;

import java.util.List;

/**
 * @InterfaceName: CourseTeacherRelService
 * @Description: 课程教师关系Service接口
 * @Author: AtnibamAitay
 * @CreateTime: 2024-02-15 15:23
 **/
public interface CourseTeacherRelService extends IService<CourseTeacherRel> {
    /**
     * 根据课程ID查询所有教师的信息
     *
     * @param courseId 课程ID
     * @return 教师信息列表
     */
    List<CourseDetailDTO.TeacherDTO> getCourseDetailTeacherInfoByCourseId(Integer courseId);

    /**
     * 根据课程ID查询所有教师的信息（不含职位）
     *
     * @param courseId 课程ID
     * @return 教师信息列表
     */
    List<TeacherDTO> getNearbyCourseTeacherInfoByCourseId(Integer courseId);
}
