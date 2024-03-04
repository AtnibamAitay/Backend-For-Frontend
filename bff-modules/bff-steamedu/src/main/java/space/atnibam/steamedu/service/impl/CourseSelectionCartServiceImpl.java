package space.atnibam.steamedu.service.impl;

import cn.dev33.satoken.stp.StpUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import space.atnibam.api.auth.dto.SessionUserInfoDTO;
import space.atnibam.api.oms.RemoteCartService;
import space.atnibam.api.oms.model.dto.ShoppingCartDTO;
import space.atnibam.steamedu.model.dto.NearbyCourseDTO;
import space.atnibam.steamedu.model.entity.Course;
import space.atnibam.steamedu.service.CourseSelectionCartService;
import space.atnibam.steamedu.service.CourseService;
import space.atnibam.steamedu.service.CourseTeacherRelService;
import space.atnibam.steamedu.utils.CourseInfoUtils;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static space.atnibam.api.auth.constants.AuthConstants.USER_INFO;

/**
 * @ClassName: CourseSelectionCartServiceImpl
 * @Description:
 * @Author: AtnibamAitay
 * @CreateTime: 2024-02-27 14:55
 **/
@Service
public class CourseSelectionCartServiceImpl implements CourseSelectionCartService {
    @Resource
    private CourseService courseService;
    @Resource
    private RemoteCartService remoteCartService;
    @Resource
    private CourseTeacherRelService courseTeacherRelService;
    @Resource
    private CourseInfoUtils courseInfoUtils;

    @Value("${app.id}")
    private int appId;

    /**
     * 获取用户的选课单列表
     *
     * @return 选课单列表
     */
    @Override
    public List<NearbyCourseDTO> getCourseSelectionCartByUserId() {
        SessionUserInfoDTO sessionUserInfoDTO = (SessionUserInfoDTO) StpUtil.getSession().get(USER_INFO);
        List<NearbyCourseDTO> courseBriefInfoDTOList = new ArrayList<>();
        List<ShoppingCartDTO> shoppingCartDTOList = remoteCartService.getShoppingCartByUserId(sessionUserInfoDTO.getUserId(), appId);
        for (ShoppingCartDTO shoppingCartDTO : shoppingCartDTOList) {
            NearbyCourseDTO courseBriefInfoDTO = new NearbyCourseDTO();
            Course course = courseService.getCourseBySpuId(shoppingCartDTO.getSpuId());
            BeanUtils.copyProperties(course, courseBriefInfoDTO);
            LocalDateTime courseStartTime = course.getStartTime();
            LocalDateTime courseEndTime = course.getEndTime();
            courseBriefInfoDTO.setName(shoppingCartDTO.getName());
            courseBriefInfoDTO.setSchoolTime(courseInfoUtils.formatCourseTime(courseStartTime, courseEndTime));
            courseBriefInfoDTO.setTeacher(courseTeacherRelService.getNearbyCourseTeacherInfoByCourseId(course.getCourseId()));
            // TODO: 这是为了方便前端测试而临时设置的一个固定值，在后续开发完距离计算部分后，这部分将需要被修改
            courseBriefInfoDTO.setDistanceFromUser("3.2km");
            courseBriefInfoDTOList.add(courseBriefInfoDTO);
        }
        return courseBriefInfoDTOList;
    }

    // TODO:根据一个或多个课程id删除掉指定用户的选课单中的课程
}