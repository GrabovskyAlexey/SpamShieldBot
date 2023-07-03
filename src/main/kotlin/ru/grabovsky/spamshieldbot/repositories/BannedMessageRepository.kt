package ru.grabovsky.spamshieldbot.repositories;

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.JpaSpecificationExecutor
import org.springframework.stereotype.Repository
import ru.grabovsky.spamshieldbot.entity.BannedMessage

@Repository
interface BannedMessageRepository : JpaRepository<BannedMessage, Long> {
}