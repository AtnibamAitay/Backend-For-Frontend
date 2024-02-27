package space.atnibam.steamedu.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import space.atnibam.common.core.domain.R;
import space.atnibam.steamedu.service.CourseService;

import javax.annotation.Resource;

/**
 * @ClassName: UserCourseController
 * @Description: 用户课程接口
 * @Author: AtnibamAitay
 * @CreateTime: 2024-02-23 09:51
 **/
@Api(tags = "用户课程模块")
@RestController
@RequestMapping("/api/user/course")
public class UserCourseController {
    @Resource
    private CourseService courseService;

    /**
     * 获取用户自适应课程列表
     *
     * @param userId 用户ID
     * @return 用户自适应课程列表
     */
    @ApiOperation(value = "获取用户自适应课程列表", notes = "获取用户自适应课程列表")
    @GetMapping("/adaptive")
    public R getUserAdaptiveCourseList(@RequestParam Integer userId) {
        return R.ok(courseService.getUserAdaptiveCourseList(userId));
    }

    /**
     * 获取用户线下课、线上课课程列表
     *
     * @param userId 用户ID
     * @return 用户线下课课程列表
     */
    @ApiOperation(value = "获取用户线下课、线上课课程列表", notes = "获取用户线下课课程列表")
    @GetMapping("/")
    public R getUserCourseList(@RequestParam Integer userId) {
        return R.ok(courseService.getUserCourseList(userId));
    }

    // TODO:用户用户线下课、线上课课程详细页

}