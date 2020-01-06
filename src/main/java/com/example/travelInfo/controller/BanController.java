package com.example.travelInfo.controller;

import com.example.travelInfo.domain.*;
import com.example.travelInfo.repositotories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/ban")
public class BanController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PlaceRepository placeRepository;

    @Autowired
    private TripRepository tripRepository;

    @Autowired
    private CommentRepo commentRepo;

    @Autowired
    private BanRepo banRepo;

    @GetMapping("/accept/{ban}")
    public String accept(@PathVariable Ban ban){
        Place placeForDelete = ban.getPlace();

        for(Comment comment : commentRepo.findAllByPlace(placeForDelete)){
            commentRepo.delete(comment);
        }

        for(Trip trip : tripRepository.findAll()){
            trip.getPlaces().remove(placeForDelete);
            tripRepository.save(trip);
        }

        for(User user : userRepository.findAll()){
            user.getPlaces().remove(placeForDelete);
            if(user.equals(ban.getPlace().getAuthor())){
                user.getMessages().add("Your place was deleted, id: " + placeForDelete.getId());
            }
            userRepository.save(user);
        }

        placeRepository.delete(placeForDelete);

        banRepo.delete(ban);
        return "redirect:/";
    }

    @GetMapping("/reject/{ban}")
    public String reject(@PathVariable Ban ban){
        System.out.println("Reject");
        return "redirect:/";
    }
}
