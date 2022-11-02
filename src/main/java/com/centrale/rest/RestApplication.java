package com.centrale.rest;

import com.centrale.rest.entity.PostEntity;
import com.centrale.rest.entity.UserEntity;
import com.centrale.rest.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.HashSet;


@SpringBootApplication
public class RestApplication {

    private static final Logger log = LoggerFactory.getLogger(RestApplication.class);
    public static void main(String[] args) {
        SpringApplication.run(RestApplication.class, args);
    }

    // Simply to execute some code after startup
    @Bean
    public CommandLineRunner demo(UserRepository userRepository) {
        return (args) -> {
            log.info("Create and save user1 and post1");

            UserEntity user1 = new UserEntity();
            user1.setUsername("user1");
            user1.setEmail("user1@gmail.com");
            user1.setHashedPassword("myHashedPasswordIncomming");

            PostEntity post1 = new PostEntity();
            post1.setTitle("My first Post");
            post1.setUser(user1);
            post1.setContent("Content of the first post");

            HashSet<PostEntity> postsUser1 = new HashSet<>();
            postsUser1.add(post1);
            user1.setPosts(postsUser1);
            userRepository.save(user1);
        };
    }

    @Bean
    public PasswordAuthentification PasswordAuthentification(){
        return new PasswordAuthentification();
    }
}
