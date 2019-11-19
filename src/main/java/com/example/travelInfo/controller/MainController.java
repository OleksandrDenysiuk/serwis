package com.example.travelInfo.controller;

import com.example.travelInfo.domain.Place;
import com.example.travelInfo.domain.User;
import com.example.travelInfo.repositotories.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


@Controller
@CrossOrigin
public class MainController {
    @Autowired
    private MessageRepository messageRepository;

    Place place;

    @Value("${upload.path}")
    private String uploadPath;

    @GetMapping("/")
    public String greeting(@AuthenticationPrincipal User user, Model model) {
        if(user == null){
            model.addAttribute("userName","Guest");
            model.addAttribute("display","none");
        }else {
            model.addAttribute("userName",user.getUsername());
            model.addAttribute("display","inline");
        }
        Iterable<Place> places = messageRepository.findAll();
        model.addAttribute("places",places);
        return "index";
    }

    @PostMapping("/map")
    public String getMultipartFiles(
            @RequestParam("img") ArrayList<MultipartFile> files
    ) throws IOException {
        this.place = new Place();
        for(MultipartFile file : files){
            if (file != null && !file.getOriginalFilename().isEmpty()) {
                File uploadDir = new File(uploadPath);

                if (!uploadDir.exists()) {
                    uploadDir.mkdir();
                }

                String uuidFile = UUID.randomUUID().toString();
                String resultFilename = uuidFile + "." + file.getOriginalFilename();

                file.transferTo(new File(uploadPath + "/" + resultFilename));

                System.out.println(resultFilename);
                this.place.setFile(resultFilename);
            }
        }

        System.out.println(files.size());
        return "redirect:/";
    }

    @RequestMapping(value = "/place", produces="application/json", method = RequestMethod.POST)
    @ResponseBody
    public void getFormData(
            @AuthenticationPrincipal User user,
            @RequestBody Place place
    ){
        this.place = new Place();
        this.place.setLatitude(place.getLatitude());
        this.place.setLongitude(place.getLongitude());
        this.place.setAddress(place.getAddress());
        this.place.setAuthor(user);
        messageRepository.save(this.place);
        System.out.println(this.place.getId() + "-" + this.place.getLatitude() + "-" + this.place.getLongitude() + "-" + this.place.getAddress() + "-" + this.place.getFile());
    }


    @GetMapping("/map")
    public String test() {
        return "map";
    }

}