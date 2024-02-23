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
     * 根据用户id查出课程id列表
     *
     * @param userId 用户id
     * @return 课程id列表
     */
    @Select("select course_id from user_courses where user_id = #{userId}")
    List<Integer> selectCourseIdListByUserId(Integer userId);
}