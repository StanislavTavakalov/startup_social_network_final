package com.netcrackerpractice.startup_social_network.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.UUID;

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "contacts")
public class Contact {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator")
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_your_account", nullable = false)
    @JsonIgnoreProperties(value = "yourContact", allowSetters = true)
    private Account yourAccount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_contact_account", nullable = false)
    @JsonIgnoreProperties(value = "otherContact", allowSetters = true)
    private Account otherAccount;
}
