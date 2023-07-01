package ru.grabovsky.spamshieldbot.bot

enum class BotCommands(private val command: String) {
    START("/start");

    companion object {
        fun findByCommand(command: String): BotCommands? {
            return values().find { it -> it.command == command }
        }
    }

}