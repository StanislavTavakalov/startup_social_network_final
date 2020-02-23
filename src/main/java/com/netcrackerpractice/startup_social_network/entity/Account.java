package com.netcrackerpractice.startup_social_network.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonView;
import com.netcrackerpractice.startup_social_network.view.View;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.*;

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@EqualsAndHashCode
@ToString
@Table(name = "accounts")
public class Account {
    @JsonView(View.BasicInfo.class)
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator")
    private UUID id;

    @JsonView(View.BasicInfo.class)
    @Column(name = "first_name")
    private String firstName;

    @JsonView(View.BasicInfo.class)
    @Column(name = "last_name")
    private String lastName;

    @JsonView(View.BasicInfo.class)
    private Date birthday;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_user")
    @JsonIgnoreProperties(value = {"account", "token"}, allowSetters = true)
    private User user;

    @Column(name = "about_me")
    private String aboutMe;

    @Column(name = "id_image")
    private String imageId;

    @JsonView(View.BasicInfo.class)
    @Column(name = "id_compressed_image")
    private String compressedImageId;

    @Column(name = "non_block")
    private boolean nonBlock;

    @Column(name = "balance", columnDefinition = "integer default '0'")
    private int balance;

    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL)
    @JsonIgnoreProperties(value = "account", allowSetters = true)
    private List<Resume> resumes;

    @OneToMany(mappedBy = "yourAccount", cascade = CascadeType.ALL)
    @JsonIgnoreProperties(value = {"otherAccount", "yourAccount"}, allowSetters = true)
    private List<Contact> yourContact;

    @OneToMany(mappedBy = "otherAccount", cascade = CascadeType.ALL)
    @JsonIgnoreProperties(value = {"otherAccount", "yourAccount"}, allowSetters = true)
    private List<Contact> otherContact;

    @OneToMany(mappedBy = "firstAccount", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Conversation> yourConversations;

    @OneToMany(mappedBy = "secondAccount", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Conversation> otherConversations;

    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL)
    @JsonIgnoreProperties(value = "account", allowSetters = true)
    private List<Startup> startups;

    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL)
    @JsonIgnoreProperties(value = "account", allowSetters = true)
    private List<StartupRole> startupRoles;

    @ManyToMany(cascade = {
            CascadeType.MERGE
    })
    @JoinTable(name = "account_favorites",
            joinColumns = @JoinColumn(name = "account_id"),
            inverseJoinColumns = @JoinColumn(name = "account_favorite_id")
    )
    @JsonIgnore
    private List<Favorite> favorites = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL)
    @JsonIgnoreProperties(value = "account", allowSetters = true)
    private Set<Education> educations;

    @OneToMany(cascade = CascadeType.ALL)
    @JsonIgnoreProperties(value = "account", allowSetters = true)
    private List<WorkExperience> workExperiences;

    public void removeFavorite(Favorite favorite) {
        favorites.remove(favorite);
        favorite.getFavoriteAccounts().remove(this);
    }

    public void addFavorite(Favorite favorite) {
        favorites.add(favorite);
        favorite.getFavoriteAccounts().add(this);
    }


    @OneToMany(mappedBy = "sender", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Message> sendMessages;

    @OneToMany(mappedBy = "receiver", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Message> receivedMessages;

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", nonBlock=" + nonBlock +
                '}';
    }
}
