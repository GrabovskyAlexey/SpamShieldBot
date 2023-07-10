package ru.grabovsky.spamshieldbot.handlers

import org.springframework.stereotype.Component
import org.telegram.telegrambots.meta.api.objects.Message
import ru.grabovsky.spamshieldbot.bot.MessageWrapper
import ru.grabovsky.spamshieldbot.handlers.interfaces.CommandHandler
import ru.grabovsky.spamshieldbot.handlers.interfaces.MessageHandler
import ru.grabovsky.spamshieldbot.services.interfaces.RuleService

@Component
class MessageHandlerImpl(
    private val commandHandler: CommandHandler,
    private val ruleService: RuleService
) : MessageHandler {
    override fun handle(message: Message): MessageWrapper {
        return when {
            message.isCommand -> commandHandler.handle(message)
            message.hasText() -> ruleService.applyRules(message)
            else -> MessageWrapper()
        }
//        if (message.isCommand) return commandHandler.handle(message)
//
//        return SendMessage.builder()
//            .chatId(message.chatId)
//            .text("Ваше сообщение: ${message.text}")
//            .build()
    }
}