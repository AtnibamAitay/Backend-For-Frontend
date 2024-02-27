package space.atnibam.steamedu.service;

import com.baomidou.mybatisplus.extension.service.IService;
import space.atnibam.steamedu.model.dto.AdaptiveCourseBaseInfoDTO;
import space.atnibam.steamedu.model.dto.UserCourseDTO;
import space.atnibam.steamedu.model.entity.Course;

import java.util.List;

/**
 * @author Atnibam Aitay
 * @description 针对表【course(课程信息表)】的数据库操作Service
 * @createDate 2024-02-15 01:32:54
 */
public interface CourseService extends IService<Course> {
    /**
     * 获取用户自适应课程列表
     *
     * @param userId 用户ID
     * @return 用户的自适应课程列表
     */
    List<AdaptiveCourseBaseInfoDTO> getUserAdaptiveCourseList(Integer userId);

    /**
     * 获取用户课程列表
     *
     * @param userId 用户ID
     * @return 用户课程列表
     */
    List<UserCourseDTO> getUserCourseList(Integer userId);

    /**
     * 根据SpuId获取课程
     *
     * @param spuId spuId
     */
    Course getCourseBySpuId(Integer spuId);
}