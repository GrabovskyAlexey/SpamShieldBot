package ru.grabovsky.spamshieldbot.handlers.interfaces

import org.telegram.telegrambots.meta.api.methods.PartialBotApiMethod
import org.telegram.telegrambots.meta.api.objects.Message

interface CommandHandler {
    fun handle(message: Message): PartialBotApiMethod<*>?
}