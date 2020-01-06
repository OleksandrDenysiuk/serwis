package com.example.travelInfo.controller;

import com.example.travelInfo.domain.Place;
import com.example.travelInfo.domain.User;
import com.example.travelInfo.repositotories.BanRepo;
import com.example.travelInfo.repositotories.PlaceRepository;
import com.example.travelInfo.repositotories.UserRepository;
import com.example.travelInfo.service.PlaceService;
import com.example.travelInfo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ProfileController {

    @Autowired
    private UserService userService;

    @Autowired
    private BanRepo banRepo;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PlaceRepository placeRepository;

    @Autowired
    private PlaceService placeService;

    @GetMapping("profile")
    public String getProfile(Model model, @AuthenticationPrincipal User user) {
        if(user.isAdmin()){
            model.addAttribute("bans", banRepo.findAll());
            model.addAttribute("users", userRepository.findAll());
            model.addAttribute("places", placeRepository.findAll());
        }
        model.addAttribute("user", user);
        return "profile";
    }

    @PostMapping("profile")
    public String updateProfile(
            @AuthenticationPrincipal User user,
            @RequestParam String username,
            @RequestParam String name,
            @RequestParam String surname,
            @RequestParam String password,
            @RequestParam String email
    ) {

        userService.updateProfile(user, username, name, surname, password, email);
        return "redirect:/profile";
    }


    @PostMapping("message/send/{user}")
    public String messageToUser(@PathVariable User user, @RequestParam String message) {
        user.getMessages().add(message);
        userRepository.save(user);
        return "redirect:/profile";
    }

    @PostMapping("block/{user}")
    public String blockUser(@PathVariable User user) {
        user.setLocked(true);
        userRepository.save(user);
        return "redirect:/profile";
    }

    @PostMapping("unlock/{user}")
    public String unlockUser(@PathVariable User user) {
        user.setLocked(false);
        userRepository.save(user);
        return "redirect:/profile";
    }

    @PostMapping("delete/{place}")
    public String deletePlace(@PathVariable Place place) {
        placeService.deletePlace(place);
        return "redirect:/profile";
    }
}
