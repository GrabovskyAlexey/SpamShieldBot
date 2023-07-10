package ru.grabovsky.spamshieldbot.matchers

import ru.grabovsky.spamshieldbot.matchers.interfaces.Matcher
import ru.grabovsky.spamshieldbot.rules.RuleCondition
import java.util.regex.Pattern

class RegExpMatcher: Matcher {
    override fun match(pattern : String, text: String, condition: RuleCondition): Boolean {
        val find = Pattern.compile(pattern).matcher(text).find()
        return if(RuleCondition.CONTAINS == condition) find else !find
    }
}