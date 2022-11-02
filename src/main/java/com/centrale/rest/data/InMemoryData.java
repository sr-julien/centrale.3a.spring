package com.centrale.rest.data;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.concurrent.ConcurrentHashMap;

@Component
@Getter
@NoArgsConstructor
public class InMemoryData {

    ConcurrentHashMap<Long, Integer> occurrences = new ConcurrentHashMap<>();


}
