package com.example.demo

import org.springframework.http.HttpHeaders.USER_AGENT
import org.springframework.stereotype.Component
import org.springframework.web.server.ServerWebExchange
import org.springframework.web.server.WebFilter
import org.springframework.web.server.WebFilterChain
import reactor.core.publisher.Mono

@Component
class DemoFilter : WebFilter {
    override fun filter(exchange: ServerWebExchange, chain: WebFilterChain): Mono<Void> {
        return chain.filter(exchange).subscriberContext { ctx ->
            val ua = exchange.request.headers[USER_AGENT]?.joinToString() ?: "(null)"
            ctx.put("ua", ua)
        }
    }
}
