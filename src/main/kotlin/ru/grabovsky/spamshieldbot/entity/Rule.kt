package ru.grabovsky.spamshieldbot.entity

import jakarta.persistence.*
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import ru.grabovsky.spamshieldbot.rules.RuleAction
import ru.grabovsky.spamshieldbot.rules.RuleCondition
import ru.grabovsky.spamshieldbot.rules.RuleType
import java.time.Instant

@Entity
@Table(name = "rules")
data class Rule(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) var id: Long? = null
) {

    @Column(name = "type", nullable = false)
    @Enumerated(EnumType.STRING)
    lateinit var type: RuleType;

    @Column(name = "action", nullable = false)
    @Enumerated(EnumType.STRING)
    lateinit var action: RuleAction;

    @Column(name = "condition", nullable = false)
    @Enumerated(EnumType.STRING)
    lateinit var condition: RuleCondition;

    @Column(name = "property", nullable = false)
    lateinit var property: String;

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