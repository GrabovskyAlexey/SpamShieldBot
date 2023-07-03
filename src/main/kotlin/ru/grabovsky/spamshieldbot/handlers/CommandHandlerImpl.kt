package ru.grabovsky.spamshieldbot.handlers

import org.springframework.stereotype.Component
import org.telegram.telegrambots.meta.api.methods.PartialBotApiMethod
import org.telegram.telegrambots.meta.api.methods.send.SendMessage
import org.telegram.telegrambots.meta.api.methods.send.SendMessage.SendMessageBuilder
import org.telegram.telegrambots.meta.api.objects.Message
import ru.grabovsky.spamshieldbot.bot.BotCommands
import ru.grabovsky.spamshieldbot.exceptions.IncorrectCommandException
import ru.grabovsky.spamshieldbot.handlers.interfaces.CommandHandler
import ru.grabovsky.spamshieldbot.services.interfaces.AdminService
import ru.grabovsky.spamshieldbot.services.interfaces.ChatService

@Component
class CommandHandlerImpl (
    private val adminService: AdminService,
    private val chatService: ChatService
) : CommandHandler {
    override fun handle(message: Message): PartialBotApiMethod<*>? {
        when (BotCommands.findByCommand(message.text)) {
            BotCommands.START -> return processStartCommand(message)
            else -> throw IncorrectCommandException("Некорректная команда ${message.text}", message.chatId)
        }
    }

    private fun processStartCommand(message: Message): SendMessage? {
        if(chatService.checkExistChat(message.chatId)) {
            return null
        }
        chatService.createChat(message)

        val reply = SendMessage()
        reply.chatId = message.chatId.toString()
        reply.text = "Hello ${message.from.userName}"
        return reply
    }
}