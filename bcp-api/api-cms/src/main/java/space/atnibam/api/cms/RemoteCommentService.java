package space.atnibam.api.cms;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import space.atnibam.common.core.domain.R;

/**
 * @InterfaceName: RemoteCommentService
 * @Description: 远程评论服务
 * @Author: AtnibamAitay
 * @CreateTime: 2024-02-03 10:53
 **/
@FeignClient(value = "modules-cms", contextId = "cms-comment")
public interface RemoteCommentService {

    /**
     * 这个端点允许你
     * 根据对象ID获取评论树
     * 获取特定对象的评论树列表
     *
     * @param objectId 需要获取评论的对象的ID
     * @param pageNum  需要获取的页码，默认值为1
     * @param pageSize 每一页的大小，默认值为10
     * @return 返回构建的评论树
     */
    @GetMapping("/api/comments/{objectId}")
    R getCommentsByObjectId(
            @PathVariable Integer objectId,
            @RequestParam(defaultValue = "1") Integer pageNum,
            @RequestParam(defaultValue = "10") Integer pageSize);
}