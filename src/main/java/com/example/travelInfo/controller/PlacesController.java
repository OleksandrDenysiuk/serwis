package com.example.travelInfo.controller;

import com.example.travelInfo.domain.Place;
import com.example.travelInfo.domain.User;
import com.example.travelInfo.repositotories.PlaceRepository;
import com.example.travelInfo.repositotories.TripRepository;
import com.example.travelInfo.repositotories.UserRepository;
import com.example.travelInfo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/places")
public class PlacesController{
    @Autowired
    PlaceRepository placeRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    TripRepository tripRepository;

    @Autowired
    UserService userService;

    @GetMapping("{place}")
    public String addToTrip(@PathVariable Place place,@AuthenticationPrincipal User user){
        userService.subscribe(place,user);
        return "redirect:/";
    }

    @GetMapping("{place}/delete")
    public String deletePLace(@PathVariable Place place,@AuthenticationPrincipal User user){
        userService.unSubscribe(place,user);
        return "redirect:/places/my";
    }

    @GetMapping("{place}/edit")
    public String editPLace(@PathVariable Place place,Model model){
        model.addAttribute("type",place.getType());
        model.addAttribute("latitude",place.getLatitude());
        model.addAttribute("longitude",place.getLongitude());
        model.addAttribute("address",place.getAddress());
        model.addAttribute("description",place.getDescription());
        model.addAttribute("tags",place.getTags());
        return "map";
    }

    @GetMapping("/my")
    public String showMyPlaces(@AuthenticationPrincipal User user, Model model){
        model.addAttribute("userName",user.getUsername());
        model.addAttribute("user",user);
        model.addAttribute("places",user.getPlaces());
        return "places";
    }

}
