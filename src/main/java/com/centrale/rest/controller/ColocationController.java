package com.centrale.rest.controller;

import com.centrale.rest.entity.ColocationEntity;
import com.centrale.rest.repository.ColocationRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@ResponseBody
@RequestMapping(value = "/colocation")
@AllArgsConstructor
public class ColocationController {

    private ColocationRepository colocationRepository;

    @GetMapping(value = "/getColocations")
    public Iterable<ColocationEntity> getEachColocationEntity(){
        return this.colocationRepository.findAll();
    }

    @PostMapping(value = "/newColocation")
    public ColocationEntity createNewColocationEntity(@RequestBody ColocationEntity colocationEntity) {
        this.colocationRepository.save(colocationEntity);
        return colocationEntity;
    }

    @GetMapping(value = "/getColocation/{id}")
    public Optional<ColocationEntity> getIdColocationEntity(@PathVariable Long id){return this.colocationRepository.findById(id);}

}
