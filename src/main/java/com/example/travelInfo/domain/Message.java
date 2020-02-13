package com.example.travelInfo.domain;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Set<MessageType> getType() {
        return type;
    }

    public void setType(Set<MessageType> type) {
        this.type = type;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public Place getPlace() {
        return place;
    }

    public void setPlace(Place place) {
        this.place = place;
    }

    public User getUserFor() {
        return userFor;
    }

    public void setUserFor(User userFor) {
        this.userFor = userFor;
    }
}
