package ru.grabovsky.spamshieldbot.services

import org.springframework.stereotype.Service
import org.telegram.telegrambots.meta.api.objects.ChatMemberUpdated
import org.telegram.telegrambots.meta.api.objects.Message
import ru.grabovsky.spamshieldbot.bot.ChanelTypes
import ru.grabovsky.spamshieldbot.entity.Admin
import ru.grabovsky.spamshieldbot.entity.Chat
import ru.grabovsky.spamshieldbot.repositories.ChatRepository
import ru.grabovsky.spamshieldbot.services.interfaces.AdminService
import ru.grabovsky.spamshieldbot.services.interfaces.ChatService
import java.util.*

@Service
class ChatServiceImpl(
    private val chatRepository: ChatRepository,
    private val adminService: AdminService
): ChatService {

    override fun getAdminsByChanelId(id: Long): MutableSet<Admin> {
        val chat = chatRepository.findById(id).orElse(Chat());
        return chat.admins
    }

    override fun checkExistChat(id: Long) = chatRepository.existsChatByChatId(id)
    override fun createChat(message: Message) {
        if (message.chat.isUserChat) {

        }


//        val chat = Chat()
//        chat.chatId = message.chatId
//        chat.chatName = message.chat.userName;
//        chatRepository.save(chat)
//        adminService.addAdminToChat(chat, message)
    }

    override fun createChat(invite: ChatMemberUpdated, isActive: Boolean) {
        val chat = Chat()
        chat.chatId = invite.chat.id
        chat.type = ChanelTypes.findByType(invite.chat.type) ?: ChanelTypes.OTHER
        chat.chatName = invite.chat.title
        chat.isActive = isActive
        val admin = adminService.getAdminByUserId(invite.from.id)
        if(admin.id == null) {
            admin.userId = invite.from.id
            admin.username = invite.from.userName
            adminService.save(admin)
        }
        chat.admins.add(admin)
        chatRepository.save(chat)
    }
    override fun getChatByChatId(id: Long): Optional<Chat> {
        return chatRepository.findChatByChatId(id)
    }

    override fun save(chat: Chat) {
        chatRepository.save(chat)
    }

    override fun removeFromChat(chat: Chat) {
        chatRepository.delete(chat)
    }
}