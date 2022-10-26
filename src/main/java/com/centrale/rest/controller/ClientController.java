package com.centrale.rest.controller;

import com.centrale.rest.entity.Client;
import com.centrale.rest.repository.ClientRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@ResponseBody
@RequestMapping(value = "/client")
@AllArgsConstructor
public class ClientController {

    private ClientRepository clientRepository;

    @GetMapping(value = "/getClients")
    public Iterable<Client> getEverySchool(){
        return this.clientRepository.findAll();
    }
}
