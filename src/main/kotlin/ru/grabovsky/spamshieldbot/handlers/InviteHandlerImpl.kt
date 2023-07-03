package ru.grabovsky.spamshieldbot.handlers

import org.springframework.stereotype.Component
import org.telegram.telegrambots.meta.api.methods.PartialBotApiMethod
import org.telegram.telegrambots.meta.api.objects.ChatMemberUpdated
import org.telegram.telegrambots.meta.api.objects.chatmember.ChatMemberAdministrator
import org.telegram.telegrambots.meta.api.objects.chatmember.ChatMemberBanned
import org.telegram.telegrambots.meta.api.objects.chatmember.ChatMemberLeft
import org.telegram.telegrambots.meta.api.objects.chatmember.ChatMemberMember
import ru.grabovsky.spamshieldbot.entity.Chat
import ru.grabovsky.spamshieldbot.exceptions.NotEnoughPermissionException
import ru.grabovsky.spamshieldbot.handlers.interfaces.InviteHandler
import ru.grabovsky.spamshieldbot.services.interfaces.ChatService
import kotlin.jvm.Throws

@Component
class InviteHandlerImpl(
    private val chatService: ChatService
) : InviteHandler {
    override fun handle(invite: ChatMemberUpdated): PartialBotApiMethod<*>? {
        return when (invite.newChatMember) {
            is ChatMemberMember -> inviteInGroup(invite)
            is ChatMemberAdministrator -> processChanelAdministratorRequest(invite)
            is ChatMemberBanned, is ChatMemberLeft -> removeFromChat(invite)
            else -> null
        }
    }

    private fun inviteInGroup(invite: ChatMemberUpdated): PartialBotApiMethod<*>? {
        chatService.createChat(invite)
        return null
    }

    private fun processChanelAdministratorRequest(invite: ChatMemberUpdated): PartialBotApiMethod<*>? {
        if(chatService.getChatByChatId(invite.chat.id).isPresent) {
            updatePermission(invite, chatService.getChatByChatId(invite.chat.id).get())
            return null;
        }
        try {
            checkChanelPermission(invite.newChatMember as ChatMemberAdministrator, invite.chat.id)
            chatService.createChat(invite)
        } catch (e: NotEnoughPermissionException) {
            chatService.createChat(invite, false)
            throw e
        }
        return null
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

    private fun removeFromChat(invite: ChatMemberUpdated): PartialBotApiMethod<*>? {
        val chat = chatService.getChatByChatId(invite.chat.id);
        if (chat.isPresent) {
            chatService.removeFromChat(chat.get())
        }
        return null
    }

    @Throws(NotEnoughPermissionException::class)
    private fun checkChanelPermission(admin: ChatMemberAdministrator, chatId: Long){
        if(!admin.canDeleteMessages && !admin.canRestrictMembers) {
            throw NotEnoughPermissionException("Боту необходимы права на удаление сообщений", chatId)
        }
    }
}