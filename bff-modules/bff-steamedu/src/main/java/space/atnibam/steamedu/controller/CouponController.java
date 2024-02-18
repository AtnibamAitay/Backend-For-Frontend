package space.atnibam.steamedu.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import space.atnibam.common.core.domain.R;
import space.atnibam.steamedu.service.CouponService;

import javax.annotation.Resource;

/**
 * @ClassName: CouponController
 * @Description: 优惠券控制器
 * @Author: AtnibamAitay
 * @CreateTime: 2024-02-18 11:51
 **/
@Api(tags = "优惠券模块")
@RestController
@RequestMapping("/api/coupon")
public class CouponController {
    @Resource
    private CouponService couponService;

    /**
     * 获取用户未失效的优惠券列表
     *
     * @param userId 用户ID
     * @return R 优惠券列表
     */
    @ApiOperation("获取用户未失效的优惠券列表")
    @GetMapping("/user")
    public R getUserUnexpiredCoupons(int userId) {
        return R.ok(couponService.getUserUnexpiredCoupons(userId));
    }
}