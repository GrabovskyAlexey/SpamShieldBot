package ru.grabovsky.spamshieldbot.services.interfaces


import org.telegram.telegrambots.meta.api.objects.Message
import ru.grabovsky.spamshieldbot.entity.Admin
import ru.grabovsky.spamshieldbot.entity.Chat

interface AdminService {
    fun addAdminToChat(chat: Chat, message: Message)
    fun getAdminByUserId(id: Long): Admin

    fun save(admin: Admin)
}