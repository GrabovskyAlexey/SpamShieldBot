package ru.grabovsky.spamshieldbot.exceptions

import org.telegram.telegrambots.meta.api.objects.Message

open class TelegramBotException(override val message: String, open val chatId: Long): RuntimeException(message) {
}