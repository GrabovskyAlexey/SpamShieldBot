package ru.grabovsky.spamshieldbot.handlers.interfaces

import org.telegram.telegrambots.meta.api.objects.Message
import ru.grabovsky.spamshieldbot.bot.MessageWrapper

interface CommandHandler {
    fun handle(message: Message): MessageWrapper
}