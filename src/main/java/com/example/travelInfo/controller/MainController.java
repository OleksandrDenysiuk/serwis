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
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class MainController {
    @Autowired
    private PlaceRepository placeRepository;

    @Autowired
    private TripRepository tripRepository;


    @GetMapping("/")
    public String viewPlaces(@AuthenticationPrincipal User user,
                             @RequestParam(defaultValue = "dataDesc") String sort,
                             Model model) {

        if(user != null){
            model.addAttribute("user",user);
        }

        Iterable<Place> places;

        if(sort.equals("dataDesc")) {
            model.addAttribute("dataDesc",true);
            places = placeRepository.findAllByOrderByDateDesc();
        }else if(sort.equals("dataAsc")) {
            model.addAttribute("dataAsc",true);
            places = placeRepository.findAllByOrderByDateAsc();
        }else if(sort.equals("ratingAsc")) {
            model.addAttribute("ratingAsc",true);
            places = placeRepository.findAllByOrderByRatingAsc();
        }else if(sort.equals("ratingDesc")) {
            model.addAttribute("ratingDesc",true);
            places = placeRepository.findAllByOrderByRatingDesc();
        }else {
            model.addAttribute("dataDesc",true);
            places = placeRepository.findAllByOrderByRatingDesc();
        }


        model.addAttribute("allPlaces",true);
        model.addAttribute("places",places);

        return "index";
    }

    @GetMapping("/all_trips")
    public String viewTrips(@AuthenticationPrincipal User user, Model model) {

        if(user != null){
            model.addAttribute("user",user);
        }

        Iterable<Trip> trips = tripRepository.findAll();
        model.addAttribute("allTrips",true);

        model.addAttribute("start","0");
        model.addAttribute("finish","0");

        model.addAttribute("trips", trips);
        return "index";
    }

}