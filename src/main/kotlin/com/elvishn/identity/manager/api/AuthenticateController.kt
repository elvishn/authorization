package com.elvishn.identity.manager.api

import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Mono

@RestController
@RequestMapping("/api/v1")
class AuthenticateController {
    @PostMapping
    fun authenticate() = Mono.just("OK")
}
