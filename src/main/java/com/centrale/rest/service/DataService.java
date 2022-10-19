package com.centrale.rest.service;

import com.centrale.rest.data.InMemoryData;
import com.centrale.rest.domain.Player;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class DataService {

    private InMemoryData data;

    public void addOccurrence(Long i){
        data.getOccurrences().put(i, data.getOccurrences().getOrDefault(i, 0) + 1);
    }

    public Map<Long, Integer> getOccurences(){
        return data.getOccurrences();
    }

    public void registerPlayer(Player p) {data.getPlayers().add(p);}
    public ArrayList<Player> getPlayers() {return data.getPlayers();}

    public void updateProfile(int playerId, Player player) {
        ArrayList<Player> players = data.getPlayers();
        for (Player p : players) {
            if (p.getId() == playerId) {
                players.remove(p);
                players.add(player);
            }
        }
    }

    public Player getPlayerById(int playerId) {
        for (Player p : data.getPlayers()) {
            if (p.getId() == playerId) {
                return p;
            }
        }
        return null;
    }
}
