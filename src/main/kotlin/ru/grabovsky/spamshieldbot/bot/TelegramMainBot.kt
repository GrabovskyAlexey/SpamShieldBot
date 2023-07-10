package ru.grabovsky.spamshieldbot.bot

import org.springframework.stereotype.Component
import org.telegram.telegrambots.bots.TelegramLongPollingBot
import org.telegram.telegrambots.meta.api.methods.send.SendMessage
import org.telegram.telegrambots.meta.api.objects.Update
import org.telegram.telegrambots.meta.api.objects.chatmember.ChatMemberLeft
import ru.grabovsky.spamshieldbot.config.BotConfig
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
            val handle = handler.handle(update)
            if(handle.isNotEmpty()) {
                handle.delete?.let { execute(it) }
                handle.message?.let { execute(it) }
                handle.userBanned?.let { execute(it) }
            }
        } catch (e: TelegramBotException) {
            sendErrorMessage(e)
        }
    }

    private fun sendErrorMessage(e: TelegramBotException) {
        execute(SendMessage.builder()
            .text(e.message)
            .chatId(e.chatId)
            .build()
        )
    }

    private fun removeMemberAndDeleteMessage() {
        ChatMemberLeft.builder()
//            .user()
            .build()
    }
}