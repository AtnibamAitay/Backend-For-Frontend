package space.atnibam.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Import;
import space.atnibam.common.security.config.SecurityRedisConfig;

@SpringBootApplication
@EnableDiscoveryClient
@Import(SecurityRedisConfig.class)
public class BffGatewayApplication {
    public static void main(String[] args) {
        SpringApplication.run(BffGatewayApplication.class, args);
    }
}