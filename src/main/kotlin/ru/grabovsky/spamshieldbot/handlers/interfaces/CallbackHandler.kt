package ru.grabovsky.spamshieldbot.handlers.interfaces

import org.telegram.telegrambots.meta.api.objects.CallbackQuery
import ru.grabovsky.spamshieldbot.bot.MessageWrapper

interface CallbackHandler {
    fun handle(callback: CallbackQuery): MessageWrapper
}