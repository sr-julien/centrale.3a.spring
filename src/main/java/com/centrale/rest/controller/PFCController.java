package com.centrale.rest.controller;

import com.centrale.rest.domain.*;
import com.centrale.rest.service.DataService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@Controller
@ResponseBody
@RequestMapping(value = "pfc")
@AllArgsConstructor
public class PFCController {

    private DataService dataService;

    @GetMapping(value = "/play")
    public int play(@RequestParam(name = "handPlayerOne") String h1, @RequestParam(name = "handPlayerTwo") String h2) {
        Player p1 = new Player();
        Player p2 = new Player();
        h1 = h1.toLowerCase();
        h2 = h2.toLowerCase();

        if (h1.equals("p")){
            Pierre p = new Pierre();
            p1.setCoup(p);
        } else if (h1.equals("f")){
            Feuille f = new Feuille();
            p1.setCoup(f);
        } else {
            Ciseaux c = new Ciseaux();
            p1.setCoup(c);
        }

        if (h2.equals("p")){
            Pierre p = new Pierre();
            p2.setCoup(p);
        } else if (h2.equals("f")){
            Feuille f = new Feuille();
            p2.setCoup(f);
        } else {
            Ciseaux c = new Ciseaux();
            p2.setCoup(c);
        }

        return p2.jeu(p1);
    }

    @PostMapping(value = "/player/register")
    public void registerPlayer(@RequestBody Player player){
        dataService.registerPlayer(player);
    }

    @GetMapping(value = "/player")
    public ArrayList<Player> getPlayers() {return dataService.getPlayers();}

    @GetMapping(value = "/player/get/{playerId}")
    public Player getPlayer(@PathVariable int playerId){return dataService.getPlayerById(playerId);}

    @PutMapping(value = "/player/update/{playerId}")
    public void updateProfile(@PathVariable int playerId, @RequestBody Player player){
        dataService.updateProfile(playerId, player);
    }

}
