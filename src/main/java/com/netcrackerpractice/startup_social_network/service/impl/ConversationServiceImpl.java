package com.netcrackerpractice.startup_social_network.service.impl;

import com.netcrackerpractice.startup_social_network.entity.Account;
import com.netcrackerpractice.startup_social_network.entity.Conversation;
import com.netcrackerpractice.startup_social_network.exception.AccountNotFoundException;
import com.netcrackerpractice.startup_social_network.repository.ConversationRepository;
import com.netcrackerpractice.startup_social_network.service.AccountService;
import com.netcrackerpractice.startup_social_network.service.ConversationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ConversationServiceImpl implements ConversationService {
    @Autowired
    private ConversationRepository conversationRepository;
    @Autowired
    private AccountService accountService;

    @Override
    public List<Conversation> getUserConversationsByUserId(UUID userId) {
        List<Conversation> conversations = conversationRepository.getUserConversationsByUserId(userId);
        conversations.forEach(conversation -> swapAccounts(conversation, userId));

        return conversations;
    }

    @Override
    public Optional<Conversation> getConversationByUsersIds(UUID yourId, UUID otherId) {
        Optional<Conversation> conversationOptional = conversationRepository.getConversationByUsersIds(yourId, otherId);
        if (!conversationOptional.isPresent()) {
            this.addConversation(yourId, otherId);
            return conversationRepository.getConversationByUsersIds(yourId, otherId);
        }
        swapAccounts(conversationOptional.get(), yourId);

        return conversationOptional;
    }

    @Override
    public void addConversation(UUID yourId, UUID otherId) {
        Conversation conversation = Conversation.builder()
                .firstAccount(accountService.findAccountById(yourId).orElseThrow(
                        () -> new AccountNotFoundException("Account with ID: " + yourId + "not found.")))
                .secondAccount(accountService.findAccountById(otherId).orElseThrow(
                        () -> new AccountNotFoundException("Account with ID: " + otherId + "not found.")))
                .messages(new ArrayList<>())
                .build();

        conversationRepository.save(conversation);
    }

    @Override
    public Optional<Conversation> findConversationById(UUID conversationId) {
        return conversationRepository.findConversationById(conversationId);
    }

    private void swapAccounts(Conversation conversation, UUID yourId) {
        if (conversation.getFirstAccount().getId().compareTo(yourId) != 0) {
            Account account = conversation.getFirstAccount();
            conversation.setFirstAccount(conversation.getSecondAccount());
            conversation.setSecondAccount(account);
        }
    }
}