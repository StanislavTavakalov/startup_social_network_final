package com.netcrackerpractice.startup_social_network.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Size;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class EducationDTO {
    private UUID id;

    @Size(max=255, message="Institution place should not be greater than 255 characters")
    private String institution;

    @Size(min=1918, max=2018, message="Completion year must be between 1918 and 2018")
    private int completionYear;
}