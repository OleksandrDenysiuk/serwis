package com.example.travelInfo.domain;


import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

@Entity
public class Place {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

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
        this.id = id;
        this.latitude = latitude;
        this.longitude = longitude;
        this.address = address;
        this.author = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public String getAuthorName(){
        return author != null ? author.getUsername() : "user null";
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    public Set<String> getTags() {
        return tags;
    }

    public void setTag(String tag) {
        this.tags.add(tag);
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Set<String> getFiles() {
        return files;
    }

    public void setFile(String file) {
        this.files.add(file);
    }

    public void setFiles(Set<String> files) {
        this.files = files;
    }

    public void setTags(Set<String> tags) {
        this.tags = tags;
    }

    public Set<Trip> getTrips() {
        return trips;
    }

    public void setTrips(Set<Trip> trips) {
        this.trips = trips;
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

    public void setRatedUsers(Set<User> ratedUsers) {
        this.ratedUsers = ratedUsers;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
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
        System.out.println(this.users.size());
        this.users.remove(user);
        user.getPlaces().remove(this);
        System.out.println(users.size());
        System.out.println(user.getPlaces().size());
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
        Place other = (Place) obj;
        if (id != other.id)
            return false;
        return true;
    }
}
