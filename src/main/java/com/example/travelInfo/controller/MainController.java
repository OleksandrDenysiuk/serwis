package com.example.travelInfo.controller;

import com.example.travelInfo.domain.Place;
import com.example.travelInfo.domain.Trip;
import com.example.travelInfo.domain.User;
import com.example.travelInfo.repositotories.PlaceRepository;
import com.example.travelInfo.repositotories.TripRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class MainController {
    @Autowired
    private PlaceRepository placeRepository;

    @Autowired
    private TripRepository tripRepository;


    @GetMapping("/")
    public String viewPlaces(@AuthenticationPrincipal User user, Model model) {

        if(user != null){
            model.addAttribute("user",user);
        }

        Iterable<Place> places = placeRepository.findAllByOrderByDateDesc();

        model.addAttribute("places",places);

        return "index";
    }

    @GetMapping("/all_trips")
    public String viewTrips(@AuthenticationPrincipal User user, Model model) {

        if(user != null){
            model.addAttribute("user",user);
        }

        Iterable<Trip> trips = tripRepository.findAll();

        model.addAttribute("trips", trips);
        return "index";
    }

}