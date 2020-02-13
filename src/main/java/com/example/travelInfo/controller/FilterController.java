package com.example.travelInfo.controller;

import com.example.travelInfo.domain.Place;
import com.example.travelInfo.domain.Trip;
import com.example.travelInfo.domain.User;
import com.example.travelInfo.repositotories.PlaceRepository;
import com.example.travelInfo.repositotories.TripRepository;
import com.example.travelInfo.repositotories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
public class FilterController {

    @Autowired
    private PlaceRepository placeRepository;

    @Autowired
    private TripRepository tripRepository;

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/filter/places")
    public String searchPlaces(@AuthenticationPrincipal User user,
                               @RequestParam String location,
                               @RequestParam(required = false) String[] type,
                               @RequestParam(required = false) String owner,
                               @RequestParam String tags,
                               Model model) {

        if (user != null) {
            model.addAttribute("user", user);
        }

        List<Place> places = placeRepository.findAll();

        if (location != "") {
            places.retainAll(placeRepository.findAllByAddress(location));
            model.addAttribute("location", location);
        }

        if (type != null) {
            List<Place> placesByTypes = new ArrayList<>();
            for (String str : type) {
                List<Place> placesByType = placeRepository.findAllByType(str);
                if (placesByType.size() != 0) {
                    placesByTypes.addAll(placesByType);
                }
                model.addAttribute(str, str);
            }
            places.retainAll(placesByTypes);
        }

        if (owner != null) {
            List<Place> placesByOwner;
            if (owner.equals("myPlaces")) {
                placesByOwner = placeRepository.findAllByAuthor(user);
                model.addAttribute("myPlaces", true);
            } else if (owner.equals("notMyPlaces")) {
                placesByOwner = placeRepository.findAll();
                placesByOwner.removeAll(placeRepository.findAllByAuthor(user));
                model.addAttribute("notMyPlaces", true);
            }else {
                placesByOwner = placeRepository.findAll();
                model.addAttribute("allPlaces", true);
            }
            places.retainAll(placesByOwner);
        }

        if (tags != "") {
            String tagsCallBack = "";
            List<Place> placesByTags = new ArrayList<>();
            for (String tag : tags.split(",")) {
                List<Place> placesByTag = placeRepository.findAllByTags(tag);
                if (placesByTag.size() != 0) {
                    placesByTags.addAll(placesByTag);
                }
                tagsCallBack += tag + ",";
            }
            model.addAttribute("tags", tagsCallBack);
            places.retainAll(placesByTags);
        }


        model.addAttribute("places", places);
        return "index";
    }

    @GetMapping("/filter/trips")
    public String searchTrips(@AuthenticationPrincipal User user,
                              @RequestParam String author,
                              @RequestParam(required = false) String owner,
                              @RequestParam(defaultValue = "0 - 0") String amount,
                              Model model) {

        if (user != null) {
            model.addAttribute("user", user);
        }

        List<Trip> trips = tripRepository.findAll();

        if (author != "") {
            trips.retainAll(
                    tripRepository.findAllByAuthor(
                            userRepository.findByUsername(author)
                    )
            );
            model.addAttribute("author", author);
        }

        if (owner != null) {
            List<Trip> tripsByOwner;
            if (owner.equals("myTrips")) {
                tripsByOwner = tripRepository.findAllByAuthor(user);
                model.addAttribute("myTrips", true);
            } else if (owner.equals("notMyTrips")) {
                tripsByOwner = tripRepository.findAll();
                tripsByOwner.removeAll(tripRepository.findAllByAuthor(user));
                model.addAttribute("notMyTrips", true);
            } else {
                tripsByOwner = tripRepository.findAll();
                model.addAttribute("allTrips", true);
            }
            trips.retainAll(tripsByOwner);
        }

        int step = 1;
        List<Trip> copy = new ArrayList<>(trips);
        String[] range = amount.split(" - ");
        for (String str : range) {
            for (Trip trip : copy) {
                if (trip.getPlaces().size() < Integer.valueOf(str) && step == 1) {
                    trips.remove(trip);
                }
                if (trip.getPlaces().size() > Integer.valueOf(str) && step == 2) {
                    trips.remove(trip);
                }
            }
            step++;
        }

        model.addAttribute("start",range[0]);
        model.addAttribute("finish",range[1]);

        model.addAttribute("trips", trips);
        return "index";
    }


}
