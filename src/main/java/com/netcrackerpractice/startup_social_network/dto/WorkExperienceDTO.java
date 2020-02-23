package com.netcrackerpractice.startup_social_network.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Past;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.UUID;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class WorkExperienceDTO {
    private UUID id;

    @Size(max=255, message="Work place should not be greater than 255 characters")
    private String workPlace;


    @Past
    private Date start;


    @PastOrPresent
    private Date finish;

    @Size(max=255, message="Position should not be greater than 255 characters")
    private String position;
}
