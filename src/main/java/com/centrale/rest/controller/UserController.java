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
public class UserController {

    PasswordAuthentification passwordAuthentification;

    UserRepository userRepository;

    PostRepository postRepository;

    @PostMapping(value = "/user/register")
    public UserEntity registerUser(@RequestBody UserDTO userDTO,
                                   HttpServletRequest httpServletRequest,
                                   HttpServletResponse httpServletResponse) throws IOException {
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
    public List<PostEntity> getAllPosts( HttpServletRequest httpServletRequest,
                                         HttpServletResponse httpServletResponse) {
        List<PostEntity> posts = new ArrayList<PostEntity>();
        postRepository.findAll().forEach(posts::add);
        return posts;
    }



    @PostMapping(value = "/user/login")
    public UserEntity login(@RequestBody UserDTO userData,
                            HttpServletRequest httpServletRequest,
                            HttpServletResponse httpServletResponse) throws IOException {

        /*
         * Authenticate with Cookie
         */

        try {
            String ValueEmailCookie = Arrays.stream(httpServletRequest.getCookies())
                    .filter(c -> "email".equals(c.getName()))
                    .map(Cookie::getValue)
                    .findAny().get();
            String ValueSessionIdCookie = Arrays.stream(httpServletRequest.getCookies())
                    .filter(c -> "sessionId".equals(c.getName()))
                    .map(Cookie::getValue)
                    .findAny().get();

            UserEntity user = userRepository.findUserEntitiesByEmail(ValueEmailCookie);


            if (Objects.equals(ValueSessionIdCookie, user.getSessionId())) {
                System.out.println("ACCESS WITH COOKIE GRANTED");
                httpServletResponse.setStatus(200);
                return user;
            } else {
                httpServletResponse.sendError(401, "Invalid Credentials");
                return null;
            }
        } catch (Exception e) {

            /*
             * If no cookie,
             * Authenticate with email, password
             */

            UserEntity user = userRepository.findUserEntitiesByEmail(userData.getEmail());
            if (user == null) {
                httpServletResponse.sendError(401, "Invalid Credentials");
                return null;
            }
            boolean AccessGranted = passwordAuthentification.authenticate(
                    userData.getPassword().toCharArray(),
                    user.getHashedPassword()
            );
            if (AccessGranted) {
                UUID valueUUID = UUID.randomUUID();
                Cookie sessionIdCookie = new Cookie("sessionId", valueUUID.toString());
                sessionIdCookie.setPath("/");
                sessionIdCookie.setHttpOnly(true);
                //sessionIdCookie.setSecure(true);
                httpServletResponse.addCookie(sessionIdCookie);
                user.setSessionId(valueUUID.toString());
                Cookie emailCookie = new Cookie("email", userData.getEmail());
                sessionIdCookie.setPath("/");
                emailCookie.setHttpOnly(true);
                //emailCookie.setSecure(true);
                httpServletResponse.addCookie(emailCookie);
                System.out.println("ACCESS WITH PASSWORD GRANTED");
                userRepository.save(user);
                httpServletResponse.setStatus(200);
                return user;
            } else {
                httpServletResponse.sendError(401, "Invalid Credentials");
                return null;
            }
        }
    }

    @GetMapping(value = "/user/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response) {

        Cookie emailCookieRemove = new Cookie("email", "");
        emailCookieRemove.setMaxAge(0);
        response.addCookie(emailCookieRemove);
        Cookie sessionIdCookieRemove = new Cookie("sessionId", "");
        sessionIdCookieRemove.setMaxAge(0);
        response.addCookie(sessionIdCookieRemove);

        response.setStatus(200);
        return "User successfully logout";
    }
}
