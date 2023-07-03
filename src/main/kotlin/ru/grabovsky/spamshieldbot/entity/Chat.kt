package ru.grabovsky.spamshieldbot.entity

import jakarta.persistence.*
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import ru.grabovsky.spamshieldbot.bot.ChanelTypes
import java.time.Instant

@Entity
@Table(name = "chats")
data class Chat(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) var id: Long? = null
) {

    @Column(name = "tg_chat_name", nullable = false)
    lateinit var chatName: String

    @Column(name = "tg_chat_id", nullable = false)
    var chatId: Long = 0

    @Column(name = "chat_type", nullable = false)
    @Enumerated(EnumType.STRING)
    lateinit var type: ChanelTypes

    @Column(name = "is_active", nullable = false)
    var isActive: Boolean = true

    @ManyToMany(cascade = [CascadeType.MERGE])
    @JoinTable(
        name = "chats_admins",
        joinColumns = [ JoinColumn(name = "chat_id") ],
        inverseJoinColumns = [ JoinColumn(name = "admin_id") ]
    )
    val admins: MutableSet<Admin> = mutableSetOf();

    @OneToMany(mappedBy = "chat")
    val rules: MutableList<Rule> = mutableListOf();

    @OneToMany(mappedBy = "chat")
    val bannedUsers: MutableList<BannedUser> = mutableListOf();

    @OneToMany(mappedBy = "chat")
    val bannedMessages: MutableList<BannedMessage> = mutableListOf();

    @Column(name = "created_at")
    @CreationTimestamp
    var cratedAt: Instant? = null

    @Column(name = "updated_at")
    @UpdateTimestamp
    var updatedAt: Instant? = null
}
