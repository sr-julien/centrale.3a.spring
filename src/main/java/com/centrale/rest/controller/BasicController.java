package com.centrale.rest.controller;

import com.centrale.rest.service.DataService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
@ResponseBody
@RequestMapping(value = "random")
@AllArgsConstructor
public class BasicController {

    private DataService dataService;

    @GetMapping(value = "/double")
    public double getRandomDouble(){
        return Math.random() * 10D;
    }

    @GetMapping(value = "/long/{min}/{max}")
    public Long getRandomIntFromInterval(@PathVariable int min, @PathVariable int max){
        Long random =  Math.round(Math.random() * (max - min) + min);
        dataService.addOccurrence(random);
        return random;
    }

    @GetMapping(value = "/statistics")
    public Map<Long, Integer> getStatisticsSeries(){
        return dataService.getOccurences();
    }

    @GetMapping(value = "/nico")
    public String getNico(){
        return "Nico t'es moche";
    }

}


