package space.atnibam.steamedu.service;

import space.atnibam.common.core.domain.R;

/**
 * @InterfaceName: SpuService
 * @Description: 商品服务
 * @Author: AtnibamAitay
 * @CreateTime: 2024-02-06 19:38
 **/
public interface SpuService {
    /**
     * 根据商品ID获取商品信息
     *
     * @param spuId 商品ID
     * @return 商品信息
     */
    R getSpuDetail(Integer spuId);
}