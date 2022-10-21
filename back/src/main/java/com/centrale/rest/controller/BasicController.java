package com.centrale.rest.controller;

import com.centrale.rest.entity.AlbumEntity;
import com.centrale.rest.entity.ImageEntity;
import com.centrale.rest.repository.AlbumRepository;
import com.centrale.rest.service.DataService;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;
import com.centrale.rest.entity.AlbumEntity;
import com.centrale.rest.entity.ImageEntity;
import com.centrale.rest.repository.AlbumRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.HashSet;
import java.util.Map;

@Controller
@ResponseBody
@AllArgsConstructor
public class BasicController {

    private DataService dataService;
    private AlbumRepository albumRepository;

    @Bean
    @GetMapping(value = "/init")
    public CommandLineRunner init() {
        return (args) -> {
            AlbumEntity album = new AlbumEntity();
            album.setName("James Webb");
            ImageEntity imageEntity = new ImageEntity();
            imageEntity.setUrl("https://www.nasa.gov/sites/default/files/styles/full_width/public/thumbnails/image/stsci-01gfnn3pwjmy4rqxkz585bc4qh.png?itok=Xja4XWS0");
            imageEntity.setAlbum(album);
            HashSet<ImageEntity> images = new HashSet<>();
            images.add(imageEntity);
            album.setImages(images);
            albumRepository.save(album);
        };
    }

    @Configuration
    @Profile("development")
    public class DevConfiguration implements WebMvcConfigurer {
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedMethods("GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS").allowedOrigins("*");
            }

    }

    //@Bean
    @GetMapping(value = "/getAlbum/{id}")
    public String getImages(@PathVariable String id) {
        return albumRepository.findById(Long.valueOf(id)).get().getName();
    }

}
