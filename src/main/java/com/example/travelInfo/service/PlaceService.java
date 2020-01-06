package com.example.travelInfo.service;


import com.example.travelInfo.domain.Comment;
import com.example.travelInfo.domain.Place;
import com.example.travelInfo.domain.Trip;
import com.example.travelInfo.domain.User;
import com.example.travelInfo.repositotories.CommentRepo;
import com.example.travelInfo.repositotories.PlaceRepository;
import com.example.travelInfo.repositotories.TripRepository;
import com.example.travelInfo.repositotories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PlaceService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PlaceRepository placeRepository;

    @Autowired
    private CommentRepo commentRepo;

    @Autowired
    private TripRepository tripRepository;

    public boolean deletePlace(Place place){
        for(Comment comment : commentRepo.findAllByPlace(place)){
            commentRepo.delete(comment);
        }

        for(Trip trip : tripRepository.findAll()){
            trip.getPlaces().remove(place);
            tripRepository.save(trip);
        }

        for(User user : userRepository.findAll()){
            user.getPlaces().remove(place);
            userRepository.save(user);
        }

        placeRepository.delete(place);

        return true;
    }
}
