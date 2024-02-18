package space.atnibam.steamedu.service;

import space.atnibam.steamedu.model.dto.UserCouponDetailDTO;

import java.util.List;

/**
 * @InterfaceName: CouponService
 * @Description: 优惠券服务接口
 * @Author: AtnibamAitay
 * @CreateTime: 2024-02-18 11:52
 **/
public interface CouponService {
    /**
     * 获取用户未使用且未过期的优惠券
     *
     * @param userId 用户ID
     * @return 用户未使用且未过期的优惠券列表
     */
    List<UserCouponDetailDTO> getUserUnexpiredCoupons(int userId);
}