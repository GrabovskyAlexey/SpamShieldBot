package ru.grabovsky.spamshieldbot.bot

import org.springframework.stereotype.Component
import org.telegram.telegrambots.bots.TelegramLongPollingBot
import org.telegram.telegrambots.meta.api.methods.send.SendMessage
import org.telegram.telegrambots.meta.api.methods.updatingmessages.DeleteMessage
import org.telegram.telegrambots.meta.api.methods.updatingmessages.DeleteMessage.DeleteMessageBuilder
import org.telegram.telegrambots.meta.api.objects.Update
import ru.grabovsky.spamshieldbot.config.BotConfig
import ru.grabovsky.spamshieldbot.exceptions.IncorrectCommandException
import ru.grabovsky.spamshieldbot.exceptions.TelegramBotException
import ru.grabovsky.spamshieldbot.handlers.interfaces.TelegramBotHandler
import ru.grabovsky.spamshieldbot.utils.getLogger

@Component
class TelegramMainBot(
    private val config: BotConfig,
    private val handler: TelegramBotHandler
) : TelegramLongPollingBot(config.token) {

    private final val log = getLogger(javaClass)

    init {
        log.debug(config.toString())
    }

    override fun getBotUsername() = config.botUsername

    override fun onUpdateReceived(update: Update) {
        try {
            when (val handle = handler.handle(update)) {
                is SendMessage -> execute(handle)
            }
        } catch (e: TelegramBotException) {
            sendErrorMessage(e)
        }
//        val deleteMessage = DeleteMessage()
//        deleteMessage.chatId = update.message.chatId.toString()
//        deleteMessage.messageId = update.message.messageId
//        execute(deleteMessage)
    }

    private fun sendErrorMessage(e: TelegramBotException) {
        execute(SendMessage.builder()
            .text(e.message)
            .chatId(e.chatId)
            .build()
        )
    }
}