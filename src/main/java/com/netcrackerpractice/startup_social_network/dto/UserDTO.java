package com.netcrackerpractice.startup_social_network.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserDTO {
    private UUID id;
    private String login;
    private String email;
    private boolean nonBlock;
}
