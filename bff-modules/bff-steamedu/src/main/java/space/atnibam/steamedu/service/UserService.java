package space.atnibam.steamedu.service;

import space.atnibam.api.ums.model.dto.UpdateUserNameDTO;
import space.atnibam.steamedu.model.dto.ExtendedUserBaseInfoDTO;

/**
 * @InterfaceName: UserService
 * @Description: 用户服务接口
 * @Author: AtnibamAitay
 * @CreateTime: 2024-02-16 21:50
 **/
public interface UserService {
    /**
     * 获取用户信息
     *
     * @return 用户信息DTO
     */
    ExtendedUserBaseInfoDTO getUserInfo();

    /**
     * 设置用户的用户名
     *
     * @param updateUserNameDTO 包含账号ID、用户名的传输实体
     */
    void setUserName(UpdateUserNameDTO updateUserNameDTO);
}
