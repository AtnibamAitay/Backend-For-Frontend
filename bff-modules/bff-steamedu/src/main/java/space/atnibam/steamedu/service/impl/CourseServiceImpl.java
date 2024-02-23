package space.atnibam.steamedu.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import space.atnibam.api.pms.RemoteSpuService;
import space.atnibam.api.pms.dto.SpuBaseInfoDTO;
import space.atnibam.steamedu.mapper.CourseMapper;
import space.atnibam.steamedu.model.dto.AdaptiveCourseBaseInfoDTO;
import space.atnibam.steamedu.model.dto.UserCourseDTO;
import space.atnibam.steamedu.model.entity.Course;
import space.atnibam.steamedu.service.CourseService;
import space.atnibam.steamedu.service.CourseTeacherRelService;
import space.atnibam.steamedu.service.UserCoursesService;
import space.atnibam.steamedu.utils.CourseInfoUtils;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Atnibam Aitay
 * @description 针对表【course(课程信息表)】的数据库操作Service实现
 * @createDate 2024-02-15 01:32:54
 */
@Service
public class CourseServiceImpl extends ServiceImpl<CourseMapper, Course> implements CourseService {
    @Resource
    private CourseMapper courseMapper;
    @Resource
    private UserCoursesService userCoursesService;
    @Resource
    private CourseTeacherRelService courseTeacherRelService;
    @Resource
    private RemoteSpuService remoteSpuService;
    @Resource
    private CourseInfoUtils courseInfoUtils;

    /**
     * 获取用户自适应课程列表
     *
     * @param userId 用户ID
     * @return 用户的自适应课程列表
     */
    @Override
    public List<AdaptiveCourseBaseInfoDTO> getUserAdaptiveCourseList(Integer userId) {
        List<Integer> courseIdListByUserId = userCoursesService.getCourseIdListByUserId(userId, 2);
        List<SpuBaseInfoDTO> spuDetailList = remoteSpuService.getSpuDetailList(courseIdListByUserId);

        // 使用 Java 8 Stream API 进行转换
        List<AdaptiveCourseBaseInfoDTO> adaptiveCourseList = spuDetailList.stream().filter(spu -> spu.getMerchant() != null).map(spu -> {
            AdaptiveCourseBaseInfoDTO adaptiveCourse = new AdaptiveCourseBaseInfoDTO();
            adaptiveCourse.setCourseId(spu.getSpuId());
            adaptiveCourse.setName(spu.getName());
            adaptiveCourse.setUserName(spu.getMerchant().getUserName());
            return adaptiveCourse;
        }).collect(Collectors.toList());

        return adaptiveCourseList;
    }

    /**
     * 获取用户课程列表
     *
     * @param userId 用户ID
     * @return 用户课程列表
     */
    @Override
    public List<UserCourseDTO> getUserCourseList(Integer userId) {
        List<Integer> courseIdListByUserId = userCoursesService.getCourseIdListByUserId(userId, 1);

        List<Course> courses = courseMapper.selectActiveCoursesBaseInfo(courseIdListByUserId);
        List<Integer> spuIds = courseInfoUtils.extractFieldFromList(courses, Course::getSpuId);
        List<SpuBaseInfoDTO> spuDetailList = remoteSpuService.getSpuDetailList(spuIds);
        List<UserCourseDTO> userCourseList = new ArrayList<>();
        for (Course course : courses) {
            UserCourseDTO userCourse = new UserCourseDTO();
            BeanUtils.copyProperties(course, userCourse);
            for (SpuBaseInfoDTO spu : spuDetailList) {
                if (course.getSpuId().equals(spu.getSpuId())) {
                    BeanUtils.copyProperties(spu, userCourse);
                }
            }
            LocalDateTime courseStartTime = course.getStartTime();
            LocalDateTime courseEndTime = course.getEndTime();
            userCourse.setSchoolTime(courseInfoUtils.formatCourseTime(courseStartTime, courseEndTime));
            userCourse.setTeacher(courseTeacherRelService.getNearbyCourseTeacherInfoByCourseId(course.getCourseId()));
            userCourseList.add(userCourse);
        }
        return userCourseList;
    }
}