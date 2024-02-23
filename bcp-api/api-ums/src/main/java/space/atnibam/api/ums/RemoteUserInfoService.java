package space.atnibam.api.ums;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import space.atnibam.api.ums.model.UpdateUserNameDTO;
import space.atnibam.common.core.domain.R;

import java.util.List;

/**
 * 用户信息远程调用接口
 */
@FeignClient(value = "modules-ums", contextId = "userInfo", url = "http://local.atnibam.space:9030")
public interface RemoteUserInfoService {
    /**
     * 查询用户信息
     *
     * @param userId 用户ID
     * @return 用户信息实体类
     */
    @GetMapping("/api/userInfo/full/{userId}")
    R getUserInfo(@PathVariable(value = "userId") String userId);

    /**
     * 根据用户id查出用户名、用户头像、用户简介
     *
     * @param userId 用户ID
     * @return 用户信息DTO
     */
    @GetMapping("/api/userInfo/{userId}")
    R getDetailedUserInfo(@PathVariable(value = "userId") Integer userId);

    /**
     * 根据一个或多个用户id查出用户名、用户头像列表
     *
     * @param userIds 用户ID列表
     * @return 用户信息DTO列表
     */
    @PostMapping("/api/userInfo/basic-info")
    R getBasicUserInfo(@RequestBody List<Integer> userIds);

    /**
     * 设置用户的用户名
     *
     * @param updateUserNameDTO 包含账号ID、用户名的传输实体
     * @return 设置用户名的结果
     */
    @PostMapping("/api/userInfo/username")
    R updateUserName(@RequestBody UpdateUserNameDTO updateUserNameDTO);
}