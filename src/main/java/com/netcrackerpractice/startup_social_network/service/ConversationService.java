package com.netcrackerpractice.startup_social_network.service;

import com.netcrackerpractice.startup_social_network.entity.Conversation;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ConversationService {
    List<Conversation> getUserConversationsByUserId(UUID userId);

    Optional<Conversation> getConversationByUsersIds(UUID yourId, UUID otherId);

    void addConversation(UUID yourId, UUID otherId);

    Optional<Conversation> findConversationById(UUID conversationId);
}