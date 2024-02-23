package space.atnibam.steamedu.service;

import com.baomidou.mybatisplus.extension.service.IService;
import space.atnibam.steamedu.model.entity.UserCourses;

import java.util.List;

/**
 * @author Atnibam Aitay
 * @description 针对表【user_courses(用户的课程表)】的数据库操作Service
 * @createDate 2024-02-23 10:56:04
 */
public interface UserCoursesService extends IService<UserCourses> {
    /**
     * 根据用户id查出课程id列表
     *
     * @param userId     用户id
     * @param courseType 课程类型
     * @return 课程id列表
     */
    List<Integer> getCourseIdListByUserId(Integer userId, Integer courseType);
}
