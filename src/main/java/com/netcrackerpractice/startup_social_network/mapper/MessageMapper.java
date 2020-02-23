package com.netcrackerpractice.startup_social_network.mapper;

import com.netcrackerpractice.startup_social_network.dto.MessageDTO;
import com.netcrackerpractice.startup_social_network.entity.Message;
import com.netcrackerpractice.startup_social_network.exception.AccountNotFoundException;
import com.netcrackerpractice.startup_social_network.exception.ConversationNotFoundException;
import com.netcrackerpractice.startup_social_network.service.AccountService;
import com.netcrackerpractice.startup_social_network.service.ConversationService;
import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Mapper(componentModel = "spring")
public abstract class MessageMapper {
    @Autowired
    private AccountService accountService;
    @Autowired
    private ConversationService conversationService;

    public Message messageDTOtoMessage(MessageDTO messageDTO) {
        return Message.builder()
                .id(UUID.randomUUID())
                .body(messageDTO.getMsg())
                .receiver(accountService.findAccountById(
                        messageDTO.getReceiverId()).orElseThrow(
                        () -> new AccountNotFoundException("Account with UUID: " + messageDTO.getReceiverId() + " not found")))
                .sender(accountService.findAccountById(
                        messageDTO.getSenderId()).orElseThrow(
                        () -> new AccountNotFoundException("Account with UUID: " + messageDTO.getReceiverId() + " not found")))
                .conversation(conversationService.findConversationById(
                        messageDTO.getConversationId()).orElseThrow(
                        () -> new ConversationNotFoundException("Conversation with UUID: " + messageDTO.getReceiverId() + " not found")))
                .creationDate(messageDTO.getCreationDate())
                .build();
    }

    public MessageDTO messageToMessageDTO(Message message) {
        return MessageDTO.builder()
                .conversationId(message.getConversation().getId())
                .creationDate(message.getCreationDate())
                .msg(message.getBody())
                .receiverId(message.getReceiver().getId())
                .senderId(message.getSender().getId())
                .build();
    }

    public abstract List<MessageDTO> messageToMessageDTO(Collection<Message> messages);
}
