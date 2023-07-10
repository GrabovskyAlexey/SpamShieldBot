package ru.grabovsky.spamshieldbot.services

import org.springframework.stereotype.Service
import org.telegram.telegrambots.meta.api.methods.groupadministration.BanChatMember
import org.telegram.telegrambots.meta.api.methods.updatingmessages.DeleteMessage
import org.telegram.telegrambots.meta.api.objects.Message
import ru.grabovsky.spamshieldbot.bot.MessageWrapper
import ru.grabovsky.spamshieldbot.repositories.RuleRepository
import ru.grabovsky.spamshieldbot.rules.RuleEngine
import ru.grabovsky.spamshieldbot.services.interfaces.AdminService
import ru.grabovsky.spamshieldbot.services.interfaces.BanService
import ru.grabovsky.spamshieldbot.services.interfaces.ChatService
import ru.grabovsky.spamshieldbot.services.interfaces.RuleService

@Service
class RuleServiceImpl(
    private val ruleRepository: RuleRepository,
    private val adminService: AdminService,
    private val chatService: ChatService,
    private val banService: BanService
) : RuleService {

    override fun applyRules(message: Message): MessageWrapper {
        val chat = chatService.getChatByChatId(message.chatId)
        if(!chat.isPresent) MessageWrapper()
        if (adminService.isAdminInChat(message.from, chat.get())) MessageWrapper()
        val rules = ruleRepository.findAllByChatId(message.chatId)
        val applyRules = RuleEngine.applyRules(rules, message) ?: return MessageWrapper()
        banService.banUserAndMessage(applyRules, chat.get())
        val deleteMessage = DeleteMessage.builder()
            .chatId(applyRules.message.chatId)
            .messageId(applyRules.message.messageId)
            .build()
        val banChatMember = BanChatMember.builder()
            .chatId(message.chatId)
            .userId(message.from.id)
            .build()
        return MessageWrapper(delete = deleteMessage, userBanned = banChatMember);
    }
}

