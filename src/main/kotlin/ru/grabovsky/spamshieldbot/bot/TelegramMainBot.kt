package ru.grabovsky.spamshieldbot.bot

import org.springframework.stereotype.Component
import org.telegram.telegrambots.bots.TelegramLongPollingBot
import org.telegram.telegrambots.meta.api.methods.send.SendMessage
import org.telegram.telegrambots.meta.api.objects.Update
import ru.grabovsky.spamshieldbot.config.BotConfig
import ru.grabovsky.spamshieldbot.exceptions.IncorrectCommandException
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
        } catch (e: IncorrectCommandException) {
            incorrectCommand(e)
        }
    }

    fun incorrectCommand(e: IncorrectCommandException) {
        val sendMessage = SendMessage()
        sendMessage.text = e.message
        sendMessage.chatId = e.incoming.chatId.toString()
        execute(sendMessage)
    }
}