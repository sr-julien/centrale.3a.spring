package com.centrale.rest.controller;


import com.centrale.rest.entity.SchoolClassEntity;
import com.centrale.rest.entity.StudentEntity;
import com.centrale.rest.repository.SchoolClassRepository;
import com.centrale.rest.repository.StudentRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@Controller
@ResponseBody
@RequestMapping(value = "/school")
@AllArgsConstructor
public class StudentControler {

    private StudentRepository studentRepository;
    private SchoolClassRepository schoolClassRepository;

    @GetMapping(value = "/getSchool")
    public Iterable<SchoolClassEntity> getEverySchool(){
        return this.schoolClassRepository.findAll();
    }

    @GetMapping(value = "/getStudent")
    public Iterable<StudentEntity> getEveryBody(){
        return this.studentRepository.findAll();
    }

    @PostMapping(value = "/newStudent")
    public StudentEntity newStudent(@RequestBody StudentEntity stud){
        this.studentRepository.save(stud);
        return stud;
    }
}
