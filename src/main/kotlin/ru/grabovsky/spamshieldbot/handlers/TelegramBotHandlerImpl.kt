package ru.grabovsky.spamshieldbot.handlers

import org.springframework.stereotype.Component
import org.telegram.telegrambots.meta.api.methods.PartialBotApiMethod
import org.telegram.telegrambots.meta.api.methods.send.SendMessage
import org.telegram.telegrambots.meta.api.objects.Update
import ru.grabovsky.spamshieldbot.bot.BotCommands
import ru.grabovsky.spamshieldbot.exceptions.IncorrectCommandException
import ru.grabovsky.spamshieldbot.handlers.interfaces.*
import ru.grabovsky.spamshieldbot.utils.getLogger

@Component
class TelegramBotHandlerImpl(
    private val messageHandler: MessageHandler,
    private val inviteHandler: InviteHandler,
    private val callbackHandler: CallbackHandler
)
    : TelegramBotHandler {

    private val log = getLogger(javaClass)

    override fun handle(update: Update): PartialBotApiMethod<*>?{
        when {
            update.hasMyChatMember() -> inviteHandler.handle(update.myChatMember)
            update.hasCallbackQuery() -> callbackHandler.handle(update.callbackQuery)
            update.hasMessage() -> messageHandler.handle(update.message)
        }
//update.
        log.debug("Handle message")
        return null
    }
}