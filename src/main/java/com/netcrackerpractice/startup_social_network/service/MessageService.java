package com.netcrackerpractice.startup_social_network.service;

import com.netcrackerpractice.startup_social_network.entity.Message;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface MessageService {
    List<Message> getConversationMessagesById(UUID conversationId);

    Optional<Message> getConversationLastMessageById(UUID conversationId);

    void addMessage(Message message);
}