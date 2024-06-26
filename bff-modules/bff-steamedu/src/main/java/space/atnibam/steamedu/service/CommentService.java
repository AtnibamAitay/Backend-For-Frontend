package space.atnibam.steamedu.service;

import space.atnibam.common.core.domain.R;

/**
 * @InterfaceName: CommentService
 * @Description: 评论服务
 * @Author: AtnibamAitay
 * @CreateTime: 2024-02-03 11:17
 **/
public interface CommentService {
    /**
     * 根据商品ID获取评论
     *
     * @param productId 商品ID
     * @return JSON字符串
     */
    R getCommentsByObjectId(Integer productId);
}