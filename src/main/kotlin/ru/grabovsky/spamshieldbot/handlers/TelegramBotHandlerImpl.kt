package ru.grabovsky.spamshieldbot.handlers

import org.springframework.stereotype.Component
import org.telegram.telegrambots.meta.api.methods.PartialBotApiMethod
import org.telegram.telegrambots.meta.api.methods.send.SendMessage
import org.telegram.telegrambots.meta.api.objects.Update
import ru.grabovsky.spamshieldbot.bot.BotCommands
import ru.grabovsky.spamshieldbot.exceptions.IncorrectCommandException
import ru.grabovsky.spamshieldbot.handlers.interfaces.CallbackHandler
import ru.grabovsky.spamshieldbot.handlers.interfaces.MessageHandler
import ru.grabovsky.spamshieldbot.handlers.interfaces.TelegramBotHandler
import ru.grabovsky.spamshieldbot.utils.getLogger

@Component
class TelegramBotHandlerImpl(
    private val messageHandler: MessageHandler,
    private val callbackHandler: CallbackHandler
)
    : TelegramBotHandler {

    private val log = getLogger(javaClass)

    override fun handle(update: Update): PartialBotApiMethod<*>{
        if(update.hasCallbackQuery()) {
            val callbackQuery = callbackHandler.handle(update.callbackQuery)
        }
        if(update.hasMessage()) {
            val message = messageHandler.handle(update.message)
        }
        log.debug("Handle message")
        return SendMessage()
    }
}