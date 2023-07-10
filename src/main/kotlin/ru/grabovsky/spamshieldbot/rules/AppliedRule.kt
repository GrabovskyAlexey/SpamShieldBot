package ru.grabovsky.spamshieldbot.rules

import org.telegram.telegrambots.meta.api.objects.Message
import ru.grabovsky.spamshieldbot.entity.Rule

data class AppliedRule(
    var message: Message,
    var rule: Rule
) {


}