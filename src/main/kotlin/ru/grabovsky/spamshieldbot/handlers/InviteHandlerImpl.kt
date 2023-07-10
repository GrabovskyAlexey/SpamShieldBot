package ru.grabovsky.spamshieldbot.handlers

import org.springframework.stereotype.Component
import org.telegram.telegrambots.meta.api.objects.ChatMemberUpdated
import org.telegram.telegrambots.meta.api.objects.chatmember.ChatMemberAdministrator
import org.telegram.telegrambots.meta.api.objects.chatmember.ChatMemberBanned
import org.telegram.telegrambots.meta.api.objects.chatmember.ChatMemberLeft
import org.telegram.telegrambots.meta.api.objects.chatmember.ChatMemberMember
import ru.grabovsky.spamshieldbot.bot.MessageWrapper
import ru.grabovsky.spamshieldbot.entity.Chat
import ru.grabovsky.spamshieldbot.exceptions.NotEnoughPermissionException
import ru.grabovsky.spamshieldbot.handlers.interfaces.InviteHandler
import ru.grabovsky.spamshieldbot.services.interfaces.ChatService

@Component
class InviteHandlerImpl(
    private val chatService: ChatService
) : InviteHandler {
    override fun handle(invite: ChatMemberUpdated): MessageWrapper {
        return when (invite.newChatMember) {
            is ChatMemberMember -> inviteInGroup(invite)
            is ChatMemberAdministrator -> processChanelAdministratorRequest(invite)
            is ChatMemberBanned, is ChatMemberLeft -> removeFromChat(invite)
            else -> MessageWrapper()
        }
    }

    private fun inviteInGroup(invite: ChatMemberUpdated): MessageWrapper {
        chatService.createChat(invite)
        return MessageWrapper()
    }

    private fun processChanelAdministratorRequest(invite: ChatMemberUpdated): MessageWrapper {
        if(chatService.getChatByChatId(invite.chat.id).isPresent) {
            updatePermission(invite, chatService.getChatByChatId(invite.chat.id).get())
            return MessageWrapper();
        }
        try {
            checkChanelPermission(invite.newChatMember as ChatMemberAdministrator, invite.chat.id)
            chatService.createChat(invite)
        } catch (e: NotEnoughPermissionException) {
            chatService.createChat(invite, false)
            throw e
        }
        return MessageWrapper()
    }

    private fun updatePermission(invite: ChatMemberUpdated, chat: Chat) {
        if (invite.oldChatMember is ChatMemberAdministrator) {
            try {
                checkChanelPermission(invite.newChatMember as ChatMemberAdministrator, chat.chatId)
                chat.isActive = true
            } catch (e: NotEnoughPermissionException) {
                chat.isActive = false
            } finally {
                chatService.save(chat)
            }
        }
    }

    private fun removeFromChat(invite: ChatMemberUpdated): MessageWrapper {
        val chat = chatService.getChatByChatId(invite.chat.id);
        if (chat.isPresent) {
            chatService.removeFromChat(chat.get())
        }
        return MessageWrapper()
    }

    @Throws(NotEnoughPermissionException::class)
    private fun checkChanelPermission(admin: ChatMemberAdministrator, chatId: Long){
        if(!admin.canDeleteMessages && !admin.canRestrictMembers) {
            throw NotEnoughPermissionException("Боту необходимы права на удаление сообщений", chatId)
        }
    }
}