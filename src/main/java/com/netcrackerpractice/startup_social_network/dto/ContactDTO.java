package com.netcrackerpractice.startup_social_network.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ContactDTO {
    @NotNull
    private UUID yourId;
    @NotNull
    private UUID otherId;
}
