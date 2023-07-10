package ru.grabovsky.spamshieldbot.services.interfaces

import ru.grabovsky.spamshieldbot.entity.BannedUser
import ru.grabovsky.spamshieldbot.entity.Chat
import ru.grabovsky.spamshieldbot.rules.AppliedRule

interface BanService {
    fun banUser(rule: AppliedRule, chat: Chat): BannedUser
    fun banMessage(rule: AppliedRule, chat: Chat, user: BannedUser)
    fun banUserAndMessage(rule: AppliedRule, chat: Chat)
}