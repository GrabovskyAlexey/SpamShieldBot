package ru.grabovsky.spamshieldbot.rules

import org.springframework.stereotype.Component
import org.telegram.telegrambots.meta.api.objects.Message
import ru.grabovsky.spamshieldbot.entity.Rule

@Component
class RuleEngine {
    companion object {
        fun applyRules(rules: List<Rule>, message: Message): AppliedRule? {
            for (rule in rules) {
                if(rule.type.matcher.match(rule.property, message.text, rule.condition)) {
                    return AppliedRule(message, rule)
                }
            }
            return null
        }
    }
}