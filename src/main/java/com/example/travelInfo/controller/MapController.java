package com.example.travelInfo.controller;

import com.example.travelInfo.domain.Place;
import com.example.travelInfo.domain.User;
import com.example.travelInfo.repositotories.PlaceRepository;
import com.example.travelInfo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Map;
import java.util.UUID;

@Controller
public class MapController {
    @Autowired
    private PlaceRepository placeRepository;

    @Autowired
    private UserService userService;

    @Value("${upload.path}")
    private String uploadPath;

    @PostMapping("/map")
    public String createPlace(
            @AuthenticationPrincipal User user,
            @Valid Place place,
            BindingResult bindingResult,
            Model model,
            @RequestParam("type") String type,
            @RequestParam("latitude") String latitude,
            @RequestParam("longitude") String longitude,
            @RequestParam("address") String address,
            @RequestParam("img") ArrayList<MultipartFile> files,
            @RequestParam("tags") String tags
    ) throws IOException {
        place.setType(type);
        place.setLatitude(latitude);
        place.setLongitude(longitude);
        place.setAddress(address);
        place.setAuthor(user);
        for(MultipartFile file : files){
            if (file != null && !file.getOriginalFilename().isEmpty()) {
                File uploadDir = new File(uploadPath);

                if (!uploadDir.exists()) {
                    uploadDir.mkdir();
                }

                String uuidFile = UUID.randomUUID().toString().replace("-", "");

                file.transferTo(new File(uploadPath + "/" + uuidFile));

                place.setFile(uuidFile);
            }
        }
        String[] arrOfStr = tags.split(",");
        for (String a : arrOfStr){
            place.setTag(a);
        }
        if (bindingResult.hasErrors()) {
            Map<String, String> errorsMap = ControllerUtils.getErrors(bindingResult);
            System.out.println(errorsMap);
            model.mergeAttributes(errorsMap);
            model.addAttribute("place", place);
            return "map";
        } else {
            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
            place.setDate(timestamp);
            placeRepository.save(place);
            userService.subscribe(place, user);
        }

        return "redirect:/";
    }


    @GetMapping("/map")
    public String showMap() {
        return "map";
    }
}
