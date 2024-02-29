package space.atnibam.steamedu.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import space.atnibam.common.core.domain.R;
import space.atnibam.steamedu.service.CourseSelectionCartService;

import javax.annotation.Resource;

/**
 * @ClassName: CourseSelectionCartController
 * @Description: 选课单接口
 * @Author: AtnibamAitay
 * @CreateTime: 2024-02-27 14:47
 **/
@Api(tags = "选课单模块")
@RestController
@RequestMapping("/api/course/cart")
public class CourseSelectionCartController {
    @Resource
    private CourseSelectionCartService courseSelectionCartService;

    /**
     * 获取用户的选课单列表
     *
     * @return 购物车列表
     */
    @ApiOperation("获取用户的选课单列表")
    @GetMapping("")
    public R getShoppingCartByUserId() {
        return R.ok(courseSelectionCartService.getCourseSelectionCartByUserId());
    }
}