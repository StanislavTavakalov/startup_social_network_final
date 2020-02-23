package com.netcrackerpractice.startup_social_network.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "investments")
public class Investment {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator")
    private UUID id;

    @NotNull(message = "Investor can't be null")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_investor")
    @JsonIgnoreProperties(value = {"startupInvestments", "birthday", "aboutMe", "imageId"
    , "nonBlock", "resumes", "yourContact", "otherContact", "yourConversations",
            "otherConversations", "startups", "favorites", "educations", "workExperiences", "user", "startupRoles"}, allowSetters = true)
    private Account investor;

    @NotNull(message = "Startup can't be null")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_startup")
    @JsonIgnoreProperties(value = {"startupInvestments", "account", "startupResumes", "startupRoles", "startupName"
            , "idea", "aboutProject", "businessPlan", "sumOfInvestment", "dateOfCreation", "imageId", "compressedImageId", "nonBlock"}, allowSetters = true)
    private Startup startup;

    @NotNull(message = "Sum of investment can'not be null")
    @Max(value = Integer.MAX_VALUE, message = "Sum of investment is too big")
    @JoinColumn(name = "sum_of_investment")
    private Integer sumOfInvestment;
}
