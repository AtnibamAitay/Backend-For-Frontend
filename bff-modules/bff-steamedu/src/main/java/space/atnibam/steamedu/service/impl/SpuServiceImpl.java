package space.atnibam.steamedu.service.impl;

import org.springframework.stereotype.Service;
import space.atnibam.api.pms.RemoteSpuService;
import space.atnibam.common.core.domain.R;
import space.atnibam.steamedu.service.SpuService;

import javax.annotation.Resource;

/**
 * @ClassName: SpuServiceImpl
 * @Description: 商品服务实现类
 * @Author: AtnibamAitay
 * @CreateTime: 2024-02-06 19:38
 **/
@Service
public class SpuServiceImpl implements SpuService {

    @Resource
    private RemoteSpuService remoteSpuService;

    /**
     * 根据商品ID获取商品信息
     *
     * @param spuId 商品ID
     * @return 商品信息
     */
    @Override
    public R getSpuDetail(Integer spuId) {
        return remoteSpuService.getSpuDetail(spuId);
    }
}