package com.centrale.rest;

import com.centrale.rest.service.DataService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class RestApplicationTests {

    @Autowired
    private DataService dataService;


    @Test
    void testOccurrences() {
        dataService.addOccurrence(1L);
        dataService.addOccurrence(1L);
        dataService.addOccurrence(2L);
        assertEquals(2, dataService.getOccurences().get(1L));
        assertEquals(1, dataService.getOccurences().get(2L));
    }

}
