package ru.grabovsky.spamshieldbot.handlers.interfaces

import org.telegram.telegrambots.meta.api.objects.ChatMemberUpdated
import ru.grabovsky.spamshieldbot.bot.MessageWrapper

interface InviteHandler {
    fun handle(invite: ChatMemberUpdated): MessageWrapper
}