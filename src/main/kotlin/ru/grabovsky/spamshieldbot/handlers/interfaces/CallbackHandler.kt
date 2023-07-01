package ru.grabovsky.spamshieldbot.handlers.interfaces

import org.telegram.telegrambots.meta.api.objects.CallbackQuery

interface CallbackHandler {
    fun handle(callback: CallbackQuery)
}