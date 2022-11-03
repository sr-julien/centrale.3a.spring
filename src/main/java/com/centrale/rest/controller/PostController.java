package com.centrale.rest.controller;


import com.centrale.rest.PasswordAuthentification;
import com.centrale.rest.PostDTO;
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
import java.util.*;

@Controller
@ResponseBody
@RequestMapping(value = "")
@AllArgsConstructor
public class PostController {

    PasswordAuthentification passwordAuthentification;

    UserRepository userRepository;

    PostRepository postRepository;

    /*
     * Util
     */


    public String getValueCookieFromRequest(HttpServletRequest request,
                                            HttpServletResponse response,
                                            String name) {
        return Arrays.stream(request.getCookies())
                .filter(c -> name.equals(c.getName()))
                .map(Cookie::getValue)
                .findAny().get();
    }

    public boolean authService(String sessionID, String email) {

        boolean flag = false;

        UserEntity user = userRepository.findUserEntitiesByEmail(email);

        flag = passwordAuthentification.authenticate(sessionID.toCharArray(), user.getSessionId());

        return flag;
    }

    public boolean authCookie(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            String ValueEmailCookie = Arrays.stream(request.getCookies())
                    .filter(c -> "email".equals(c.getName()))
                    .map(Cookie::getValue)
                    .findAny().get();
            String ValueSessionIdCookie = Arrays.stream(request.getCookies())
                    .filter(c -> "sessionId".equals(c.getName()))
                    .map(Cookie::getValue)
                    .findAny().get();

            response.setStatus(200);
            return authService(ValueSessionIdCookie, ValueEmailCookie);
        } catch (Exception e) {
            response.sendError(401, "Invalid Credentials");
            return false;
        }
    }


    /*
     * Routes
     */


    @GetMapping(value = "/posts")
    public List<PostEntity> getAllPosts( HttpServletRequest httpServletRequest,
                                      HttpServletResponse httpServletResponse) {
        List<PostEntity> posts = new ArrayList<PostEntity>();
        postRepository.findAll().forEach(posts::add);
        return posts;
    }

    @GetMapping(value = "/posts/find")
    public PostEntity getPostsByTitle( HttpServletRequest httpServletRequest,
                                       HttpServletResponse httpServletResponse,
                                       @RequestParam String title) throws IOException {
        PostEntity post =  postRepository.findPostEntitiesByTitle(title);
        if (post != null) {
            return post;
        } else {
            httpServletResponse.sendError(404, "Post not found");
            return null;
        }
    }

    @PostMapping(value = "posts/create")
    public PostEntity createPost(HttpServletRequest request,
                                 HttpServletResponse response,
                                 @RequestBody PostDTO post) throws IOException {

        if (authCookie(request, response)){

            String emailCookie = getValueCookieFromRequest(request, response, "email");
            UserEntity author = userRepository.findUserEntitiesByEmail(emailCookie);

            if (postRepository.findPostEntityByTitleAndAuthor(post.getTitle(), author) == null)
            {
                PostEntity newPost = new PostEntity();
                newPost.setTitle(post.getTitle());
                newPost.setContent(post.getContent());
                newPost.setAuthor(author);
                postRepository.save(newPost);
                return newPost;

            } else {
                response.sendError(401, "You have already created this post");
            }

        }
        return null;
    }

    @DeleteMapping(value = "posts/find/delete")
    public String deletePost(HttpServletRequest request,
                                 HttpServletResponse response,
                                 @RequestParam String title) throws IOException {

        if (authCookie(request, response)){

            String emailCookie = getValueCookieFromRequest(request, response, "email");
            UserEntity author = userRepository.findUserEntitiesByEmail(emailCookie);
            PostEntity post = postRepository.findPostEntityByTitleAndAuthor(title, author);

            if (post != null)
            {
                postRepository.delete(post);
                return "Post successfully deleted";

            } else {
                response.sendError(404, "Post not found");
                return null;
            }
        }
        return null;
    }

    @PutMapping(value = "posts/find/update")
    public PostEntity updatePost(HttpServletRequest request,
                             HttpServletResponse response,
                             @RequestBody PostDTO postDTO) throws IOException {

        if (authCookie(request, response)){

            String emailCookie = getValueCookieFromRequest(request, response, "email");
            UserEntity author = userRepository.findUserEntitiesByEmail(emailCookie);
            PostEntity post = postRepository.findPostEntityByTitleAndAuthor(postDTO.getTitle(), author);

            if (post != null)
            {
                post.setContent(postDTO.getContent());
                postRepository.save(post);
                return post;

            } else {
                response.sendError(404, "Post not found");
                return null;
            }
        }
        return null;
    }

}
