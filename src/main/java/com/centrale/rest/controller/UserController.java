package com.centrale.rest.controller;


import com.centrale.rest.PasswordAuthentification;
import com.centrale.rest.UserDTO;
import com.centrale.rest.entity.PostEntity;
import com.centrale.rest.entity.UserEntity;
import com.centrale.rest.repository.PostRepository;
import com.centrale.rest.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;

@Controller
@ResponseBody
@RequestMapping(value = "")
@AllArgsConstructor
public class UserController {

    PasswordAuthentification passwordAuthentification;

    UserRepository userRepository;

    PostRepository postRepository;

    @PostMapping(value = "/user/register")
    public UserEntity registerUser(@RequestBody UserDTO userDTO, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws IOException {
        if (userRepository.findUserEntitiesByEmail(userDTO.getEmail()) != null) {
            System.out.println("User already exists");
            httpServletResponse.sendError(400, "User already exists");
        }
        else {
            UserEntity user = new UserEntity();
            user.setUsername(userDTO.getUsername());
            user.setEmail(userDTO.getEmail());
            user.setHashedPassword(passwordAuthentification.hash(userDTO.getPassword().toCharArray()));
            //user.setHashedPassword(userDTO.getPassword());
            userRepository.save(user);
            httpServletResponse.setStatus(200);
            return user;
        }
        return null;
    }

    @GetMapping(value = "/getAllPosts")
    public List<PostEntity> getAllPosts( HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        List<PostEntity> posts = new ArrayList<PostEntity>();
        postRepository.findAll().forEach(posts::add);
        return posts;
    }
}
