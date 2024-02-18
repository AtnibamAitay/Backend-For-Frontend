package space.atnibam.steamedu.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import space.atnibam.api.sms.RemoteCouponService;
import space.atnibam.steamedu.model.dto.UserCouponDetailDTO;
import space.atnibam.steamedu.service.CouponService;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @ClassName: CouponServiceImpl
 * @Description: 优惠券服务实现类
 * @Author: AtnibamAitay
 * @CreateTime: 2024-02-18 11:52
 **/
@Service
public class CouponServiceImpl implements CouponService {
    @Value("${app.id}")
    private int appId;
    @Resource
    private ObjectMapper objectMapper;
    @Resource
    private RemoteCouponService remoteCouponService;

    /**
     * 获取用户未使用且未过期的优惠券
     *
     * @param userId 用户ID
     * @return 用户未使用且未过期的优惠券列表
     */
    @Override
    public List<UserCouponDetailDTO> getUserUnexpiredCoupons(int userId) {
        // 调用远程服务获取用户未使用且未过期的优惠券
        Object coupons = remoteCouponService.getUserUnexpiredCoupons(appId, userId).getData();
        // 将优惠券列表转换为Map列表
        List<Map<String, Object>> couponListMap = (List<Map<String, Object>>) coupons;

        // 创建优惠券列表
        List<UserCouponDetailDTO> couponsDTO = new ArrayList<>();
        // 遍历优惠券列表
        for (Map<String, Object> countMap : couponListMap) {
            // 将Map转换为优惠券详情对象
            UserCouponDetailDTO couponDetailDTO = objectMapper.convertValue(countMap, UserCouponDetailDTO.class);
            // 将优惠券的最小消费门槛转换为MinSpendThresholdsDTO对象
            couponDetailDTO.setMinSpendThresholds(objectMapper.convertValue(countMap.get("minSpendThresholds"), UserCouponDetailDTO.MinSpendThresholdsDTO.class));
            // 将优惠券详情对象添加到优惠券列表中
            couponsDTO.add(couponDetailDTO);
        }
        // 返回优惠券列表
        return couponsDTO;
    }

}