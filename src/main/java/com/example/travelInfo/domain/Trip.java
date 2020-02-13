package com.example.travelInfo.domain;

import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Trip {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

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
        this.id = id;
        this.places = places;
        this.comments = comments;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public Set<Place> getPlaces() {
        return places;
    }

    public void setPlaces(Set<Place> places) {
        this.places = places;
    }

    public Set<Comment> getComments() {
        return comments;
    }

    public void setComments(Set<Comment> comments) {
        this.comments = comments;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public Set<User> getRatedUsers() {
        return ratedUsers;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setRatedUsers(Set<User> ratedUsers) {
        this.ratedUsers = ratedUsers;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        return id.intValue() * 31;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Trip other = (Trip) obj;
        if (id != other.id)
            return false;
        return true;
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
