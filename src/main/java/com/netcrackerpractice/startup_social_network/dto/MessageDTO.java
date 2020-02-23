package com.netcrackerpractice.startup_social_network.dto;

import com.fasterxml.jackson.annotation.JsonView;
import com.netcrackerpractice.startup_social_network.view.View;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonView(View.ConversationInfo.class)
public class MessageDTO {
    private UUID conversationId;
    private UUID senderId;
    private UUID receiverId;
    private String msg;
    private Timestamp creationDate;
}
