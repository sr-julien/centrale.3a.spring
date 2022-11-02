package com.centrale.rest.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class displayController {

    @GetMapping("/display")
    public String displayData(){
       return "display";
    }
}
