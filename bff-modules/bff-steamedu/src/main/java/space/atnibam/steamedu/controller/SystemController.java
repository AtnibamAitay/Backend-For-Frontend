package space.atnibam.steamedu.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import space.atnibam.common.core.domain.R;
import space.atnibam.steamedu.service.SystemService;

import javax.annotation.Resource;

/**
 * @ClassName: SystemController
 * @Description: 系统相关的控制器，用于处理与系统设置相关的请求
 * @Author: AtnibamAitay
 * @CreateTime: 2024-02-27 16:56
 */
@Api(tags = "系统模块")
@RestController
@RequestMapping("/api/system")
public class SystemController {

    @Resource
    private SystemService systemService;

    /**
     * 检查更新接口
     *
     * @return 返回最新的系统版本和下载链接
     */
    @ApiOperation("检查系统更新")
    @GetMapping("/checkUpdate")
    public R checkUpdate() {
        return R.ok(systemService.getSystemUpdateInfo());
    }
}