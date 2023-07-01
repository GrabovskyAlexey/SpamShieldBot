package ru.grabovsky.spamshieldbot.handlers.interfaces

import org.telegram.telegrambots.meta.api.methods.PartialBotApiMethod
import org.telegram.telegrambots.meta.api.objects.Update

interface TelegramBotHandler {
    fun handle(update: Update) : PartialBotApiMethod<*>;
}