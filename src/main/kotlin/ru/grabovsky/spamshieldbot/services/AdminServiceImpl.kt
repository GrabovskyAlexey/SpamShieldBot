package ru.grabovsky.spamshieldbot.services

import org.springframework.stereotype.Service

import org.telegram.telegrambots.meta.api.objects.Message
import org.telegram.telegrambots.meta.api.objects.User
import ru.grabovsky.spamshieldbot.entity.Admin
import ru.grabovsky.spamshieldbot.entity.Chat
import ru.grabovsky.spamshieldbot.repositories.AdminRepository
import ru.grabovsky.spamshieldbot.services.interfaces.AdminService

@Service
class AdminServiceImpl(
    private val adminRepository: AdminRepository
) : AdminService{

    override fun getAdminByUserId(id: Long): Admin {
        return adminRepository.findAdminByUserId(id).orElse(Admin())
    }

    override fun isAdminInChat(user: User, chat: Chat): Boolean {
        return adminRepository.existsAdminByChatsContainsAndUserId(chat, user.id)
    }

    override fun addAdminToChat(chat: Chat, message: Message) {
        val admin = Admin()
        admin.userId = message.from.id
        admin.username = message.from.userName
        adminRepository.save(admin)
    }

    override fun save(admin: Admin) {
        adminRepository.save(admin)
    }
}