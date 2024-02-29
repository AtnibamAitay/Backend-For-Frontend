package space.atnibam.steamedu;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Import;
import space.atnibam.common.security.config.SecurityRedisConfig;
import space.atnibam.common.security.handler.GlobalExceptionHandler;
import space.atnibam.common.swagger.annotation.EnableCustomSwagger;
import springfox.documentation.oas.annotations.EnableOpenApi;

@SpringBootApplication
@EnableCustomSwagger
@EnableDiscoveryClient
@EnableFeignClients(basePackages = "space.atnibam.api")
@EnableOpenApi
@MapperScan("space.atnibam.**.mapper")
@Import({SecurityRedisConfig.class, GlobalExceptionHandler.class})
public class SteameduApplication {
    public static void main(String[] args) {
        SpringApplication.run(SteameduApplication.class, args);
    }
}