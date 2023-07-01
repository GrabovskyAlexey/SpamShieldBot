package ru.grabovsky.spamshieldbot.handlers.interfaces

import org.telegram.telegrambots.meta.api.objects.Message

interface MessageHandler {
    fun handle(message: Message)
}