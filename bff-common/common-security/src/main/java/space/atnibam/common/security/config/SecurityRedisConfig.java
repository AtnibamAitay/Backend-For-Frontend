package space.atnibam.common.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;

/**
 * @ClassName: SecurityRedisConfig
 * @Description: Redis配置
 * @Author: AtnibamAitay
 * @CreateTime: 2024-01-31 20:43
 */
@Configuration
public class SecurityRedisConfig {

    @Bean
    public RedisConnectionFactory redisConnectionFactory() {
        LettuceConnectionFactory lettuceConnectionFactory = new LettuceConnectionFactory();
        lettuceConnectionFactory.setHostName("redis.bff-dev.com");
        lettuceConnectionFactory.setPort(6379);
        lettuceConnectionFactory.setPassword("nfsn-redis-6379");
        lettuceConnectionFactory.setDatabase(0);
        lettuceConnectionFactory.setTimeout(10000);
        return lettuceConnectionFactory;
    }
}
