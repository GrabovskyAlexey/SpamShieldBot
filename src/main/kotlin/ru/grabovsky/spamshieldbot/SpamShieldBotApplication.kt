package ru.grabovsky.spamshieldbot

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class SpamShieldBotApplication

fun main(args: Array<String>) {
	runApplication<SpamShieldBotApplication>(*args)
}
