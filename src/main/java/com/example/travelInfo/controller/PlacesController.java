package com.example.travelInfo.controller;

import com.example.travelInfo.domain.Place;
import com.example.travelInfo.domain.User;
import com.example.travelInfo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/places")
public class PlacesController{

    @Autowired
    UserService userService;

    @GetMapping("{place}")
    public String addToTrip(@PathVariable Place place,@AuthenticationPrincipal User user){
        userService.subscribe(place,user);
        return "redirect:/";
    }

    @GetMapping("{place}/delete")
    public String deleteFromTrip(@PathVariable Place place,@AuthenticationPrincipal User user){
        userService.unSubscribe(place,user);
        return "redirect:/places/my";
    }

}
