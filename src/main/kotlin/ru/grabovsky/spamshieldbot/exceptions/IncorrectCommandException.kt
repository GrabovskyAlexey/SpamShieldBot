package ru.grabovsky.spamshieldbot.exceptions

class IncorrectCommandException(override val message: String, override val chatId: Long): TelegramBotException(message, chatId) {
}