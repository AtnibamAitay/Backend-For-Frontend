package space.atnibam.common.core.exception;

import space.atnibam.common.core.enums.ResultCode;
import space.atnibam.common.core.exception.base.BaseException;

/**
 * @ClassName: SystemServiceException
 * @Description: 该类用于封装系统服务层执行过程中遇到的异常情况，继承自基础异常类BaseException
 * @Author: AtnibamAitay
 * @CreateTime: 2024-1-31 16:41
 */
public class SystemServiceException extends BaseException {

    /**
     * 构造方法，传入一个ResultCode对象以提供详细的错误信息和状态码
     *
     * @param resultCode 包含系统服务异常相关错误信息和状态码的枚举类型对象
     */
    public SystemServiceException(ResultCode resultCode) {
        // 调用父类构造方法，将结果码传入以初始化异常信息
        super(resultCode);
    }
}
