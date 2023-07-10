package ru.grabovsky.spamshieldbot.handlers.interfaces

import org.telegram.telegrambots.meta.api.objects.Update
import ru.grabovsky.spamshieldbot.bot.MessageWrapper

interface TelegramBotHandler {
    fun handle(update: Update): MessageWrapper
}