package com.centrale.rest.controller;

import com.centrale.rest.domain.*;
import com.centrale.rest.service.DataService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Controller
@ResponseBody
@RequestMapping(value = "PFC")
@AllArgsConstructor



public class PFCcontroller {

    private DataService dataService;


    @GetMapping(value = "/play")
    public String play(UserParams param){
        Player joueur1 = new Player();
        Player joueur2 = new Player();
        Pierre p = new Pierre();
        Ciseaux c = new Ciseaux();
        Feuille f = new Feuille();
        Map<String, Hand> dict = new HashMap<String,Hand>();
        dict.put("F",f);dict.put("P",p);dict.put("C",c);
        Map<Integer, String> result = new HashMap<Integer, String>();
        result.put(-1, "Perdu");result.put(0, "Egalité");result.put(1, "Gagné");



        joueur1.setMamain(dict.get(param.getHandPlayerOne()));
        joueur2.setMamain(dict.get(param.getHandPlayerTwo()));



        return "Joueur 1 à " + result.get(joueur1.jeu(joueur2));
    }
    @PostMapping(value = "/player/register")
    public void register(@RequestBody Profile profile){
        dataService.addProfile(profile);
    }

    @GetMapping(value="/getProfiles")
    public Map<Integer, Profile> getProfiles(){
        return dataService.getProfiles();
    }

    @PatchMapping(value = "/player/update/{id}")
    public void updatePlayer(@RequestBody PlayerUpdate update,@PathVariable int id){
        Profile profile = dataService.getProfiles().get(id);

    }



}




