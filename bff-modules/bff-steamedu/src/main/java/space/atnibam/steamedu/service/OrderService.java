package space.atnibam.steamedu.service;

import space.atnibam.steamedu.model.dto.OrderInfoDTO;

import java.util.List;

/**
 * @InterfaceName: OrderService
 * @Description: 订单服务接口
 * @Author: AtnibamAitay
 * @CreateTime: 2024-03-05 16:13
 **/
public interface OrderService {
    /**
     * 获取课程订单信息
     *
     * @param courseIds 课程ID列表
     * @return 订单信息
     */
    OrderInfoDTO getOrderInfo(List<Integer> courseIds);
}