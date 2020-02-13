package com.example.travelInfo.domain;


import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

@Getter
@Setter
@Entity
public class Place extends BaseEntity{

    @NotBlank(message = "Please choose some type of place")
    private String type;
    @NotBlank(message = "Please choose some point on map")
    private String latitude;
    @NotBlank(message = "Please choose some point on map")
    private String longitude;
    @NotBlank(message = "Please choose some point on map")
    private String address;

    private Timestamp date;

    @ManyToOne(
            fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User author;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "file", joinColumns = @JoinColumn(name = "place_id"))
    @Column(name = "file_name")
    private Set<String> files = new HashSet<>();


    @NotBlank(message = "Please fill the description of place")
    @Length(max = 255, message = "Description too long (more than 255)")
    private String description;

    @ManyToMany(
            fetch = FetchType.EAGER
    )
    @JoinTable(
            name = "subscriber_place",
            joinColumns = { @JoinColumn(name = "place_id") },
            inverseJoinColumns = { @JoinColumn(name = "subscriber_id") }
    )
    private Set<User> users = new HashSet<>();

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "tag", joinColumns = @JoinColumn(name = "place_id"))
    @Column(name = "tag")
    private Set<String> tags = new HashSet<>();

    @ManyToMany(
            cascade=CascadeType.ALL,
            fetch=FetchType.EAGER,
            mappedBy = "places")
    private Set<Trip> trips = new HashSet<>();

    private int rating = 0;

    @ManyToMany(
            fetch = FetchType.EAGER
    )
    @JoinTable(
            name = "rated_user",
            joinColumns = { @JoinColumn(name = "place_id") },
            inverseJoinColumns = { @JoinColumn(name = "rated_user_id") }
    )
    private Set<User> ratedUsers = new HashSet<>();

    public Place(){

    }

    public Place(Long id, String latitude, String longitude, String address, User user) {
        super(id);
        this.latitude = latitude;
        this.longitude = longitude;
        this.address = address;
        this.author = user;
    }

    public String getOneFile(){
        Iterator<String> i = files.iterator();
        while (i.hasNext()){
            return i.next();
        }
        return null;
    }

    public Set<String> getWithoutFirstFile(){
        Set<String> list = getFiles();
        list.remove(getOneFile());
        return list;
    }


    public void removeUser(User user) {
        this.users.remove(user);
        user.getPlaces().remove(this);
    }

    public boolean isSubscriber(User subscriber){
        for(User user : this.getUsers()){
            if(user.equals(subscriber)){
                return true;
            }
        }
        return false;
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
