package com.centrale.rest.controller;

import com.centrale.rest.entity.AlbumEntity;
import com.centrale.rest.entity.ImageEntity;
import com.centrale.rest.repository.AlbumRepository;
import com.centrale.rest.repository.ImageRepository;
import com.centrale.rest.service.DataService;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping(produces="application/json")
@AllArgsConstructor
public class BasicController {

    private DataService dataService;
    private AlbumRepository albumRepository;
    private ImageRepository imageRepository;


    @GetMapping(value = "/init")
    public Iterable<AlbumEntity> init() {
        // Clean the database
        imageRepository.deleteAll();
        albumRepository.deleteAll();
        // Create an album
        AlbumEntity album = new AlbumEntity();
        Set<ImageEntity> images = new HashSet<>();
        album.setImages(images);
        album.setName("Album 1");
        albumRepository.save(album);
        // Return the albums
        return albumRepository.findAll();
    }

    @Configuration
    @Profile("development")
    public class DevConfiguration implements WebMvcConfigurer {
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedMethods("GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS").allowedOrigins("*");
            }

    }

    @GetMapping(value = "/getAlbumName/{id}")
    public String getImages(@PathVariable String id) {
        return albumRepository.findById(Long.valueOf(id)).get().getName();
    }


    @GetMapping(value = "/getAlbum/{id}")
    public AlbumEntity getAlbum(@PathVariable String id) {
        return albumRepository.findById(Long.valueOf(id)).get();
    }

    @GetMapping(value = "/getAlbums")
    public Iterable<AlbumEntity> getAlbums() {
        return albumRepository.findAll();
    }

    @PostMapping(value = "/addAlbum")
    public AlbumEntity addAlbum(@RequestBody AlbumEntity albumEntity) {
        return albumRepository.save(albumEntity);
    }

    @PostMapping(value = "/addImage/{id}")
    public String addImage(@RequestBody String imageURL, @PathVariable Long id) {
        AlbumEntity albumEntity = albumRepository.findById(id).get();
        ImageEntity imageEntity = new ImageEntity();
        imageEntity.setAlbum(albumEntity);
        imageEntity.setUrl(imageURL);
        imageRepository.save(imageEntity);
        albumRepository.save(albumEntity);
        return "Image added";

    }

}
