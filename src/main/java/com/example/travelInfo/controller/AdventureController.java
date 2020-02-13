package com.example.travelInfo.controller;

import com.example.travelInfo.domain.Place;
import com.example.travelInfo.domain.Trip;
import com.example.travelInfo.domain.User;
import com.example.travelInfo.repositotories.CommentRepo;
import com.example.travelInfo.repositotories.TripRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/adventure")
public class AdventureController {

    @Autowired
    private TripRepository tripRepository;

    @Autowired
    private CommentRepo commentRepo;

    @GetMapping("/trips")
    public String viewTrips(@AuthenticationPrincipal User user, Model model) {

        if(user != null){
            model.addAttribute("user",user);
        }

        Iterable<Trip> trips = tripRepository.findAllByAuthor(user);

        model.addAttribute("trips", trips);
        return "adventure";
    }

    @GetMapping("/places")
    public String viewPlaces(@AuthenticationPrincipal User user, Model model){
        model.addAttribute("userName",user.getUsername());
        model.addAttribute("user",user);
        model.addAttribute("places",user.getPlaces());
        return "adventure";
    }

    @GetMapping("/place/{place}/details")
    public String viewPlace(@AuthenticationPrincipal User user,
                            @PathVariable Place place,
                            @RequestParam(required = false) String error,
                            Model model) {
        if(error != null){
            model.addAttribute("error","You have already rated this place!");
        }
        model.addAttribute("user", user);
        model.addAttribute("place", place);
        model.addAttribute("trips", tripRepository.findAllByAuthor(user));
        model.addAttribute("comments", commentRepo.findAllByPlace(place));
        model.addAttribute("leftBar","placeDetails");
        model.addAttribute("callBack","initMapPlaceDetails");
        return "map";
    }

    @GetMapping("/trip/{trip}/details")
    public String viewTrip(@AuthenticationPrincipal User user,
                           @PathVariable Trip trip,
                           @RequestParam(required = false) String error,
                           Model model) {
        if(error != null){
            model.addAttribute("error","You have already rated this place!");
        }
        model.addAttribute("user", user);
        model.addAttribute("trip", trip);
        model.addAttribute("comments", commentRepo.findAllByTrip(trip));
        model.addAttribute("leftBar","tripDetails");
        model.addAttribute("callBack","initMapTripDetails");
        return "map";
    }
}
