package ru.grabovsky.spamshieldbot.config

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.telegram.telegrambots.meta.TelegramBotsApi
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession
import ru.grabovsky.spamshieldbot.bot.TelegramMainBot
import ru.grabovsky.spamshieldbot.utils.getLogger

@Configuration
class BotConfig(
    @Value("\${application.bot.token}") val token: String,
    @Value("\${application.bot.name}") val botUsername: String,
) {

    init {
        val log = getLogger(javaClass)
    }

    override fun toString(): String {
        return "BotConfig(token='$token', botUsername='$botUsername')"
    }

    @Bean
    fun telegramBotApi(bot: TelegramMainBot) :TelegramBotsApi{
        val api = TelegramBotsApi(DefaultBotSession::class.java)
        api.registerBot(bot)
        return api
    }

}