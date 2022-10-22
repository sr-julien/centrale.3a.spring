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
    public String init() {
        // Clean the database
        imageRepository.deleteAll();
        albumRepository.deleteAll();
        return "Database cleaned";
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

    @GetMapping(value = "/addAlbum/{name}")
    public AlbumEntity addAlbum(@PathVariable String name) {
        AlbumEntity album = new AlbumEntity();
        Set<ImageEntity> images = new HashSet<>();
        album.setImages(images);
        album.setName(name);
        albumRepository.save(album);
        return album;
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

    // Delete image
    @DeleteMapping(value = "/deleteImage/{id}")
    public String deleteImage(@PathVariable Long id) {
        imageRepository.deleteById(id);
        return "Image deleted";
    }

    // Delete album
    @DeleteMapping(value = "/deleteAlbum/{id}")
    public String deleteAlbum(@PathVariable Long id) {
        for (ImageEntity imageEntity : albumRepository.findById(id).get().getImages()) {
            imageRepository.deleteById(imageEntity.getId());
        }
        albumRepository.deleteById(id);
        return "Album deleted";
    }

}
