package space.atnibam.common.core.exception;

import space.atnibam.common.core.enums.ResultCode;
import space.atnibam.common.core.exception.base.BaseException;

/**
 * @ClassName: RedisException
 * @Description: Redis异常类，继承自BaseException
 * @Author: AtnibamAitay
 * @CreateTime: 2024-1-31 14:34
 **/
public class RedisException extends BaseException {
    /**
     * 构造方法
     *
     * @param resultCode 异常对应的结果代码
     */
    public RedisException(ResultCode resultCode) {
        super(resultCode);
    }
}