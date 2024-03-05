package space.atnibam.steamedu.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import space.atnibam.common.core.domain.R;
import space.atnibam.steamedu.service.OrderService;

import javax.annotation.Resource;
import java.util.List;

/**
 * @ClassName: OrderController
 * @Description: 订单模块
 * @Author: AtnibamAitay
 * @CreateTime: 2024-03-05 16:13
 **/
@Api(tags = "订单模块")
@RestController
@RequestMapping("/api/order")
public class OrderController {
    @Resource
    private OrderService orderService;

    /**
     * 获取订单信息
     *
     * @param courseIds 课程ID
     * @return 订单信息
     */
    @ApiOperation(value = "获取订单信息", notes = "获取订单信息")
    @PostMapping("")
    public R getOrderInfo(@RequestBody List<Integer> courseIds) {
        return R.ok(orderService.getOrderInfo(courseIds));
    }
}