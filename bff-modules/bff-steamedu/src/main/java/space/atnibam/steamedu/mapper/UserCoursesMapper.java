package space.atnibam.steamedu.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;
import space.atnibam.steamedu.model.entity.UserCourses;

import java.util.List;

/**
 * @author Atnibam Aitay
 * @description 针对表【user_courses(用户的课程表)】的数据库操作Mapper
 * @createDate 2024-02-23 10:56:04
 * @Entity space.atnibam.steamedu.model.entity.UserCourses
 */
public interface UserCoursesMapper extends BaseMapper<UserCourses> {
    /**
     * 根据用户id和课程类型查出课程id列表
     *
     * @param userId     用户id
     * @param courseType 课程类型（0代表线上课，1代表线下课，2代表自适应课程）
     * @return 课程id列表
     */
    @Select("SELECT uc.course_id FROM user_courses uc INNER JOIN course c ON uc.course_id = c.course_id WHERE uc.user_id = #{userId} AND c.course_type = #{courseType}")
    List<Integer> selectCourseIdListByUserIdAndCourseType(Integer userId, Integer courseType);
}