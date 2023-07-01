package ru.grabovsky.spamshieldbot.entity

import jakarta.persistence.*
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import java.time.Instant

@Entity
@Table(name = "admins")
data class Admin(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) var id :Long? = null
) {
    @Column(name = "username", nullable = false)
    lateinit var username : String

    @Column(name = "tg_user_id", nullable = false)
    var userId : Long = 0

    @ManyToOne
    @JoinColumn(name = "int_chat_id", nullable = false)
    lateinit var chat: Chat;

    @Column(name = "created_at")
    @CreationTimestamp
    var cratedAt: Instant? = null

    @Column(name = "updated_at")
    @UpdateTimestamp
    var updatedAt: Instant? = null
}
