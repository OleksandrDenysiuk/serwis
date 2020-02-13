package com.example.travelInfo.domain;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Comment extends BaseEntity{

    private String content;

    @ManyToOne(
            fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User author;

    @ManyToOne(
            fetch = FetchType.EAGER)
    @JoinColumn(name = "place_id")
    private Place place;

    @ManyToOne(
            fetch = FetchType.EAGER)
    @JoinColumn(name = "trip_id")
    private Trip trip;

    public Comment() {
    }

    public Comment(String content, User author, Place place) {
        this.content = content;
        this.author = author;
        this.place = place;
    }

    public Comment(String content, User author, Trip trip) {
        this.content = content;
        this.author = author;
        this.trip = trip;
    }

    public Comment(String content, User author) {
        this.content = content;
        this.author = author;
    }
}
