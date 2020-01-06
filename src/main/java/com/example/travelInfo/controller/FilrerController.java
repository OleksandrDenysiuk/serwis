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
public class FilrerController {

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

        if(user != null){
            model.addAttribute("user",user);
        }

        List<Place> places = placeRepository.findAll();

        if (location != ""){
            places.retainAll(placeRepository.findAllByAddress(location));
        }

        if(type != null){
            List<Place> placesByTypes = new ArrayList<>();
            for(String str : type){
                List<Place> placesByType = placeRepository.findAllByType(str);
                if (placesByType.size() != 0){
                    placesByTypes.addAll(placesByType);
                }
            }
            places.retainAll(placesByTypes);
        }

        if(owner != null) {
            if(owner.equals("myPlaces")){
                places = placeRepository.findAllByAuthor(user);
            }else if(owner.equals("notMyPLaces")){
                places.removeAll(placeRepository.findAllByAuthor(user));
            }
        }

        if (tags != "") {
            List<Place> placesByTags = new ArrayList<>();
            for(String tag : tags.split(",")){
                List<Place> placesByTag = placeRepository.findAllByTags(tag);
                if (placesByTag.size() != 0){
                    placesByTags.addAll(placesByTag);
                }
            }
            places.retainAll(placesByTags);
        }


        model.addAttribute("places",places);
        return "index";
    }

    @GetMapping("/filter/trips")
    public String searchTrips(@AuthenticationPrincipal User user,
                              @RequestParam String author,
                              @RequestParam(required = false) String owner,
                              @RequestParam String amount,
                              Model model) {

        if(user != null){
            model.addAttribute("user",user);
        }

        List<Trip> trips = tripRepository.findAll();

        if (author != ""){
            trips.retainAll(
                    tripRepository.findAllByAuthor(
                            userRepository.findByUsername(author)
                    )
            );
        }

        if(owner != null) {
            if(owner.equals("myPlaces")){
                trips = tripRepository.findAllByAuthor(user);
            }else if(owner.equals("notMyPLaces")){
                trips.removeAll(tripRepository.findAllByAuthor(user));
            }
        }

        int step = 1;
        List<Trip> copy = new ArrayList<>(trips);
        for (String str : amount.split(" - ")){
            for(Trip trip : copy){
                if(trip.getPlaces().size() < Integer.valueOf(str) && step == 1){
                    trips.remove(trip);
                }
                if(trip.getPlaces().size() > Integer.valueOf(str) && step == 2){
                    trips.remove(trip);
                }
            }
            step++;
        }



        model.addAttribute("trips",trips);
        return "index";
    }
}
