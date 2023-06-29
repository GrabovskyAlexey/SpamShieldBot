package ru.grabovsky.spamshieldbot.entity

import jakarta.persistence.*
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import java.time.Instant

@Entity
@Table(name = "banned_messages")
data class BannedMessage(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) var id: Long? = null
) {
    @Column(name = "tg_message_id", nullable = false)
    var telegramMessageId: Int = 0

    @Column(name = "text", nullable = false)
    lateinit var text : String;

    @ManyToOne
    @JoinColumn(name = "rule_id", nullable = false)
    lateinit var rule: Rule;

    @ManyToOne
    @JoinColumn(name = "int_chat_id", nullable = false)
    lateinit var chat: Chat;

    @ManyToOne
    @JoinColumn(name = "int_user_id", nullable = false)
    lateinit var user: BannedUser;

    @Column(name = "created_at")
    @CreationTimestamp
    var cratedAt: Instant? = null

    @Column(name = "updated_at")
    @UpdateTimestamp
    var updatedAt: Instant? = null
}