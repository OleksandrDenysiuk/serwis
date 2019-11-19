package com.example.travelInfo.controller;

import com.example.travelInfo.domain.Place;
import com.example.travelInfo.repositotories.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;

@Controller
public class TestController {
    @Autowired
    private MessageRepository messageRepository;

    @Value("${upload.path}")
    private String uploadPath;

    @GetMapping("/test")
    public String load(){
        return "test";
    }

    /*@PostMapping("/test")
    @ResponseBody
    public String add(
            @AuthenticationPrincipal User user,
            @RequestParam("file") MultipartFile file

    ) throws IOException {

        if (file != null && !file.getOriginalFilename().isEmpty()) {
            File uploadDir = new File(uploadPath);

            if (!uploadDir.exists()) {
                uploadDir.mkdir();
            }

            String uuidFile = UUID.randomUUID().toString();
            String resultFilename = uuidFile + "." + file.getOriginalFilename();

            file.transferTo(new File(uploadPath + "/" + resultFilename));

            System.out.println(resultFilename);
        }
        return "test";
    }*/

    @RequestMapping(value = "/test", produces="application/json", method = RequestMethod.POST)
    @ResponseBody
    public String upload(
            @RequestParam("img") ArrayList<MultipartFile> files
    ){
        System.out.println(files.size());
        return "redirect:/index";
    }
}