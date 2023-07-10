package ru.grabovsky.spamshieldbot.repositories;

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import ru.grabovsky.spamshieldbot.entity.Chat
import java.util.*

@Repository
interface ChatRepository : JpaRepository<Chat, Long> {
    fun existsChatByChatId(id: Long): Boolean;
    fun findChatByChatId(id: Long): Optional<Chat>;
}