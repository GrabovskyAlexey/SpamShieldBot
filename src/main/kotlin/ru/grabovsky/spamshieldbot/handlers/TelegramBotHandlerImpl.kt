package ru.grabovsky.spamshieldbot.handlers

import org.springframework.stereotype.Component
import org.telegram.telegrambots.meta.api.objects.Update
import ru.grabovsky.spamshieldbot.bot.MessageWrapper
import ru.grabovsky.spamshieldbot.handlers.interfaces.CallbackHandler
import ru.grabovsky.spamshieldbot.handlers.interfaces.InviteHandler
import ru.grabovsky.spamshieldbot.handlers.interfaces.MessageHandler
import ru.grabovsky.spamshieldbot.handlers.interfaces.TelegramBotHandler
import ru.grabovsky.spamshieldbot.utils.getLogger

@Component
class TelegramBotHandlerImpl(
    private val messageHandler: MessageHandler,
    private val inviteHandler: InviteHandler,
    private val callbackHandler: CallbackHandler
)
    : TelegramBotHandler {

    private val log = getLogger(javaClass)

    override fun handle(update: Update): MessageWrapper  {
        when {
            update.hasMyChatMember() -> inviteHandler.handle(update.myChatMember)
            update.hasCallbackQuery() -> callbackHandler.handle(update.callbackQuery)
            update.hasMessage() -> messageHandler.handle(update.message)
//            update.hasChannelPost() -> messageHandler.handle(update.channelPost)
        }
        log.debug("Handle message")
        return MessageWrapper()
    }
}