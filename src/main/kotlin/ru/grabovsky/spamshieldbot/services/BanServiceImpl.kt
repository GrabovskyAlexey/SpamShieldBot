package ru.grabovsky.spamshieldbot.services

import org.springframework.stereotype.Service
import ru.grabovsky.spamshieldbot.entity.BannedMessage
import ru.grabovsky.spamshieldbot.entity.BannedUser
import ru.grabovsky.spamshieldbot.entity.Chat
import ru.grabovsky.spamshieldbot.repositories.BannedMessageRepository
import ru.grabovsky.spamshieldbot.repositories.BannedUserRepository
import ru.grabovsky.spamshieldbot.rules.AppliedRule
import ru.grabovsky.spamshieldbot.services.interfaces.BanService

@Service
class BanServiceImpl(
    private val bannedMessageRepository: BannedMessageRepository,
    private val bannedUserRepository: BannedUserRepository
) : BanService {
    override fun banUser(rule: AppliedRule, chat: Chat): BannedUser {
        val bannedUser = BannedUser()
        bannedUser.telegramUserId = rule.message.from.id
        bannedUser.username = rule.message.from.userName ?: "undefined"
        bannedUser.rule = rule.rule
        bannedUser.chat = chat
        return bannedUserRepository.save(bannedUser)
    }

    override fun banMessage(rule: AppliedRule, chat: Chat, user: BannedUser) {
        val bannedMessage = BannedMessage()
        bannedMessage.telegramMessageId = rule.message.messageId
        bannedMessage.rule = rule.rule
        bannedMessage.chat = chat
        bannedMessage.text = rule.message.text
        bannedMessage.user = user
        bannedMessageRepository.save(bannedMessage)
    }

    override fun banUserAndMessage(rule: AppliedRule, chat: Chat) {
        val banUser = banUser(rule, chat)
        banMessage(rule, chat, banUser)
    }
}