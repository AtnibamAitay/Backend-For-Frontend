package space.atnibam.steamedu.config;

import cn.dev33.satoken.context.SaHolder;
import cn.dev33.satoken.filter.SaServletFilter;
import cn.dev33.satoken.same.SaSameUtil;
import cn.dev33.satoken.util.SaResult;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @ClassName: SaTokenConfigure
 * @Description: [Sa-Token 权限认证] 配置类
 * @Author: AtnibamAitay
 * @CreateTime: 2024-01-31 00:17
 **/
@Configuration
public class SaTokenConfigure implements WebMvcConfigurer {
    /**
     * 注册 Sa-Token全局过滤器
     *
     * @return 返回SaReactorFilter对象
     */
    public SaServletFilter getSaServletFilter() {
        return new SaServletFilter()
                .addInclude("/**")
                .addExclude("/favicon.ico")
                .setAuth(obj -> {
                    // 校验 Same-Token 身份凭证
                    String token = SaHolder.getRequest().getHeader(SaSameUtil.SAME_TOKEN);
                    SaSameUtil.checkToken(token);
                })
                .setError(e -> {
                    return SaResult.error(e.getMessage());
                });
    }
}
