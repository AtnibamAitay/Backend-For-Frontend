package space.atnibam.steamedu.service.impl;

import cn.dev33.satoken.stp.StpUtil;
import org.springframework.stereotype.Service;
import space.atnibam.api.auth.dto.SessionUserInfoDTO;
import space.atnibam.api.ums.RemoteFriendService;
import space.atnibam.common.core.domain.R;
import space.atnibam.steamedu.service.FriendService;

import javax.annotation.Resource;

import static space.atnibam.api.auth.constants.AuthConstants.USER_INFO;

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
     * @return 响应结果
     */
    @Override
    public R getReceivedFriendRequests() {
        SessionUserInfoDTO sessionUserInfoDTO = (SessionUserInfoDTO) StpUtil.getSession().get(USER_INFO);
        return remoteFriendService.getReceivedFriendRequests(sessionUserInfoDTO.getUserId());
    }

    /**
     * 根据用户ID获取好友列表
     *
     * @return 好友列表
     */
    @Override
    public R getFriendList() {
        SessionUserInfoDTO sessionUserInfoDTO = (SessionUserInfoDTO) StpUtil.getSession().get(USER_INFO);
        return remoteFriendService.getFriendList(sessionUserInfoDTO.getUserId());
    }
}