package com.example.travelInfo.controller;

import com.example.travelInfo.domain.Comment;
import com.example.travelInfo.repositotories.CommentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CommentController {

    @Autowired
    CommentRepo commentRepo;

    @GetMapping("/{place}/{comment}/delete")
    public String deleteForPlace(@PathVariable Comment comment){
        commentRepo.delete(comment);
        return "redirect:/place/{place}/view";
    }

    @PostMapping("/{place}/{comment}/edit")
    public String editForPlace(@PathVariable Comment comment,
                       @RequestParam String editComment){
        comment.setContent(editComment);
        commentRepo.save(comment);
        return "redirect:/place/{place}/view";
    }

    @GetMapping("/{comment}/delete")
    public String deleteForTrip(@PathVariable Comment comment){
        commentRepo.delete(comment);
        return "redirect:/";
    }

    @PostMapping("/{comment}/edit")
    public String editForTrip(@PathVariable Comment comment,
                       @RequestParam String editComment){
        comment.setContent(editComment);
        commentRepo.save(comment);
        return "redirect:/";
    }
}
