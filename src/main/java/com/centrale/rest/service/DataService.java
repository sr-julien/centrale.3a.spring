package com.centrale.rest.service;

import com.centrale.rest.data.InMemoryData;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

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


}
