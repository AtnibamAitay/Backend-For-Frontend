package space.atnibam.steamedu.service.impl;

import cn.dev33.satoken.stp.StpUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import space.atnibam.api.auth.dto.SessionUserInfoDTO;
import space.atnibam.api.pms.RemoteSpuService;
import space.atnibam.api.pms.dto.SpuBaseInfoDTO;
import space.atnibam.steamedu.mapper.CourseMapper;
import space.atnibam.steamedu.model.dto.AdaptiveCourseBaseInfoDTO;
import space.atnibam.steamedu.model.dto.OrderInfoDTO;
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

import static space.atnibam.api.auth.constants.AuthConstants.USER_INFO;

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
     * @return 用户的自适应课程列表
     */
    @Override
    public List<AdaptiveCourseBaseInfoDTO> getUserAdaptiveCourseList() {
        SessionUserInfoDTO sessionUserInfoDTO = (SessionUserInfoDTO) StpUtil.getSession().get(USER_INFO);
        List<Integer> courseIdListByUserId = userCoursesService.getCourseIdListByUserId(sessionUserInfoDTO.getUserId(), 2);
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
     * @return 用户课程列表
     */
    @Override
    public List<UserCourseDTO> getUserCourseList() {
        SessionUserInfoDTO sessionUserInfoDTO = (SessionUserInfoDTO) StpUtil.getSession().get(USER_INFO);
        List<Integer> courseIdListByUserId = userCoursesService.getCourseIdListByUserId(sessionUserInfoDTO.getUserId(), 1);

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

    /**
     * 根据SpuId获取课程
     *
     * @param spuId spuId
     */
    @Override
    public Course getCourseBySpuId(Integer spuId) {
        return courseMapper.selectCourseById(spuId);
    }

    /**
     * 根据课程ID列表获取订单课程信息
     *
     * @param courseIds 课程ID列表
     * @return 订单课程信息列表
     */
    @Override
    public List<OrderInfoDTO.OrderCourseInfoDTO> getOrderCourseInfo(List<Integer> courseIds) {
        List<Course> courseList = courseMapper.selectActiveCoursesBaseInfo(courseIds);
        List<OrderInfoDTO.OrderCourseInfoDTO> orderCourseInfoDTOList = new ArrayList<>();
        List<Integer> spuIds = courseInfoUtils.extractFieldFromList(courseList, Course::getSpuId);
        List<SpuBaseInfoDTO> spuDetailList = remoteSpuService.getSpuDetailList(spuIds);
        for (Course course : courseList) {
            OrderInfoDTO.OrderCourseInfoDTO orderCourseInfoDTO = new OrderInfoDTO.OrderCourseInfoDTO();
            BeanUtils.copyProperties(course, orderCourseInfoDTO);
            for (SpuBaseInfoDTO spu : spuDetailList) {
                if (course.getSpuId().equals(spu.getSpuId())) {
                    orderCourseInfoDTO.setName(spu.getName());
                }
            }
            LocalDateTime courseStartTime = course.getStartTime();
            LocalDateTime courseEndTime = course.getEndTime();
            orderCourseInfoDTO.setSchoolTime(courseInfoUtils.formatCourseTime(courseStartTime, courseEndTime));
            orderCourseInfoDTOList.add(orderCourseInfoDTO);
        }
        return orderCourseInfoDTOList;
    }
}