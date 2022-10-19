package com.centrale.rest.service;

import com.centrale.rest.data.InMemoryData;
import com.centrale.rest.domain.Profile;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Map;

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

    public void registerProfile(Profile p) {data.getProfiles().add(p);}
    public ArrayList<Profile> getProfiles() {return data.getProfiles();}

    public void updateProfile(int playerId, Profile profile) {
        ArrayList<Profile> profiles = data.getProfiles();
        for (Profile p : profiles) {
            if (p.getId() == playerId) {
                p = profile;
            }
        }
    }
}
