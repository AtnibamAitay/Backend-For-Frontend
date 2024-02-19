package space.atnibam.steamedu.service.impl;

import org.springframework.stereotype.Service;
import space.atnibam.api.ims.RemoteFriendService;
import space.atnibam.common.core.domain.R;
import space.atnibam.steamedu.service.FriendService;

import javax.annotation.Resource;

/**
 * @ClassName: FriendServiceImpl
 * @Description: 好友服务实现类
 * @Author: AtnibamAitay
 * @CreateTime: 2024-02-19 09:41
 **/
@Service
public class FriendServiceImpl implements FriendService {
    @Resource
    private RemoteFriendService remoteFriendService;

    /**
     * 根据用户id获取收到的好友请求列表
     *
     * @param userId 用户id
     * @return 响应结果
     */
    @Override
    public R getReceivedFriendRequests(int userId) {
        return remoteFriendService.getReceivedFriendRequests(userId);
    }

    /**
     * 根据用户ID获取好友列表
     *
     * @param ownId 用户ID
     * @return 好友列表
     */
    @Override
    public R getFriendList(int ownId) {
        return remoteFriendService.getFriendList(ownId);
    }
}