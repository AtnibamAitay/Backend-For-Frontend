package space.atnibam.steamedu.service.impl;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import space.atnibam.steamedu.model.dto.OrderInfoDTO;
import space.atnibam.steamedu.model.dto.StudentInfoDTO;
import space.atnibam.steamedu.service.CourseService;
import space.atnibam.steamedu.service.OrderService;
import space.atnibam.steamedu.service.StudentInfoService;

import javax.annotation.Resource;
import java.util.List;

/**
 * @ClassName: OrderServiceImpl
 * @Description: 订单服务实现类
 * @Author: AtnibamAitay
 * @CreateTime: 2024-03-05 16:14
 **/
@Service
public class OrderServiceImpl implements OrderService {

    @Resource
    private StudentInfoService studentInfoService;
    @Resource
    private CourseService courseService;

    /**
     * 获取课程订单信息
     *
     * @param courseIds 课程ID列表
     * @return 订单信息
     */
    @Override
    public OrderInfoDTO getOrderInfo(List<Integer> courseIds) {
        OrderInfoDTO orderInfoDTO = new OrderInfoDTO();
        OrderInfoDTO.OrderStudentInfoDTO orderStudentInfoDTO = new OrderInfoDTO.OrderStudentInfoDTO();
        StudentInfoDTO studentInfosByCurrentUser = studentInfoService.getStudentInfosByCurrentUser();
        BeanUtils.copyProperties(studentInfosByCurrentUser, orderStudentInfoDTO);
        orderInfoDTO.setStudentInfo(orderStudentInfoDTO);
        orderInfoDTO.setCourseInfo(courseService.getOrderCourseInfo(courseIds));
        return orderInfoDTO;
    }
}
