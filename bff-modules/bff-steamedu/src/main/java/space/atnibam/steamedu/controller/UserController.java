package space.atnibam.steamedu.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import space.atnibam.api.ums.model.UpdateUserNameDTO;
import space.atnibam.common.core.domain.R;
import space.atnibam.steamedu.service.UserService;

import javax.annotation.Resource;

/**
 * @ClassName: UserController
 * @Description: 用户相关接口
 * @Author: AtnibamAitay
 * @CreateTime: 2024-02-06 20:08
 **/
@Api(tags = "用户接口")
@RestController
@RequestMapping("/api/user")
public class UserController {
    @Resource
    private UserService userService;

    /**
     * 获取用户信息
     *
     * @param userId 用户ID
     * @return 用户信息
     */
    @ApiOperation("获取用户头像、用户名、用户所在区")
    @GetMapping("/{userId}/info")
    public R getUserInfo(@PathVariable("userId") String userId) {
        return R.ok(userService.getUserInfo(userId));
    }

    /**
     * 设置用户的用户名
     *
     * @param updateUserNameDTO 包含账号ID、用户名的传输实体
     */
    @ApiOperation("设置用户名")
    @PostMapping("/set-username")
    public R setUserName(@RequestBody UpdateUserNameDTO updateUserNameDTO) {
        userService.setUserName(updateUserNameDTO);
        return R.ok();
    }
}