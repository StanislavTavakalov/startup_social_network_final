package com.netcrackerpractice.startup_social_network.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.netcrackerpractice.startup_social_network.dto.ConversationDTO;
import com.netcrackerpractice.startup_social_network.exception.ConversationNotFoundException;
import com.netcrackerpractice.startup_social_network.mapper.ConversationMapper;
import com.netcrackerpractice.startup_social_network.service.ConversationService;
import com.netcrackerpractice.startup_social_network.view.View;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/conversation")
public class ConversationController {
    @Autowired
    private ConversationService conversationService;
    @Autowired
    private ConversationMapper conversationMapper;

    @JsonView(View.ConversationInfo.class)
    @GetMapping("/getConversationInfoByUsersIds")
    public ConversationDTO getConversationInfo(@RequestParam(name = "yourId") UUID yourId, @RequestParam(name = "otherId") UUID otherId) {
        return conversationMapper.conversationToConversationDTO(
                conversationService.getConversationByUsersIds(yourId, otherId).orElseThrow(
                        () -> new ConversationNotFoundException("Conversation not found for users: " + yourId + "and " + otherId)
                ));
    }

    @JsonView(View.ConversationInfo.class)
    @GetMapping("/{userId}")
    public List<ConversationDTO> getConversationsByUserId(@PathVariable(name = "userId") UUID userId) {
        return conversationMapper
                .conversationDTOtoConversation(
                        conversationService.getUserConversationsByUserId(userId)
                );
    }
}