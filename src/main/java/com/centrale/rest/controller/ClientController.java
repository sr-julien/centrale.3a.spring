package com.centrale.rest.controller;

import com.centrale.rest.entity.Client;
import com.centrale.rest.repository.ClientRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@ResponseBody
@RequestMapping(value = "/client")
@AllArgsConstructor
public class ClientController {

    private ClientRepository clientRepository;

    @GetMapping(value = "/getClients")
    public Iterable<Client> getEachClient(){
        return this.clientRepository.findAll();
    }

    @GetMapping(value = "/getClient/{id}")
    public Optional<Client> getIdClient(@PathVariable Long id){return this.clientRepository.findById(id);}

    @PostMapping(value = "/newClient")
    public Client createNewClient(@RequestBody Client client) {
        this.clientRepository.save(client);
        return client;
    }
}
