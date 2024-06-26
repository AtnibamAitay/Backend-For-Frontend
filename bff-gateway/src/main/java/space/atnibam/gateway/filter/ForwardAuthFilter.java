package space.atnibam.gateway.filter;

import cn.dev33.satoken.same.SaSameUtil;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * @ClassName: ForwardAuthFilter
 * @Description: 全局过滤器，为请求添加 Same-Token
 * @Author: AtnibamAitay
 * @CreateTime: 2024-01-31 00:17
 **/
@Component
public class ForwardAuthFilter implements GlobalFilter {

    /**
     * 过滤方法，用于修改请求头信息，向其中添加 Same-Token 参数，并将修改后的请求传递给下一过滤链
     *
     * @param exchange ServerWebExchange 对象，表示当前的请求和响应
     * @param chain    GatewayFilterChain 对象，代表过滤链
     * @return Mono<Void> 类型的对象，表示过滤操作完成后的行为
     */
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        // 创建新的请求，其内容基于当前请求，并追加 Same-Token 参数
        ServerHttpRequest newRequest = exchange
                .getRequest()
                .mutate()
                .header(SaSameUtil.SAME_TOKEN, SaSameUtil.getToken())
                .build();

        // 使用新的请求创建新的交换对象
        ServerWebExchange newExchange = exchange.mutate().request(newRequest).build();

        // 将新的交换对象传递给过滤链进行进一步处理
        return chain.filter(newExchange);
    }
}