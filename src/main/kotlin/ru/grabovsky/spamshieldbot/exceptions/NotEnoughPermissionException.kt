package ru.grabovsky.spamshieldbot.exceptions

class NotEnoughPermissionException(override val message: String, override val chatId: Long): TelegramBotException(message, chatId) {
}