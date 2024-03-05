package space.atnibam.steamedu.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import space.atnibam.common.core.domain.R;
import space.atnibam.steamedu.service.StudentInfoService;

import javax.annotation.Resource;

/**
 * @ClassName: StudentInfoController
 * @Description: 学生信息模块
 * @Author: AtnibamAitay
 * @CreateTime: 2024-03-05 13:59
 **/
@Api(tags = "学生信息模块")
@RestController
@RequestMapping("/api/student")
public class StudentInfoController {
    @Resource
    private StudentInfoService studentInfoService;

    /**
     * 获取当前用户学生信息
     *
     * @return 当前用户学生信息
     */
    @ApiOperation(value = "获取当前用户学生信息", notes = "获取当前用户学生信息")
    @GetMapping("/info")
    public R getCurrentUserStudentInfos() {
        return R.ok(studentInfoService.getStudentInfosByCurrentUser());
    }
}