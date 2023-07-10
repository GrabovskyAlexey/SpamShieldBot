package ru.grabovsky.spamshieldbot.repositories;

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import ru.grabovsky.spamshieldbot.entity.Rule

@Repository
interface RuleRepository : JpaRepository<Rule, Long> {
    fun findAllByChatId(chatId: Long): List<Rule>
}