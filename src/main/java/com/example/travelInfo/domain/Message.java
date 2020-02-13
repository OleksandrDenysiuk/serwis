package com.example.travelInfo.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@Entity
public class Message extends BaseEntity {

    @ElementCollection(targetClass = MessageType.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "message_type", joinColumns = @JoinColumn(name = "message_id"))
    @Enumerated(EnumType.STRING)
    private Set<MessageType> type;

    private String content;

    @ManyToOne(
            fetch = FetchType.EAGER)
    @JoinColumn(name = "author_id")
    private User author;

    @ManyToOne(
            fetch = FetchType.EAGER)
    @JoinColumn(name = "place_id")
    private Place place;

    @ManyToOne(
            fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User userFor;

    public Message() {
    }

    public Message( String content, User author, User userFor) {
        this.content = content;
        this.author = author;
        this.userFor = userFor;
    }
}
