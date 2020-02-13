package com.example.travelInfo.controller;

import com.example.travelInfo.domain.Place;
import com.example.travelInfo.domain.User;
import com.example.travelInfo.repositotories.CommentRepo;
import com.example.travelInfo.repositotories.PlaceRepository;
import com.example.travelInfo.repositotories.TripRepository;
import com.example.travelInfo.service.PlaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;

@Controller
@RequestMapping("/place")
public class PlaceController {

    @Autowired
    TripRepository tripRepository;

    @Autowired
    PlaceRepository placeRepository;

    @Autowired
    private PlaceService placeService;

    @Autowired
    CommentRepo commentRepo;


    @GetMapping("/{place}/rate")
    public String rating(@RequestParam String rating,
                         @PathVariable Place place,
                         @AuthenticationPrincipal User user) {
        if (!place.isRater(user)) {
            int currentRate = place.getRating() * place.getRatedUsers().size();
            place.getRatedUsers().add(user);
            int newRate = (currentRate + Integer.valueOf(rating)) / place.getRatedUsers().size();
            place.setRating(newRate);
            placeRepository.save(place);
            return "redirect:/adventure/place/{place}/details";
        }
        return "redirect:/adventure/place/{place}/details?error=You have already rated this place!";
    }

    @GetMapping("{place}/edit")
    public String editPlaceForm(@PathVariable Place place,
                                Model model){
        model.addAttribute("place",place);
        model.addAttribute("callBack","initMap");
        model.addAttribute("leftBar","leftBarEdit");
        return "map";
    }

    @PostMapping("{place}/edit")
    public String editPlace(@PathVariable Place place,
                            @RequestParam("type") String type,
                            @RequestParam("latitude") String latitude,
                            @RequestParam("longitude") String longitude,
                            @RequestParam("description") String description,
                            @RequestParam("address") String address,
                            @RequestParam("img") ArrayList<MultipartFile> files,
                            @RequestParam("tags") String tags
    ){
        placeService.updatePlace(place, type, latitude, longitude, description, address, tags, files);
        return "redirect:/adventure/place/{place}/details";
    }
}
