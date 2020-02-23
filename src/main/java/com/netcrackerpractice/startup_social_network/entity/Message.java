package com.netcrackerpractice.startup_social_network.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.UUID;

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "message")
public class Message implements Comparable<Message> {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator")
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "conversation_id")
    private Conversation conversation;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sender_id")
//    @JsonIgnoreProperties(value = {"user","resumes","yourContact","otherContact",
//            "yourConversations","otherConversations",
//            "startups", "startupRoles","favorites",
//            "educations", "workExperiences", "sendMessages", "receivedMessages"}, allowSetters = true)
    private Account sender;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "receiver_id")
//    @JsonIgnoreProperties(value = {"user","resumes","yourContact","otherContact",
//            "yourConversations","otherConversations",
//            "startups", "startupRoles","favorites",
//            "educations", "workExperiences", "sendMessages", "receivedMessages"}, allowSetters = true)
    private Account receiver;

    private String body;

    @Column(name = "creation_date")
    private Timestamp creationDate;

    @Override
    public int compareTo(Message o) {
        if (getCreationDate() == null || o.getCreationDate() == null) {
            return 0;
        }
        return getCreationDate().compareTo(o.getCreationDate());
    }
}
