package com.example.travelInfo.controller;

import com.example.travelInfo.domain.*;
import com.example.travelInfo.repositotories.BanRepo;
import com.example.travelInfo.repositotories.CommentRepo;
import com.example.travelInfo.repositotories.PlaceRepository;
import com.example.travelInfo.repositotories.TripRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/place")
public class PlaceController {

    @Autowired
    TripRepository tripRepository;

    @Autowired
    PlaceRepository placeRepository;

    @Autowired
    CommentRepo commentRepo;

    @Autowired
    BanRepo banRepo;

    @GetMapping("{place}/view")
    public String viewPlace(@PathVariable Place place,
                            @AuthenticationPrincipal User user,
                            Model model) {
        model.addAttribute("user", user);
        model.addAttribute("place", place);
        model.addAttribute("trips", tripRepository.findAllByAuthor(user));
        model.addAttribute("comments", commentRepo.findAllByPlaceOrderByIdDesc(place));
        return "place";
    }

    @PostMapping("{place}/commit")
    public String commit(@PathVariable Place place,
                         @AuthenticationPrincipal User user,
                         @RequestParam String newComment) {
        Comment comment = new Comment(newComment, user, place);
        commentRepo.save(comment);
        return "redirect:/place/{place}/view";
    }

    @GetMapping("/rate/{place}")
    public String rating(@RequestParam String rating,
                         @PathVariable Place place,
                         @AuthenticationPrincipal User user) {
        if (!place.isRater(user)) {
            int currentRate = place.getRating() * place.getRatedUsers().size();
            place.getRatedUsers().add(user);
            int newRate = (currentRate + Integer.valueOf(rating)) / place.getRatedUsers().size();
            place.setRating(newRate);
            placeRepository.save(place);
        } else {
            System.out.println("rater");
        }
        return "redirect:/place/{place}/view";
    }

    @PostMapping("{place}/ban")
    public String ban(@PathVariable Place place,
                      @AuthenticationPrincipal User user,
                      @RequestParam(value = "typeOfBan") String typeOfBan,
                      @RequestParam String commentForBan) {
        Ban ban = new Ban(typeOfBan, user, place);
        System.out.printf("true");
        banRepo.save(ban);
        return "redirect:/place/{place}/view";
    }
}
