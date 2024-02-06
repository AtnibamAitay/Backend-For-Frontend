package space.atnibam.steamedu.service.impl;

import org.springframework.stereotype.Service;
import space.atnibam.api.cms.RemoteCommentService;
import space.atnibam.common.core.domain.R;
import space.atnibam.steamedu.service.CommentService;

import javax.annotation.Resource;

/**
 * @ClassName: CommentServiceImpl
 * @Description: 评论服务实现类
 * @Author: AtnibamAitay
 * @CreateTime: 2024-02-03 11:17
 **/
@Service
public class CommentServiceImpl implements CommentService {
    @Resource
    private RemoteCommentService remoteCommentService;

    /**
     * 根据商品ID获取评论
     *
     * @param productId 商品ID
     * @param pageNum   页码
     * @param pageSize  每页大小
     * @return JSON字符串
     */
    @Override
    public R getCommentsByObjectId(Integer productId, Integer pageNum, Integer pageSize) {
        return remoteCommentService.getCommentsByObjectId(productId, pageNum, pageSize);
    }
}