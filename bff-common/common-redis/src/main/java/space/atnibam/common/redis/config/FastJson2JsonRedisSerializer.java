package space.atnibam.common.redis.config;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONReader;
import com.alibaba.fastjson2.JSONWriter;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.SerializationException;

import java.nio.charset.Charset;

/**
 * @ClassName: FastJson2JsonRedisSerializer<T>
 * @Description: 一个实现了RedisSerializer接口的类，用于将对象序列化为Redis的键值对。<T>为泛型参数，表示要序列化的对象类型。
 * @Author: AtnibamAitay
 * @CreateTime: 2024-01-31 23:09
 **/
public class FastJson2JsonRedisSerializer<T> implements RedisSerializer<T> {
    /**
     * 默认的字符集
     */
    public static final Charset DEFAULT_CHARSET = Charset.forName("UTF-8");

    private Class<T> clazz;

    /**
     * 构造方法，用于创建FastJson2JsonRedisSerializer对象。
     *
     * @param clazz 要序列化的对象类型
     */
    public FastJson2JsonRedisSerializer(Class<T> clazz) {
        super();
        this.clazz = clazz;
    }

    /**
     * 序列化方法，将对象序列化为Redis的键值对。
     *
     * @param t 要序列化的对象
     * @return 序列化后的字节数组
     * @throws SerializationException 序列化异常
     */
    @Override
    public byte[] serialize(T t) throws SerializationException {
        if (t == null) {
            return new byte[0];
        }
        return JSON.toJSONString(t, JSONWriter.Feature.WriteClassName).getBytes(DEFAULT_CHARSET);
    }

    /**
     * 反序列化方法，将Redis的键值对反序列化为对象。
     *
     * @param bytes 要反序列化的字节数组
     * @return 反序列化后得到的对象
     * @throws SerializationException 反序列化异常
     */
    @Override
    public T deserialize(byte[] bytes) throws SerializationException {
        if (bytes == null || bytes.length <= 0) {
            return null;
        }
        String str = new String(bytes, DEFAULT_CHARSET);

        return JSON.parseObject(str, clazz, JSONReader.Feature.SupportAutoType);
    }
}