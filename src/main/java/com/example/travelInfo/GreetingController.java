package com.example.travelInfo;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

@Controller
public class GreetingController {

    private String testString = "";

    @GetMapping("/greeting")
    public String greeting(@RequestParam(name="name", required=false, defaultValue="World") String name, Model model) {
        model.addAttribute("name", name);
        return "greeting";
    }

    @RequestMapping(value = "/greeting", method = POST)
    @ResponseBody
    public String greeting(@RequestBody Map<String, Test> test) {

        System.out.println(test);
        return "Post some Foos";
    }

    @RequestMapping(value = "/", method = POST)
    @ResponseBody
    public String test(@RequestBody List<Test> test) {
        for (Test num : test) {
            System.out.println(num.getId() + "-" + num.getLat() + "-" + num.getLng() + "-" + num.getAddress());
        }

        return "Post some Foos";
    }


    @GetMapping("/")
    public String test() {
        return "index";
    }

}