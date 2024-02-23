package space.atnibam.steamedu.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import space.atnibam.steamedu.mapper.CourseMapper;
import space.atnibam.steamedu.model.dto.AdaptiveCourseBaseInfoDTO;
import space.atnibam.steamedu.model.dto.UserCourseDTO;
import space.atnibam.steamedu.model.entity.Course;
import space.atnibam.steamedu.service.CourseService;

import java.util.List;

/**
 * @author Atnibam Aitay
 * @description 针对表【course(课程信息表)】的数据库操作Service实现
 * @createDate 2024-02-15 01:32:54
 */
@Service
public class CourseServiceImpl extends ServiceImpl<CourseMapper, Course>
        implements CourseService {

    /**
     * 获取用户自适应课程列表
     *
     * @param userId 用户ID
     * @return 用户的自适应课程列表
     */
    @Override
    public List<AdaptiveCourseBaseInfoDTO> getUserAdaptiveCourseList(Integer userId) {
        // TODO
        return null;
    }

    /**
     * 获取用户课程列表
     *
     * @param userId 用户ID
     * @return 用户课程列表
     */
    @Override
    public List<UserCourseDTO> getUserCourseList(Integer userId) {
        // TODO
        return null;
    }
}