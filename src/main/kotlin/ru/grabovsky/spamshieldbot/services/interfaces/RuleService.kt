package ru.grabovsky.spamshieldbot.services.interfaces

import org.telegram.telegrambots.meta.api.objects.Message
import ru.grabovsky.spamshieldbot.bot.MessageWrapper

interface RuleService {
    fun applyRules(message: Message): MessageWrapper
}