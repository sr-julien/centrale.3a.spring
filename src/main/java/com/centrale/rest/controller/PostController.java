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

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.*;

@Controller
@ResponseBody
@RequestMapping(value = "")
@AllArgsConstructor
public class PostController {

    PasswordAuthentification passwordAuthentification;

    UserRepository userRepository;

    PostRepository postRepository;


    @GetMapping(value = "/getPosts")
    public List<PostEntity> getPosts( HttpServletRequest httpServletRequest,
                                      HttpServletResponse httpServletResponse) {
        List<PostEntity> posts = new ArrayList<PostEntity>();
        postRepository.findAll().forEach(posts::add);
        return posts;
    }

    @GetMapping(value = "/getPosts/{title}")
    public PostEntity getPostsById( HttpServletRequest httpServletRequest,
                                    HttpServletResponse httpServletResponse,
                                    @RequestParam String title) {
        PostEntity post = postRepository.findPostEntitiesByTitle(title);
        return post;
    }

}
