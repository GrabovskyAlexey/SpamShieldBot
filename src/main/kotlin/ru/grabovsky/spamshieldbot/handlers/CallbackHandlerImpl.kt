package ru.grabovsky.spamshieldbot.handlers

import org.springframework.stereotype.Component
import org.telegram.telegrambots.meta.api.methods.PartialBotApiMethod
import org.telegram.telegrambots.meta.api.objects.CallbackQuery
import ru.grabovsky.spamshieldbot.handlers.interfaces.CallbackHandler

@Component
class CallbackHandlerImpl: CallbackHandler {
    override fun handle(callback: CallbackQuery): PartialBotApiMethod<*>? {
        TODO("Not yet implemented")
    }
}