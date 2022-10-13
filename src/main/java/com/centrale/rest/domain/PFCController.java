package com.centrale.rest.domain;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@ResponseBody
@RequestMapping(value="/play")
public class PFCController {

    @RequestMapping(value="")
    public String Play(UserParams user) {

        JeuPFC Game = new JeuPFC();
        return Game.playPFC(user.getChoiceJ1(), user.getChoiceJ2());
    }
}
