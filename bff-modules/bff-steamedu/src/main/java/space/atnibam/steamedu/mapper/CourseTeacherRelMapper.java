package space.atnibam.steamedu.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;
import space.atnibam.steamedu.model.entity.CourseTeacherRel;

import java.util.List;

/**
 * @ClassName: CourseTeacherRelMapper
 * @Description: 针对表【course_teacher_rel】的数据库操作Mapper
 * @Author: AtnibamAitay
 * @CreateTime: 2024-02-15 15:23
 **/
public interface CourseTeacherRelMapper extends BaseMapper<CourseTeacherRel> {
    /**
     * 根据课程id查询教师信息列表
     *
     * @param courseId 课程id
     * @return 教师信息列表
     */
    @Select("select * from course_teacher_rel where course_id = #{courseId}")
    List<CourseTeacherRel> selectCourseTeacherRelByCourseId(Integer courseId);
}




