package space.atnibam.steamedu.service;


import space.atnibam.steamedu.model.dto.SystemUpdateDTO;

/**
 * @ClassName: SystemService
 * @Description: 系统服务接口，定义系统相关服务方法
 * @Author: AtnibamAitay
 * @CreateTime: 2024-02-27 16:58
 */
public interface SystemService {

    /**
     * 获取系统更新信息
     *
     * @return 返回含有版本号和更新链接的数据传输对象
     */
    SystemUpdateDTO getSystemUpdateInfo();
}