package com.kurz.password_generator

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class PasswordGeneratorApplication

fun main(args: Array<String>) {
	runApplication<PasswordGeneratorApplication>(*args)
}
