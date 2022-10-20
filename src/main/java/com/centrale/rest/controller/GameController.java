package com.centrale.rest.controller;

import com.centrale.rest.*;
import com.centrale.rest.service.DataService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Array;
import java.util.ArrayList;

@Controller
@ResponseBody
@RequestMapping(value = "play")
@AllArgsConstructor

public class GameController {

    ArrayList<Player> allPlayers = new ArrayList<>();


    private DataService dataService;

    @GetMapping(value = "/pfc")
    public int getGame(UserParams userParams){

        ChoixInterface a = null;
        ChoixInterface b = null;

        switch (userParams.getPlayerOne()) {
            case "pierre" -> {
                a = new Pierre();
                break;
            }
            case "feuille" -> {
                a = new Feuille();
                break;
            }
            case "ciseaux" -> {
                a = new Ciseaux();
                break;
            }
        }

        switch (userParams.getPlayerTwo()) {
            case "pierre" -> {
                b = new Pierre();
                break;
            }
            case "feuille" -> {
                b = new Feuille();
                break;
            }
            case "ciseaux" -> {
                b = new Ciseaux();
                break;
            }
        }

        int result = a.playWith(b);

        return result;
    }

    @PostMapping(value = "/player/register")
    public void postPlayer(@RequestBody Player player){
        allPlayers.add(player.getID(), player);
    }

    @PutMapping(value ="/player/update/[ID]")
    public void updatePlayer(@RequestBody Player player) {
        allPlayers.add(player.getID(), player);
    }

    @GetMapping(value = "/player/get/{id}")
    public Player getPlayer(@PathVariable int id){
        return (allPlayers.get(id));
    }

    @GetMapping(value = "/players/top")
    public ArrayList<Player> getTopPlayers(){
        return topfunction(allPlayers);
    }

    public ArrayList<Player> topfunction(ArrayList<Player> listPlayer) {
        ArrayList<Player> topPlayers = new ArrayList();
        while (listPlayer.size()>1) {
            Player tempTopPlayer = listPlayer.get(0);
            for (int i=1; i<listPlayer.size(); i++) {
                if (listPlayer.get(i).getScore()>tempTopPlayer.getScore()) tempTopPlayer=listPlayer.get(i);
            }
            topPlayers.add(tempTopPlayer);
            listPlayer.remove(tempTopPlayer);
        }
        topPlayers.add(listPlayer.get(0));
        return topPlayers;
    }
}
