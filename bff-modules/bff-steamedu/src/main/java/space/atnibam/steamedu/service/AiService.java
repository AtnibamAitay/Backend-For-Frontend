package space.atnibam.steamedu.service;

import space.atnibam.api.ai.model.dto.MessageDTO;

import java.util.List;

/**
 * @InterfaceName: AiService
 * @Description: AI服务接口
 * @Author: AtnibamAitay
 * @CreateTime: 2024-03-06 15:48
 **/
public interface AiService {
    /**
     * 使用指定的GPT模型和消息列表与GPT进行交互
     *
     * @param messagesDTO 消息列表
     * @return 交互结果
     */
    String generateText(List<MessageDTO> messagesDTO);
}
