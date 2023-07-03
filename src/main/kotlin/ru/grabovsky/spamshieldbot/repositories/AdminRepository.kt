package ru.grabovsky.spamshieldbot.repositories;

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import ru.grabovsky.spamshieldbot.entity.Admin
import java.util.Optional

@Repository
interface AdminRepository : JpaRepository<Admin, Long> {
    fun findAdminByUserId(id: Long): Optional<Admin>
}