package space.atnibam.steamedu.service.impl;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import space.atnibam.api.ai.RemoteAiService;
import space.atnibam.api.ai.model.dto.MessageDTO;
import space.atnibam.steamedu.service.AiService;

import javax.annotation.Resource;
import java.util.List;

/**
 * @ClassName: AiServiceImpl
 * @Description: AI服务实现类
 * @Author: AtnibamAitay
 * @CreateTime: 2024-03-06 15:48
 **/
@Service
public class AiServiceImpl implements AiService {
    @Resource
    private RemoteAiService remoteAiService;


    /**
     * 使用指定的GPT模型和消息列表与GPT进行交互
     *
     * @param messagesDTO 消息列表
     * @return 交互结果
     */
    @Override
    public String generateText(List<MessageDTO> messagesDTO) {
        ResponseEntity<String> response = remoteAiService.generateTextWithGpt("gpt-3.5-turbo", messagesDTO);

        // 从ResponseEntity中提取body值
        String body = response.getBody();

        // 确保body不为空，防止空指针异常
        if (body != null) {
            return body;
        } else {
            throw new RuntimeException("Failed to fetch the response body from AI service.");
        }
    }
}