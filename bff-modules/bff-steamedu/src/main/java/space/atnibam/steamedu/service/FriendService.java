package space.atnibam.steamedu.service;

import space.atnibam.common.core.domain.R;

/**
 * @InterfaceName: FriendService
 * @Description: 好友服务接口类
 * @Author: AtnibamAitay
 * @CreateTime: 2024-02-19 09:40
 **/
public interface FriendService {
    /**
     * 根据用户id获取收到的好友请求列表
     *
     * @param userId 用户id
     * @return 响应结果
     */
    R getReceivedFriendRequests(int userId);

    /**
     * 根据用户ID获取好友列表
     *
     * @param ownId 用户ID
     * @return 好友列表
     */
    R getFriendList(int ownId);
}