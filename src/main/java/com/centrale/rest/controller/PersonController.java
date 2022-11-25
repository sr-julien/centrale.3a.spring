package com.centrale.rest.controller;

import com.centrale.rest.entity.Person;
import com.centrale.rest.repository.PersonRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@ResponseBody
@RequestMapping(value = "/person")
@AllArgsConstructor
public class PersonController {

    private PersonRepository personRepository;

    @GetMapping(value = "/getPersons")
    public Iterable<Person> getPersons(){
        return this.personRepository.findAll();
    }

    @GetMapping(value = "/getPerson/{id}")
    public Optional<Person> getPersonById(@PathVariable Long id){return this.personRepository.findById(id);}

    @PostMapping(value = "/newPerson")
    public Person createNewPerson(@RequestBody Person person) {
        this.personRepository.save(person);
        return person;
    }
}
