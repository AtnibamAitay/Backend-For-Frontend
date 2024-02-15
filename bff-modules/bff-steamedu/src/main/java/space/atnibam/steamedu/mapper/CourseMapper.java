package space.atnibam.steamedu.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;
import space.atnibam.steamedu.model.entity.Course;

/**
 * @ClassName: CourseMapper
 * @Description: 针对表【course(课程信息表)】的数据库操作Mapper
 * @Author: AtnibamAitay
 * @CreateTime: 2024-02-15 01:32
 **/
public interface CourseMapper extends BaseMapper<Course> {
    /**
     * 根据课程ID查出status为1的课程信息
     *
     * @param courseId 课程ID
     * @return Course 课程信息
     */
    @Select("select * from course where status = 1 AND course_id = #{courseId}")
    Course selectCourseById(int courseId);
}