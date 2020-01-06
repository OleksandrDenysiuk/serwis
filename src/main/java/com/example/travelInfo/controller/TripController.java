package com.example.travelInfo.controller;

import com.example.travelInfo.domain.Comment;
import com.example.travelInfo.domain.Place;
import com.example.travelInfo.domain.Trip;
import com.example.travelInfo.domain.User;
import com.example.travelInfo.repositotories.CommentRepo;
import com.example.travelInfo.repositotories.TripRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;


@Controller
@RequestMapping("/trip")
public class TripController{

    @Autowired
    TripRepository tripRepository;


    @Autowired
    CommentRepo commentRepo;

    @PostMapping
    public String newTrip(@Valid Trip trip,
                          BindingResult bindingResult,
                          Model model,
                          @AuthenticationPrincipal User user){
        trip.setAuthor(user);
        if (bindingResult.hasErrors()) {
            Map<String, String> errorsMap = ControllerUtils.getErrors(bindingResult);
            model.mergeAttributes(errorsMap);
            model.addAttribute("trip", trip);
        } else {
            tripRepository.save(trip);
        }
        return "redirect:/places/my";
    }

    @PostMapping("/{place}/add")
    public String addPlaceToTrip(@PathVariable Place place,
                                 @RequestParam(value = "trip") Trip trip){
        trip.getPlaces().add(place);
        tripRepository.save(trip);
        return "redirect:/places/my";
    }

    @PostMapping("{trip}/commit")
    public String commit(@PathVariable Trip trip,
                         @AuthenticationPrincipal User user,
                         @RequestParam String newComment) {
        Comment comment = new Comment(newComment,user);
        commentRepo.save(comment);
        trip.getComments().add(comment);
        tripRepository.save(trip);
        return "redirect:/";
    }
}
