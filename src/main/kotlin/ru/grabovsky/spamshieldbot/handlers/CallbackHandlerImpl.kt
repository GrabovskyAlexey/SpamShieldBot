package ru.grabovsky.spamshieldbot.handlers

import org.springframework.stereotype.Component
import org.telegram.telegrambots.meta.api.objects.CallbackQuery
import ru.grabovsky.spamshieldbot.bot.MessageWrapper
import ru.grabovsky.spamshieldbot.handlers.interfaces.CallbackHandler

@Component
class CallbackHandlerImpl: CallbackHandler {
    override fun handle(callback: CallbackQuery): MessageWrapper {
        TODO("Not yet implemented")
    }
}