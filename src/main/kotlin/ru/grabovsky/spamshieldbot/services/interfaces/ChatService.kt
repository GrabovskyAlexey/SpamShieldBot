package ru.grabovsky.spamshieldbot.services.interfaces

import org.telegram.telegrambots.meta.api.objects.ChatMemberUpdated
import org.telegram.telegrambots.meta.api.objects.Message
import ru.grabovsky.spamshieldbot.entity.Admin
import ru.grabovsky.spamshieldbot.entity.Chat
import java.util.*

interface ChatService {
    fun getAdminsByChanelId(id: Long): MutableSet<Admin>
    fun checkExistChat(id: Long): Boolean
    fun createChat(message: Message)
    fun getChatByChatId(id: Long) : Optional<Chat>
    fun save(chat: Chat)
    fun createChat(invite: ChatMemberUpdated, isActive: Boolean = true)
    fun removeFromChat(chat: Chat)
}