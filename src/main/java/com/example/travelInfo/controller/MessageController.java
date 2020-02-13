package com.example.travelInfo.controller;

import com.example.travelInfo.domain.Message;
import com.example.travelInfo.domain.MessageType;
import com.example.travelInfo.domain.Place;
import com.example.travelInfo.domain.User;
import com.example.travelInfo.repositotories.MessageRepo;
import com.example.travelInfo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Collections;


@Controller
@RequestMapping("/message")
public class MessageController {

    @Autowired
    private MessageRepo messageRepo;

    @Autowired
    UserService userService;

    @PostMapping("/send/{user}")
    public String messageToUser(
            @AuthenticationPrincipal User author,
            @PathVariable User user,
            @RequestParam String message) {
        Message newMessage = new Message();
        if(author.isAdmin()){
            newMessage.setType(Collections.singleton(MessageType.FOR_USER));
        }else {
            newMessage.setType(Collections.singleton(MessageType.FOR_ADMIN));
        }
        newMessage.setAuthor(author);
        newMessage.setContent(message);
        newMessage.setUserFor(user);
        messageRepo.save(newMessage);
        return "redirect:/profile";
    }

    @PostMapping("/claim/{place}")
    public String claimOnPlace(
            @AuthenticationPrincipal User author,
            @PathVariable Place place,
            @RequestParam String message) {
        Message newMessage = new Message();
        newMessage.setType(Collections.singleton(MessageType.CLAIM));
        newMessage.setAuthor(author);
        newMessage.setPlace(place);
        newMessage.setContent(message);
        newMessage.setUserFor(userService.getAdmin());
        messageRepo.save(newMessage);
        return "redirect:/profile";
    }
}
