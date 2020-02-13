package com.example.travelInfo.domain;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
public class Trip extends BaseEntity{

    @NotNull(message = "Bed data od trip, try second time")
    @Length(max = 25, message = "Name too long (more than 25)")
    private String name;

    @NotNull(message = "Bed data od trip, try second time")
    @Length(max = 255, message = "Description too long (more than 255)")
    private String description;

    private int rating = 0;

    @ManyToMany(
            fetch = FetchType.EAGER
    )
    @JoinTable(
            name = "rated_trip_user",
            joinColumns = { @JoinColumn(name = "trip_id") },
            inverseJoinColumns = { @JoinColumn(name = "rated_user_id") }
    )
    private Set<User> ratedUsers = new HashSet<>();

    @ManyToOne(
            fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User author;

    @ManyToMany(
            cascade=CascadeType.REMOVE,
            fetch=FetchType.EAGER)
    @JoinTable(
            name = "trip_place",
            joinColumns = { @JoinColumn(name = "trip_id") },
            inverseJoinColumns = { @JoinColumn(name = "place_id") }
    )
    private Set<Place> places = new HashSet<>();

    @OneToMany(
            fetch = FetchType.EAGER)
    @JoinColumn(name = "trip_id")
    private Set<Comment> comments;

    public Trip() {
    }

    public Trip(String name,User author){
        this.name = name;
        this.author = author;
    }

    public Trip(String name, User author, Place place) {
        this.name = name;
        this.author = author;
        this.places.add(place);
    }

    public Trip(Long id, Set<Place> places, Set<Comment> comments) {
        super(id);
        this.places = places;
        this.comments = comments;
    }

    public boolean isRater(User rater){
        for(User user : this.getRatedUsers()){
            if(user.equals(rater)){
                return true;
            }
        }
        return false;
    }

    public boolean isAuthor(User user){
        return getAuthor().equals(user);
    }
}
