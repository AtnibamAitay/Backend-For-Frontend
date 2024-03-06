package space.atnibam.steamedu.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import space.atnibam.api.ai.model.dto.MessageDTO;
import space.atnibam.common.core.domain.R;
import space.atnibam.steamedu.service.AiService;

import javax.annotation.Resource;
import java.util.List;

/**
 * @ClassName: AiController
 * @Description: AI模块
 * @Author: AtnibamAitay
 * @CreateTime: 2024-03-06 15:48
 **/
@Api(tags = "AI模块")
@RestController
@RequestMapping("/api/ai")
public class AiController {
    @Resource
    private AiService aiService;

    /**
     * 处理与GPT模型交互的请求（无流式输出）
     *
     * @param messagesDTO 消息列表
     * @return ResponseEntity 包含交互结果或错误消息
     */
    @ApiOperation("与GPT交互接口")
    @PostMapping("/interact-with-gpt")
    public R generateTextWithGpt(
            @RequestBody List<MessageDTO> messagesDTO) {
        return R.ok(aiService.generateText(messagesDTO));
    }
}