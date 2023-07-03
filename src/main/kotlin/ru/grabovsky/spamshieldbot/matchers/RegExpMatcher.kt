package ru.grabovsky.spamshieldbot.matchers

import ru.grabovsky.spamshieldbot.matchers.interfaces.Matcher
import java.util.regex.Pattern

class RegExpMatcher: Matcher {
    override fun match(pattern : String, text: String) = Pattern.compile(pattern).matcher(text).find()
}