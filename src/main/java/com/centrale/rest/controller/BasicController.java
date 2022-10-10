package com.centrale.rest.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "random")
public class BasicController {

    @GetMapping(value = "/double")
    public double getRandomDouble(){
        return Math.random() * 10;
    }

    @GetMapping(value = "/long/{min}/{max}")
    public long getRandomIntFromInterval(@PathVariable int min, @PathVariable int max){
        return Math.round(Math.random() * (max - min) + min);
    }

}
