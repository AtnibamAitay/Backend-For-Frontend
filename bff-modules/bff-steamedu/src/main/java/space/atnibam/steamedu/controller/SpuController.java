package space.atnibam.steamedu.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import space.atnibam.steamedu.api.RemoteSpuService;
import space.atnibam.steamedu.service.CommentService;

import javax.annotation.Resource;

/**
 * @ClassName: SpuController
 * @Description: 商品（课程）接口
 * @Author: AtnibamAitay
 * @CreateTime: 2024-02-01 00:26
 **/
@Api(tags = "课程接口")
@RestController
@RequestMapping("/api/spu")
public class SpuController implements RemoteSpuService {
    @Resource
    private CommentService commentService;

    /**
     * 根据商品ID获取评论
     *
     * @param spuId 商品ID
     * @return 评论
     */
    @Override
    @ApiOperation("根据商品ID获取评论")
    @GetMapping("/comments/{spuId}")
    public String getCommentsBySpuId(@PathVariable("spuId") Integer spuId) {
        return commentService.getCommentsByObjectId(spuId, 1, 10);
    }

}