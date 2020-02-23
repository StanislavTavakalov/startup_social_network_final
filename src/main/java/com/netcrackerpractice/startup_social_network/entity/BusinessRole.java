package com.netcrackerpractice.startup_social_network.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.netcrackerpractice.startup_social_network.entity.enums.BusinessRoleEnum;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "Business_roles")
public class BusinessRole {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator")
    private UUID id;

    @Column(name = "role_name")
    @Enumerated(value = EnumType.STRING)
    private BusinessRoleEnum businessRoleName;

    @OneToMany(mappedBy = "businessRole", cascade = CascadeType.ALL)
    @JsonIgnoreProperties(value = "businessRole", allowSetters = true)
    private List<Resume> resumes;

    @Override
    public String toString() {
        return "BusinessRole{" +
                "id=" + id +
                ", businessRoleName=" + businessRoleName +
                '}';
    }
}
