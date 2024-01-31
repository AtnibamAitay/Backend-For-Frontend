package space.atnibam.common.service.aop.anno;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @ClassName: MQIdempotency
 * @Description: MQ消息幂等性注解
 * @Author: AtnibamAitay
 * @CreateTime: 2024-01-31 19:36
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface MQIdempotency {
    /**
     * 过期时长（毫秒）
     * 5分钟
     */
    int expire() default 300000;
}
