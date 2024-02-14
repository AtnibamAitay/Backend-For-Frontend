package space.atnibam.steamedu.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import space.atnibam.common.core.domain.R;
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
public class SpuController {
    @Resource
    private CommentService commentService;

    /**
     * 根据商品ID获取评论
     *
     * @param spuId 商品ID
     * @return 评论
     */
    @ApiOperation("根据商品ID获取评论")
    @GetMapping("/comments")
    public R getCommentsBySpuId(@RequestParam Integer spuId) {
        return commentService.getCommentsByObjectId(spuId);
    }

}