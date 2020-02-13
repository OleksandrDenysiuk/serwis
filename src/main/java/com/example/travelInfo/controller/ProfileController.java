package com.example.travelInfo.controller;

import com.example.travelInfo.domain.Place;
import com.example.travelInfo.domain.User;
import com.example.travelInfo.repositotories.MessageRepo;
import com.example.travelInfo.repositotories.PlaceRepository;
import com.example.travelInfo.repositotories.UserRepository;
import com.example.travelInfo.service.PlaceService;
import com.example.travelInfo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
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
    private UserRepository userRepository;

    @Autowired
    private PlaceRepository placeRepository;

    @Autowired
    private PlaceService placeService;

    @Autowired
    private MessageRepo messageRepo;

    @GetMapping("profile")
    public String getProfile(Model model, @AuthenticationPrincipal User user) {
        if(user.isAdmin()){
            model.addAttribute("users", userRepository.findAll());
            model.addAttribute("places", placeRepository.findAll());
        }
        System.out.println(messageRepo.findAllByUserFor(user).size());
        model.addAttribute("messages",messageRepo.findAllByUserFor(user));
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

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("block/{user}")
    public String blockUser(@PathVariable User user) {
        user.setLocked(true);
        userRepository.save(user);
        return "redirect:/profile";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("unlock/{user}")
    public String unlockUser(@PathVariable User user) {
        user.setLocked(false);
        userRepository.save(user);
        return "redirect:/profile";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("delete/{place}")
    public String deletePlace(@PathVariable Place place) {
        placeService.deletePlace(place);
        return "redirect:/profile";
    }

    @GetMapping("/Access_Denied")
    public String access(Model model){
        model.addAttribute("error","Access denied, please sing in as an Admin");
        return "login";
    }
}
