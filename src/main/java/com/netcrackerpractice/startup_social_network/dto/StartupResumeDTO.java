package com.netcrackerpractice.startup_social_network.dto;

import com.netcrackerpractice.startup_social_network.entity.Startup;
import lombok.*;

import java.util.UUID;


@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class StartupResumeDTO {
    private UUID id;
    private boolean accepted;
    private ResumeDTO resume;
    private Startup startup;
}
