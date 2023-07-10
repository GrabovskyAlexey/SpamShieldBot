package ru.grabovsky.spamshieldbot.matchers

import opennlp.tools.stemmer.snowball.SnowballStemmer
import opennlp.tools.stemmer.snowball.SnowballStemmer.ALGORITHM
import ru.grabovsky.spamshieldbot.matchers.interfaces.Matcher
import ru.grabovsky.spamshieldbot.rules.RuleCondition
import java.util.regex.Pattern

class WordMatcher : Matcher {

    companion object {
        private val CYRILLIC_PATTERN = Pattern.compile("^.*\\p{InCyrillic}+.*$")
        private val CYRILLIC_STEMMER = SnowballStemmer(ALGORITHM.RUSSIAN)
        private val ENGLISH_STEMMER = SnowballStemmer(ALGORITHM.ENGLISH)
    }

    override fun match(pattern: String, text: String, condition: RuleCondition): Boolean {
        return when (condition) {
            RuleCondition.CONTAINS -> text.contains(pattern, true);
            RuleCondition.NOT_CONTAINS -> !text.contains(pattern, true);
            RuleCondition.SAME -> sameWord(pattern, text);
            RuleCondition.NOT_SAME -> !sameWord(pattern, text);
            else -> false
        }

    }

    private fun sameWord(pattern: String, text: String): Boolean {
        val words = text.split(" ");
        val stemmer = if (isCyrillic(pattern)) CYRILLIC_STEMMER else ENGLISH_STEMMER
        val stemmedPattern = stemmer.stem(pattern)
        for(word in words) {
           if (stemmer.stem(word).equals(stemmedPattern)) return true
        }
        return false
    }

    private fun isCyrillic(pattern: String) = pattern.matches(CYRILLIC_PATTERN.toRegex())
}