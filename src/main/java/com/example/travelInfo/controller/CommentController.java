package com.example.travelInfo.controller;

import com.example.travelInfo.domain.Comment;
import com.example.travelInfo.domain.Place;
import com.example.travelInfo.domain.Trip;
import com.example.travelInfo.domain.User;
import com.example.travelInfo.repositotories.CommentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CommentController {

    @Autowired
    CommentRepo commentRepo;

    @GetMapping("Place/{place}/{comment}/delete")
    public String deleteForPlace(@PathVariable Comment comment){
        commentRepo.delete(comment);
        return "redirect:/adventure/place/{place}/details";
    }

    @GetMapping("Trip/{trip}/{comment}/delete")
    public String deleteForTrip(@PathVariable Comment comment){
        commentRepo.delete(comment);
        return "redirect:/adventure/trip/{trip}/details";
    }

    @PostMapping("Place/{place}/{comment}/edit")
    public String editForPlace(@PathVariable Comment comment,
                       @RequestParam String editComment){
        comment.setContent(editComment);
        commentRepo.save(comment);
        return "redirect:/adventure/place/{place}/details";
    }

    @PostMapping("Trip/{trip}/{comment}/edit")
    public String editForTrip(@PathVariable Comment comment,
                               @RequestParam String editComment){
        comment.setContent(editComment);
        commentRepo.save(comment);
        return "redirect:/adventure/trip/{trip}/details";
    }

    @PostMapping("/Place/{place}/commit")
    public String commitPlace(@PathVariable Place place,
                         @AuthenticationPrincipal User user,
                         @RequestParam String newComment) {
        Comment comment = new Comment(newComment, user, place);
        commentRepo.save(comment);
        return "redirect:/adventure/place/{place}/details";
    }

    @PostMapping("/Trip/{trip}/commit")
    public String commitTrip(@PathVariable Trip trip,
                         @AuthenticationPrincipal User user,
                         @RequestParam String newComment) {
        Comment comment = new Comment(newComment, user, trip);
        commentRepo.save(comment);
        return "redirect:/adventure/trip/{trip}/details";
    }

}
