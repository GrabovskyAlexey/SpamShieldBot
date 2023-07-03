package ru.grabovsky.spamshieldbot.exceptions

import org.telegram.telegrambots.meta.api.objects.Message

class IncorrectCommandException(override val message: String, override val chatId: Long): TelegramBotException(message, chatId) {
}