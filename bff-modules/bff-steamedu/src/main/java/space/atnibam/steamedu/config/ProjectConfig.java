package space.atnibam.steamedu.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * @ClassName: ProjectConfig
 * @Description: 项目配置
 * @Author: AtnibamAitay
 * @CreateTime: 2024-02-06 17:01
 **/
@Configuration
@Getter
public class ProjectConfig {

    @Value("${app.id}")
    public String appId;
}
