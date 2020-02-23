package com.netcrackerpractice.startup_social_network.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "Resumes")
public class Resume {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator")
    private UUID id;

    private String info;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_account")
    @JsonIgnoreProperties(value = "resumes", allowSetters = true)
    private Account account;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_business_role")
    @JsonIgnoreProperties(value = "resumes", allowSetters = true)
    private BusinessRole businessRole;

    @ManyToMany(cascade = {
            CascadeType.MERGE
    })
    @JoinTable(name = "resumes_resume_skills",
            joinColumns = @JoinColumn(name = "resume_id"),
            inverseJoinColumns = @JoinColumn(name = "resumes_skills_id")
    )
    @JsonIgnoreProperties(value = "resume", allowSetters = true)
    private Set<Skill> resumeSkills = new HashSet<>();

    @OneToMany(mappedBy = "resume", cascade = CascadeType.ALL)
    @JsonIgnoreProperties(value = "resume", allowSetters = true)
    private Set<StartupResume> startupResumes;

    public void addSkill(Skill skill) {
        resumeSkills.add(skill);
        skill.getResumeSet().add(this);
    }

    @Override
    public String toString() {
        return "Resume{" +
                "id=" + id +
                ", info='" + info + '\'' +
                ", account=" + account +
                ", businessRole=" + businessRole +
                ", resumeSkills=" + resumeSkills +
                '}';
    }


}
