package ru.grabovsky.spamshieldbot.handlers.interfaces

import org.telegram.telegrambots.meta.api.methods.PartialBotApiMethod
import org.telegram.telegrambots.meta.api.objects.ChatMemberUpdated

interface InviteHandler {
    fun handle(invite: ChatMemberUpdated): PartialBotApiMethod<*>?
}