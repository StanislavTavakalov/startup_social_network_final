package com.netcrackerpractice.startup_social_network.controller;

import com.netcrackerpractice.startup_social_network.dto.MessageDTO;
import com.netcrackerpractice.startup_social_network.mapper.MessageMapper;
import com.netcrackerpractice.startup_social_network.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/messages")
public class MessageController {
    @Autowired
    private MessageService messageService;
    @Autowired
    private MessageMapper messageMapper;

    @GetMapping("/{conversationId}")
    public List<MessageDTO> getConversationMessages(@PathVariable(name = "conversationId") UUID conversationId) {
        return messageMapper.messageToMessageDTO(messageService.getConversationMessagesById(conversationId));
    }
}
