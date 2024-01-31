package space.atnibam.steamedu;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("space.atnibam.**.mapper")
public class SteameduApplication {
    public static void main(String[] args) {
        SpringApplication.run(SteameduApplication.class, args);
    }
}