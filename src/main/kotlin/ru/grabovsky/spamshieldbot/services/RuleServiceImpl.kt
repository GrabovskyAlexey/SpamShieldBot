package ru.grabovsky.spamshieldbot.services

import org.springframework.stereotype.Service
import org.telegram.telegrambots.meta.api.objects.Message
import ru.grabovsky.spamshieldbot.repositories.RuleRepository
import ru.grabovsky.spamshieldbot.services.interfaces.RuleService

@Service
class RuleServiceImpl(private val ruleRepository: RuleRepository) : RuleService {

    fun checkMessage(message:Message) {
        val allRules = ruleRepository.findAllByChatId(message.chatId)
        allRules.stream().anyMatch { it -> it.type.matcher.match(it.property, message.text) }
    }

}

