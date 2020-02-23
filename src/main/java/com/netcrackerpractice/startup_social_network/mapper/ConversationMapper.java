package com.netcrackerpractice.startup_social_network.mapper;

import com.netcrackerpractice.startup_social_network.dto.ConversationDTO;
import com.netcrackerpractice.startup_social_network.entity.Conversation;
import com.netcrackerpractice.startup_social_network.exception.AccountNotFoundException;
import com.netcrackerpractice.startup_social_network.service.AccountService;
import com.netcrackerpractice.startup_social_network.service.MessageService;
import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Mapper(componentModel = "spring")
public abstract class ConversationMapper {
    @Autowired
    private AccountService accountService;
    @Autowired
    private MessageService messageService;
    @Autowired
    private MessageMapper messageMapper;

    public ConversationDTO conversationToConversationDTO(Conversation conversation) {
        conversation.getMessages().sort(Collections.reverseOrder());
        return ConversationDTO.builder()
                .id(conversation.getId())
                .firstAccount(conversation.getFirstAccount())
                .secondAccount(conversation.getSecondAccount())
                .conversationMessages(messageMapper.messageToMessageDTO(conversation.getMessages()))
                .build();
    }

    public Conversation conversationDTOtoConversation(ConversationDTO conversationDTO) {
        return Conversation.builder()
                .id(conversationDTO.getId())
                .firstAccount(accountService.findAccountById(conversationDTO.getFirstAccount().getId()).orElseThrow(
                        () -> new AccountNotFoundException("Account with UUID" + conversationDTO.getFirstAccount() + "not found")))
                .secondAccount(accountService.findAccountById(conversationDTO.getSecondAccount().getId()).orElseThrow(
                        () -> new AccountNotFoundException("Account with UUID" + conversationDTO.getSecondAccount() + "not found")))
                .messages(messageService.getConversationMessagesById(conversationDTO.getId()))
                .build();
    }

    public abstract List<ConversationDTO> conversationDTOtoConversation(Collection<Conversation> conversations);
}