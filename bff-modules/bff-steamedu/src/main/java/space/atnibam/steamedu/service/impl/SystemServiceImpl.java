package space.atnibam.steamedu.service.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import space.atnibam.steamedu.model.dto.SystemUpdateDTO;
import space.atnibam.steamedu.service.SystemService;

/**
 * @ClassName: SystemServiceImpl
 * @Description: 系统服务实现类，实现获取系统更新信息等功能
 * @Author: AtnibamAitay
 * @CreateTime: 2024-02-27 16:58
 */
@Service
public class SystemServiceImpl implements SystemService {

    @Value("${app.version}")
    private String systemVersion;

    @Value("${app.update-url}")
    private String updateUrl;

    /**
     * 获取系统更新信息
     *
     * @return 返回含有版本号和更新链接的数据传输对象
     */
    @Override
    public SystemUpdateDTO getSystemUpdateInfo() {
        SystemUpdateDTO systemUpdateDTO = new SystemUpdateDTO();
        systemUpdateDTO.setVersion(systemVersion);
        systemUpdateDTO.setUpdateUrl(updateUrl);
        return systemUpdateDTO;
    }
}