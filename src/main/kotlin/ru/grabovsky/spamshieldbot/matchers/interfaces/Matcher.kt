package ru.grabovsky.spamshieldbot.matchers.interfaces

interface Matcher {
    fun match(pattern : String, text: String): Boolean
}