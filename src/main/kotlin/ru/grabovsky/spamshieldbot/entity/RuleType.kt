package ru.grabovsky.spamshieldbot.entity

import ru.grabovsky.spamshieldbot.matchers.RegExpMatcher
import ru.grabovsky.spamshieldbot.matchers.WordMatcher
import ru.grabovsky.spamshieldbot.matchers.interfaces.Matcher

enum class RuleType(val matcher: Matcher) {
    REGEXP(RegExpMatcher()), WORD(WordMatcher());
}