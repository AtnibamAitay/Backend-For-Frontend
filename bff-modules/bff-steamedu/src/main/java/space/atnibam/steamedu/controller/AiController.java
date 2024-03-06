package space.atnibam.steamedu.controller;

import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import space.atnibam.steamedu.service.AiService;

import javax.annotation.Resource;

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
}