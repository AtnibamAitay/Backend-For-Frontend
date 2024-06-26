package space.atnibam.steamedu.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import space.atnibam.steamedu.model.entity.Course;

import java.util.List;

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

    /**
     * 查询所有未结课且未被删除的课程的信息
     *
     * @return Course 课程信息
     */
    @Select("SELECT course_id, spu_id, longitude, latitude, course_type, start_time, end_time " +
            "FROM course " +
            "WHERE status = 1 AND completion_status = 0 AND course_type = #{course_type}")
    List<Course> selectActiveCourses(@Param("course_type") Integer course_type);

    /**
     * 查询所有未结课且未被删除的课程的基础信息，支持根据多个课程类型查询
     *
     * @param courseIdList 课程ID列表
     * @return List<Course> 课程信息集合
     */
    @Select("<script>" +
            "SELECT course_id, spu_id, course_type, start_time, end_time, location, class_duration " +
            "FROM course " +
            "WHERE status = 1 AND completion_status = 0 AND spu_id IN " +
            "<foreach item='item' index='index' collection='courseIdList' open='(' separator=',' close=')'>" +
            "#{item}" +
            "</foreach>" +
            "</script>")
    List<Course> selectActiveCoursesBaseInfo(@Param("courseIdList") List<Integer> courseIdList);


    /**
     * 根据SPUID查出课程信息
     *
     * @param spuId 商品ID
     * @return Course 课程信息
     */
    @Select("SELECT * FROM course WHERE spu_id = #{spuId} AND status = 1")
    Course selectCourseBySpuId(int spuId);
}