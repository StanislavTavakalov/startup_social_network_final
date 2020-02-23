package com.netcrackerpractice.startup_social_network.dto;

import lombok.*;

import java.util.UUID;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ResumeStartupDTO {
    private UUID id;
    private String status;
    private UUID startupId;
    private String startupName;
    private String idea;
    private String aboutProject;
    private String businessPlan;
    private int sumOfInvestment;
}

