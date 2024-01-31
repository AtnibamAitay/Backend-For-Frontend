package space.atnibam.common.core.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @ClassName: MqMessage
 * @Description: MQ消息实体
 * @Author: AtnibamAitay
 * @CreateTime: 2024-01-30 17:13
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MqMessage implements Serializable {

    private static final long serialVersionUID = 1L;
    private String messageKey;
    private String messageBody;

    public MqMessage(String messageBody) {
        this.messageBody = messageBody;
    }
}
