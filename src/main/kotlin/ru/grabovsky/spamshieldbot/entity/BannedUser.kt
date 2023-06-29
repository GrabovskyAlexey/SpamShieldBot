package ru.grabovsky.spamshieldbot.entity

import jakarta.persistence.*
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import java.time.Instant

@Entity
@Table(name = "banned_users")
data class BannedUser(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) var id: Long? = null
) {

    @Column(name = "tg_user_id", nullable = false)
    var telegramUserId: Long = 0

    @ManyToOne
    @JoinColumn(name = "rule_id", nullable = false)
    lateinit var rule: Rule;

    @JoinColumn(name = "username", nullable = false)
    lateinit var username: String;

    @ManyToOne
    @JoinColumn(name = "int_chat_id", nullable = false)
    lateinit var chat: Chat;

    @OneToMany(mappedBy = "user")
    val messages: MutableList<BannedMessage> = mutableListOf();

    @Column(name = "created_at")
    @CreationTimestamp
    var cratedAt: Instant? = null

    @Column(name = "updated_at")
    @UpdateTimestamp
    var updatedAt: Instant? = null
}