package com.netcrackerpractice.startup_social_network.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.netcrackerpractice.startup_social_network.entity.enums.StartupRoleEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.UUID;

//@Builder
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "Startups_Roles")
public class StartupRole {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator")
    private UUID id;

    @Column(name = "role_name")
    @Enumerated(value = EnumType.STRING)
    private StartupRoleEnum roleName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_startup")
    @JsonIgnoreProperties(value = {"startupRoles",
            "account", "startupResumes",
            "startupRoles", "startupInvestments"}, allowSetters = true)
    private Startup startup;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_account")
    @JsonIgnoreProperties(value = {"startupRoles", "firstName", "lastName"
            , "user", "birthday", "aboutMe", "idImage", "nonBlock",
            "resumes", "yourContact", "otherContact", "yourConversations", "otherConversations",
            "startups", "startupRoles", "favorites", "educations", "workExperiences"}, allowSetters = true)
    private Account account;

    @Override
    public String toString() {
        return "StartupRole{" +
                "id=" + id +
                ", roleName=" + roleName +
                ", startup=" + startup +
                ", account=" + account +
                '}';
    }
}
