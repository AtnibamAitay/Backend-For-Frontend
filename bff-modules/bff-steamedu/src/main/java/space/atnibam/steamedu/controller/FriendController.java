package space.atnibam.steamedu.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import space.atnibam.common.core.domain.R;
import space.atnibam.steamedu.service.FriendService;

import javax.annotation.Resource;

/**
 * @ClassName: FriendController
 * @Description: 好友模块控制器
 * @Author: AtnibamAitay
 * @CreateTime: 2024-02-19 09:40
 **/
@Api(tags = "好友模块")
@RestController
@RequestMapping("/api/friend")
public class FriendController {
    @Resource
    private FriendService friendService;

    /**
     * 根据用户id获取收到的好友请求列表
     *
     * @return 响应结果
     */
    @ApiOperation("获取收到的好友请求列表")
    @GetMapping("/received")
    public R getReceivedFriendRequests() {
        return friendService.getReceivedFriendRequests();
    }

    /**
     * 根据用户ID获取好友列表
     *
     * @param ownId 用户ID
     * @return 好友列表
     */
    @ApiOperation("获取好友列表")
    @GetMapping("/list/{ownId}")
    public R getFriendList(@PathVariable("ownId") Integer ownId) {
        return friendService.getFriendList(ownId);
    }
}