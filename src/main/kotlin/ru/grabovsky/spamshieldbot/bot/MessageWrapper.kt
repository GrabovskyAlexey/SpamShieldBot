package ru.grabovsky.spamshieldbot.bot

import org.telegram.telegrambots.meta.api.methods.botapimethods.BotApiMethodBoolean
import org.telegram.telegrambots.meta.api.methods.send.SendMessage
import org.telegram.telegrambots.meta.api.methods.updatingmessages.DeleteMessage

class MessageWrapper(
    val message: SendMessage? = null,
    val delete: DeleteMessage? = null,
    val userBanned: BotApiMethodBoolean? = null
    ) {

    fun isNotEmpty() = message != null || delete != null || userBanned != null
}