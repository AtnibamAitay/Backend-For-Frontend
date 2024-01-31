package space.atnibam.common.swagger.annotation;

import org.springframework.context.annotation.Import;
import space.atnibam.common.swagger.config.SwaggerAutoConfiguration;

import java.lang.annotation.*;

/**
 * @ClassName: EnableCustomSwagger
 * @Description: 自定义启用Swagger的注解，通过此注解可自动配置并引入Swagger的相关配置类
 * @Author: AtnibamAitay
 * @CreateTime: 2024-01-31 19:36
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Import({SwaggerAutoConfiguration.class})
public @interface EnableCustomSwagger {
}