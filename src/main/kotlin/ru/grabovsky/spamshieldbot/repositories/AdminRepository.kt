package ru.grabovsky.spamshieldbot.repositories;

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import ru.grabovsky.spamshieldbot.entity.Admin
import ru.grabovsky.spamshieldbot.entity.Chat
import java.util.*

@Repository
interface AdminRepository : JpaRepository<Admin, Long> {
    fun findAdminByUserId(id: Long): Optional<Admin>
    fun existsAdminByChatsContainsAndUserId(chats: Chat, userId: Long): Boolean
}