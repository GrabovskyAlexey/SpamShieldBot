package ru.grabovsky.spamshieldbot.bot

enum class ChanelTypes(val type: String) {
    PRIVATE("private"),
    GROUP("group"),
    SUPERGROUP("supergroup"),
    CHANNEL("channel"),
    OTHER("other");

    companion object {
        fun findByType(type: String): ChanelTypes? {
            return ChanelTypes.values().find { it -> it.type == type }
        }
    }
}