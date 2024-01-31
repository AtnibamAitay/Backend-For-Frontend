package space.atnibam.common.redis.config;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.redisson.config.SingleServerConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @ClassName: RedissonConfig
 * @Description: Redisson配置
 * @Author: AtnibamAitay
 * @CreateTime: 2024-01-31 23:09
 **/
@Configuration
public class RedissonConfig {

    /**
     * Redisson客户端注册
     * 单机模式
     */
    @Bean(destroyMethod = "shutdown")
    public RedissonClient createRedissonClient() {

        Config config = new Config();
        SingleServerConfig singleServerConfig = config.useSingleServer();
        singleServerConfig.setAddress("redis://redis.bff-dev.com:6379");
        singleServerConfig.setPassword("nfsn-redis-6379");
        singleServerConfig.setTimeout(3000);
        return Redisson.create(config);
    }
}