package com.netcrackerpractice.startup_social_network.dto;

import com.fasterxml.jackson.annotation.JsonView;
import com.netcrackerpractice.startup_social_network.entity.Account;
import com.netcrackerpractice.startup_social_network.view.View;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonView(View.ConversationInfo.class)
public class ConversationDTO {
    private UUID id;
    private Account firstAccount;
    private Account secondAccount;
    private String name;
    private List<MessageDTO> conversationMessages;
}