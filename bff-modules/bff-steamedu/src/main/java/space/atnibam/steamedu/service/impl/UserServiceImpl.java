package space.atnibam.steamedu.service.impl;

import cn.dev33.satoken.stp.StpUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import space.atnibam.api.auth.dto.SessionUserInfoDTO;
import space.atnibam.api.ums.RemoteUserInfoService;
import space.atnibam.api.ums.model.dto.UpdateUserNameDTO;
import space.atnibam.steamedu.model.dto.ExtendedUserBaseInfoDTO;
import space.atnibam.steamedu.service.UserService;

import javax.annotation.Resource;
import java.util.Map;

import static space.atnibam.api.auth.constants.AuthConstants.USER_INFO;

/**
 * @ClassName: UserServiceImpl
 * @Description: 用户服务实现类
 * @Author: AtnibamAitay
 * @CreateTime: 2024-02-16 21:50
 **/
@Service
public class UserServiceImpl implements UserService {
    @Resource
    private ObjectMapper objectMapper;
    @Resource
    private RemoteUserInfoService remoteUserInfoService;

    /**
     * 获取用户信息
     *
     * @return 用户信息DTO
     */
    @Override
    public ExtendedUserBaseInfoDTO getUserInfo() {
        SessionUserInfoDTO sessionUserInfoDTO = (SessionUserInfoDTO) StpUtil.getSession().get(USER_INFO);
        // 调用远程用户信息服务获取用户信息
        Object userInfo = remoteUserInfoService.getUserInfo(String.valueOf(sessionUserInfoDTO.getUserId())).getData();

        // 将用户信息转换为Map对象
        Map<String, Object> merchantDataMap = (Map<String, Object>) userInfo;

        // 将Map对象转换为ExtendedUserBaseInfoDTO对象
        return objectMapper.convertValue(merchantDataMap, ExtendedUserBaseInfoDTO.class);
    }

    /**
     * 设置用户的用户名
     *
     * @param updateUserNameDTO 包含账号ID、用户名的传输实体
     */
    @Override
    public void setUserName(UpdateUserNameDTO updateUserNameDTO) {
        remoteUserInfoService.updateUserName(updateUserNameDTO);
    }
}
