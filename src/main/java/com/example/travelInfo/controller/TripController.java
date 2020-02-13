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
public class TripController {

    @Autowired
    TripRepository tripRepository;


    @Autowired
    CommentRepo commentRepo;

    @PostMapping
    public String newTrip(@Valid Trip trip,
                          BindingResult bindingResult,
                          Model model,
                          @AuthenticationPrincipal User user) {
        if (bindingResult.hasErrors()) {
            Map<String, String> errorsMap = ControllerUtils.getErrors(bindingResult);
            model.mergeAttributes(errorsMap);
        } else {
            trip.setAuthor(user);
            tripRepository.save(trip);
        }
        return "redirect:/";
    }

    @PostMapping("{trip}/commit")
    public String commit(@PathVariable Trip trip,
                         @AuthenticationPrincipal User user,
                         @RequestParam String newComment) {
        Comment comment = new Comment(newComment, user);
        commentRepo.save(comment);
        trip.getComments().add(comment);
        tripRepository.save(trip);
        return "redirect:/";
    }


    @GetMapping("/{trip}/{place}/delete")
    public String deleteFromTrip(@PathVariable Place place,
                                 @PathVariable Trip trip,
                                 Model model) {
        trip.getPlaces().remove(place);
        tripRepository.save(trip);
        return "redirect:/trip/{trip}/edit";
    }

    @PostMapping("/{place}/add")
    public String addPlaceToTrip(@PathVariable Place place,
                                 @RequestParam Trip trip) {
        trip.getPlaces().add(place);
        tripRepository.save(trip);
        return "redirect:/adventure/place/{place}/details";
    }

    @GetMapping("/{trip}/rate")
    public String rating(@RequestParam String rating,
                         @PathVariable Trip trip,
                         @AuthenticationPrincipal User user,
                         Model model) {
        if (!trip.isRater(user)) {
            int currentRate = trip.getRating() * trip.getRatedUsers().size();
            trip.getRatedUsers().add(user);
            int newRate = (currentRate + Integer.valueOf(rating)) / trip.getRatedUsers().size();
            trip.setRating(newRate);
            tripRepository.save(trip);
            return "redirect:/adventure/trip/{trip}/details";
        }
        return "redirect:/adventure/trip/{trip}/details?error=You have already rated this place!";
    }

    @GetMapping("/{trip}/edit")
    public String editFormTrip(@AuthenticationPrincipal User user,
                               Model model,
                               @PathVariable Trip trip) {
        model.addAttribute("edit", true);
        model.addAttribute("user", user);
        model.addAttribute("trip", trip);
        model.addAttribute("comments", commentRepo.findAllByTrip(trip));
        model.addAttribute("leftBar", "tripDetails");
        model.addAttribute("callBack", "initMapTripDetails");
        return "map";
    }

    @PostMapping("/{trip}/edit")
    public String editTrip(@PathVariable Trip trip,
                           @RequestParam String name,
                           @RequestParam String description
    ) {
        if (isChanged(name, trip.getName())) {
            trip.setName(name);
        }

        if (isChanged(description, trip.getDescription())) {
            trip.setDescription(description);
        }

        tripRepository.save(trip);

        return "redirect:/adventure/trip/{trip}/details";
    }

    private boolean isChanged(String value1, String value2) {
        return (value1 != null && !value1.equals(value2)) ||
                (value2 != null && !value2.equals(value1));
    }
}
