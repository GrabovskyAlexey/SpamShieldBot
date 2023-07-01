package ru.grabovsky.spamshieldbot.handlers

import org.springframework.stereotype.Component
import org.telegram.telegrambots.meta.api.objects.Message
import ru.grabovsky.spamshieldbot.bot.BotCommands
import ru.grabovsky.spamshieldbot.exceptions.IncorrectCommandException
import ru.grabovsky.spamshieldbot.handlers.interfaces.MessageHandler

@Component
class MessageHandlerImpl: MessageHandler {
    override fun handle(message: Message) {
        val text = message.text
        if(text.startsWith("/") && BotCommands.findByCommand(text) == null) {
            throw IncorrectCommandException("Некорректная команда $text", message)
        }
    }
}