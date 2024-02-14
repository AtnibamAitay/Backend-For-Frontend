package space.atnibam.steamedu.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import space.atnibam.api.cms.RemoteCommentService;
import space.atnibam.common.core.domain.R;
import space.atnibam.steamedu.model.dto.SpuDetailPageCommentDTO;
import space.atnibam.steamedu.service.CommentService;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
    @Resource
    private ObjectMapper objectMapper;

    /**
     * 根据商品ID获取商品的三条评论（不含子评论）
     *
     * @param spuId 商品ID
     * @return JSON字符串
     */
    @Override
    public R getCommentsByObjectId(Integer spuId) {
        // 调用远程评论服务获取商品的三条评论
        Object commentsList = remoteCommentService.getTopLevelCommentsByObjectId(spuId, 1, 3, String.valueOf(2)).getData();
        // 将评论数据转换为Map列表
        List<Map<String, Object>> commentsListDataMap = (List<Map<String, Object>>) commentsList;
        // 创建评论DTO列表
        List<SpuDetailPageCommentDTO> comments = new ArrayList<>();
        // 遍历评论数据列表
        for (Map<String, Object> commentMap : commentsListDataMap) {
            // 将评论数据转换为评论DTO对象
            SpuDetailPageCommentDTO commentDTO = objectMapper.convertValue(commentMap, SpuDetailPageCommentDTO.class);
            // 将评论用户信息转换为CommentUserInfoDTO对象，并与评论DTO关联
            commentDTO.setUserInfo(objectMapper.convertValue(commentMap.get("commentUserInfo"), SpuDetailPageCommentDTO.CommentUserInfoDTO.class));
            // 将评论DTO添加到评论DTO列表中
            comments.add(commentDTO);
        }
        // 返回包含评论DTO列表的JSON字符串
        return R.ok(comments);
    }
}