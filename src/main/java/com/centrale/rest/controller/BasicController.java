package com.centrale.rest.controller;

import com.centrale.rest.entity.SongEntity;
import com.centrale.rest.entity.ArtistEntity;
import com.centrale.rest.repository.ArtistRepository;
import com.centrale.rest.repository.SongRepository;
import com.centrale.rest.service.DataService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Controller
@ResponseBody
@RequestMapping(value = "appsong")
@AllArgsConstructor
public class BasicController {

    private DataService dataService;
    private SongRepository songRepository;
    private ArtistRepository artistRepository;

    // Global requests

    @GetMapping(value = "/cleandb")
    public void cleandb() {
        songRepository.deleteAll();
        artistRepository.deleteAll();
    }

    // Artists requests

    @GetMapping(value = "/getAllArtists")
    public Iterable<ArtistEntity> getAllArtists() {
        return artistRepository.findAll();
    }

    @GetMapping(value = "/getArtistById/{id}")
    public ArtistEntity getArtistById(@PathVariable String id) {
        return artistRepository.findById(Long.valueOf(id)).get();
    }


    @PostMapping(value = "/postArtist/")
    public ArtistEntity postArtistById(@RequestBody ArtistEntity artist) {
        Set<SongEntity> songs = new HashSet<>();
        artist.setSongs(songs);
        artistRepository.save(artist);
        return artist;
    }

    @DeleteMapping(value="/removeArtist/{id}")
    public void removeArtist(@PathVariable Long id) {
        artistRepository.deleteById(id);
    }

    // Songs requests

    @GetMapping(value = "/getAllSongs")
    public Iterable<SongEntity> getAllSongs() {
        return songRepository.findAll();
    }

    @GetMapping(value = "/getSongById/{id}")
    public SongEntity getSongById(@PathVariable String id) {
        return songRepository.findById(Long.valueOf(id)).get();
    }

    @PostMapping(value = "/postSong/")
    public SongEntity postArtistById(@RequestBody SongEntity inputSong) {
        songRepository.save(inputSong);
        return inputSong;
    }
    @DeleteMapping(value="/removeSong/{id}")
    public void removeSong(@PathVariable Long id) {
        songRepository.deleteById(id);
    }

}
