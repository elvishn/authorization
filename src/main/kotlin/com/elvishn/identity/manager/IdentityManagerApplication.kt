package com.elvishn.identity.manager

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class IdentityManagerApplication

fun main(args: Array<String>) {
	runApplication<com.elvishn.identity.manager.IdentityManagerApplication>(*args)
}
