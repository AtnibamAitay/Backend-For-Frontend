package space.atnibam.common.swagger.config;

import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @ClassName: SwaggerWebConfiguration
 * @Description: Swagger资源映射路径配置
 * @Author: AtnibamAitay
 * @CreateTime: 2024-01-31 19:36
 */
public class SwaggerWebConfiguration implements WebMvcConfigurer {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // swagger-ui 地址
        registry.addResourceHandler("/swagger-ui/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/springfox-swagger-ui/");
    }
}