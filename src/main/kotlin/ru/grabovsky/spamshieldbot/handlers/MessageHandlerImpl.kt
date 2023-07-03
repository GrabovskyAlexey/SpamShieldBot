package ru.grabovsky.spamshieldbot.handlers

import org.springframework.stereotype.Component
import org.telegram.telegrambots.meta.api.methods.PartialBotApiMethod
import org.telegram.telegrambots.meta.api.methods.send.SendMessage
import org.telegram.telegrambots.meta.api.objects.Message
import ru.grabovsky.spamshieldbot.bot.BotCommands
import ru.grabovsky.spamshieldbot.exceptions.IncorrectCommandException
import ru.grabovsky.spamshieldbot.handlers.interfaces.CommandHandler
import ru.grabovsky.spamshieldbot.handlers.interfaces.MessageHandler

@Component
class MessageHandlerImpl(
    private val commandHandler: CommandHandler
): MessageHandler {
    override fun handle(message: Message): PartialBotApiMethod<*>? {
        if (message.isCommand) return commandHandler.handle(message)
        return SendMessage.builder()
            .chatId(message.chatId)
            .text("Ваше сообщение: ${message.text}")
            .build()
    }
}