package com.example.travelInfo.controller;

import com.example.travelInfo.domain.Place;
import com.example.travelInfo.domain.User;
import com.example.travelInfo.repositotories.CommentRepo;
import com.example.travelInfo.repositotories.PlaceRepository;
import com.example.travelInfo.repositotories.TripRepository;
import com.example.travelInfo.service.PlaceService;
import com.example.travelInfo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

    @Autowired
    CommentRepo commentRepo;

    @Autowired
    TripRepository tripRepository;

    @Autowired
    private PlaceService placeService;

    @Value("${upload.path}")
    private String uploadPath;

    @PostMapping("/createPlace")
    public String createPlace(
            @AuthenticationPrincipal User user,
            @Valid Place place,
            BindingResult bindingResult,
            Model model,
            @RequestParam("img") ArrayList<MultipartFile> files,
            @RequestParam("tags") String tags
    ) throws IOException {
        if (bindingResult.hasErrors()) {
            Map<String, String> errorsMap = ControllerUtils.getErrors(bindingResult);
            model.mergeAttributes(errorsMap);
            if(tags.equals("")){
                model.addAttribute("tagsError","Please write some tag");
            }
            if(placeService.sizeOf(files) == 0){
                model.addAttribute("filesError","Choose some images!");
            }else if(files.size() > 9){
                model.addAttribute("filesError","Max 9 images! Try again!");
            }
            model.addAttribute("callBack","initMap");
            model.addAttribute("leftBar","createPlace");
            model.addAttribute("place", place);
            return "map";
        } else {
            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
            place.setDate(timestamp);
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
            placeRepository.save(place);
            userService.subscribe(place, user);
        }

        return "redirect:/";
    }


    @GetMapping("/createPlace")
    public String formPlace( Model model) {
        model.addAttribute("callBack","initMap");
        model.addAttribute("leftBar","createPlace");
        return "map";
    }
}
