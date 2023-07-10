package ru.grabovsky.spamshieldbot.exceptions

open class TelegramBotException(override val message: String, open val chatId: Long): RuntimeException(message) {
}