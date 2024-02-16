package space.atnibam.steamedu.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import space.atnibam.common.core.domain.R;
import space.atnibam.steamedu.service.CommentService;
import space.atnibam.steamedu.service.SpuService;

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
    @Resource
    private SpuService spuService;

    /**
     * 根据位置获取用户附近的课程列表
     *
     * @param lat 纬度
     * @param lng 经度
     * @return 课程列表
     */
    @ApiOperation(value = "获取用户附近的课程列表", notes = "根据传入的经纬度获取用户附近的课程列表")
    @GetMapping("/nearby")
    public R getNearbyCourseList(@RequestParam Double lat, @RequestParam Double lng) {
        return R.ok(spuService.getNearbyCourseList(lat, lng));
    }

    /**
     * 根据课程ID获取课程详细信息（商品详情）
     *
     * @param courseId 课程ID
     * @return 课程信息响应实体R，包含CourseDetailDTO对象
     */
    @GetMapping("/{courseId}/detail")
    @ApiOperation(value = "获取课程详细信息", notes = "根据传入的课程ID获取课程详细信息，返回封装后的商品详情数据")
    public R getCourseDetail(@PathVariable("courseId") Integer courseId) {
        return spuService.getCourseDetailByCourseId(courseId);
    }

    /**
     * 根据课程的商品ID获取评论
     *
     * @param spuId 课程的商品ID
     * @return 评论
     */
    @ApiOperation("根据课程的商品ID获取3条评论")
    @GetMapping("/comments")
    public R getCommentsBySpuId(@RequestParam Integer spuId) {
        return commentService.getCommentsByObjectId(spuId);
    }

}