package ru.grabovsky.spamshieldbot.matchers

import ru.grabovsky.spamshieldbot.matchers.interfaces.Matcher

class WordMatcher : Matcher {
    override fun match(pattern: String, text: String) = text.contains(pattern, true)
}