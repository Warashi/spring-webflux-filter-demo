package com.example.demo

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Mono

@RestController
class DemoController {
    @GetMapping("/")
    fun demo(): Mono<String> {
        return Mono.just("hello")
            .flatMap {s -> Mono.subscriberContext()
                .map { ctx -> "$s ${ctx.getOrDefault("ua", "world")}"}}
    }
}
