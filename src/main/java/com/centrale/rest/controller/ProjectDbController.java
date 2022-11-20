package com.centrale.rest.controller;

import com.centrale.rest.domain.PlayerUpdate;
import com.centrale.rest.domain.Profile;
import com.centrale.rest.entity.*;

import com.centrale.rest.repository.SeriesRepository;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@ResponseBody
@RequestMapping(value = "project")
@AllArgsConstructor

public class ProjectDbController {

    private SeriesRepository seriesrepository;

    @GetMapping(value = "/series")
    public Iterable<SeriesEntity> GetAllSeries() {
        Iterable<SeriesEntity> series = seriesrepository.findAll();

        return series;
    }

    @GetMapping(value = "/wishlist")
    public Iterable<SeriesEntity> GetWishlist() {
        Iterable<SeriesEntity> series = seriesrepository.findSeriesEntitiesByStateIs("Wished");

        return series;
    }

    @GetMapping(value = "/shorter/{length}")
    public Iterable<SeriesEntity> GetSeriesShorterThan(@PathVariable int length){
        Iterable<SeriesEntity> series = seriesrepository.findSeriesEntitiesByTotalEpisodeLessThan(length);
        return series;
    }

    @PostMapping(value="/new")
    public void PostSeries( @RequestBody SeriesEntity series){
        seriesrepository.save(series);
    }

    @DeleteMapping (value = "/delete/{id}")
    public void DeleteSeries(@PathVariable long id){
        seriesrepository.deleteById(id);
    }

    @PutMapping(value = "update/{id}")
    public void UpdateSeries(@RequestBody SeriesEntity series ,@PathVariable long id){
       seriesrepository.deleteById(id);
       seriesrepository.save(series);
    }


}
