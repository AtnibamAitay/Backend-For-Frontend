package space.atnibam.steamedu.model.dto;

import lombok.Data;

/**
 * @ClassName: SystemUpdateDTO
 * @Description: 系统更新信息传输对象
 * @Author: AtnibamAitay
 * @CreateTime: 2024-02-27 16:58
 */
@Data
public class SystemUpdateDTO {

    /**
     * 版本号
     */
    private String version;

    /**
     * 更新链接
     */
    private String updateUrl;
}