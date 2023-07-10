package ru.grabovsky.spamshieldbot.matchers.interfaces

import ru.grabovsky.spamshieldbot.rules.RuleCondition

interface Matcher {
    fun match(pattern : String, text: String, condition: RuleCondition): Boolean
}