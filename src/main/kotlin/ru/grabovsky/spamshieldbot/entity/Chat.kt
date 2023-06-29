package ru.grabovsky.spamshieldbot.entity

import jakarta.persistence.*
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import java.time.Instant

@Entity
@Table(name = "chats")
data class Chat(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) var id :Long? = null
) {

    @Column(name = "tg_chat_name", nullable = false)
    lateinit var chatName  : String

    @Column(name = "tg_chat_id", nullable = false)
    var chatId  : Long = 0

    @Column(name = "created_at")
    @CreationTimestamp
    var cratedAt: Instant? = null

    @Column(name = "updated_at")
    @UpdateTimestamp
    var updatedAt: Instant? = null
}
