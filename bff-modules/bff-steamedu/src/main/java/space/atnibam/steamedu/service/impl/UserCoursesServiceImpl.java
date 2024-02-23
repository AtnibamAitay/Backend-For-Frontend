package space.atnibam.steamedu.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import space.atnibam.steamedu.mapper.UserCoursesMapper;
import space.atnibam.steamedu.model.entity.UserCourses;
import space.atnibam.steamedu.service.UserCoursesService;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Atnibam Aitay
 * @description 针对表【user_courses(用户的课程表)】的数据库操作Service实现
 * @createDate 2024-02-23 10:56:04
 */
@Service
public class UserCoursesServiceImpl extends ServiceImpl<UserCoursesMapper, UserCourses>
        implements UserCoursesService {

    @Resource
    private UserCoursesMapper userCoursesMapper;

    /**
     * 根据用户id查出课程id列表
     *
     * @param userId     用户id
     * @param courseType 课程类型
     * @return 课程id列表
     */
    @Override
    public List<Integer> getCourseIdListByUserId(Integer userId, Integer courseType) {
        return userCoursesMapper.selectCourseIdListByUserIdAndCourseType(userId, courseType);
    }
}




