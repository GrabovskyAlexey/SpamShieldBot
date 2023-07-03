package ru.grabovsky.spamshieldbot.exceptions

import org.telegram.telegrambots.meta.api.objects.Message

class NotEnoughPermissionException(override val message: String, override val chatId: Long): TelegramBotException(message, chatId) {
}