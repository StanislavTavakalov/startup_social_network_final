package com.netcrackerpractice.startup_social_network.dto;

import com.netcrackerpractice.startup_social_network.entity.Account;
import lombok.*;

import java.util.Set;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ResumeDTO {
    private UUID id;
    private String info;
    private Set<SkillDTO> resumeSkills;
    private BusinessRoleDTO businessRole;
    //private DetailAccountDTO account;
    private Account account;

    @Override
    public String toString() {
        return "ResumeDTO{" +
                "id=" + id +
                ", info='" + info + '\'' +
                ", resumeSkills=" + resumeSkills +
                ", businessRole=" + businessRole +
                ", account=" + account +
                '}';
    }
}
