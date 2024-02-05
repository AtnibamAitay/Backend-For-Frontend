package space.atnibam.steamedu.api;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @InterfaceName: RemoteSpuService
 * @Description: 远程评论服务
 * @Author: AtnibamAitay
 * @CreateTime: 2024-02-03 10:53
 **/
@FeignClient(value = "modules-steamedu", contextId = "steamedu-comment")
public interface RemoteSpuService {

    /**
     * 根据商品ID获取评论
     *
     * @param spuId 商品ID
     * @return 评论
     */
    @GetMapping("/comments/{spuId}")
    String getCommentsBySpuId(@PathVariable("spuId") Integer spuId);
}