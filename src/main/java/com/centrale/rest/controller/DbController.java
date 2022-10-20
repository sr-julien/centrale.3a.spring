package com.centrale.rest.controller;

import com.centrale.rest.domain.*;
import com.centrale.rest.entity.*;
import com.centrale.rest.repository.SchoolClassRepository;
import com.centrale.rest.repository.StudentRepository;
import com.centrale.rest.service.DataService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Controller
@ResponseBody
@RequestMapping(value = "db")
@AllArgsConstructor
public class DbController {

    private StudentRepository studentrepository;

    @GetMapping(value = "/students")
    public Iterable<StudentEntity> GetAllStudents() {
        Iterable<StudentEntity> student = studentrepository.findAll();

        return student;
    }
    @GetMapping(value = "/studente/{id}")
    public Map<StudentEntity,SchoolClassEntity> GetStudent(@PathVariable long id) {
        StudentEntity student = studentrepository.findById(id).get();
        SchoolClassEntity school = student.getSchoolClass();
        Map<StudentEntity,SchoolClassEntity> res = new HashMap<>();
        res.put(student,school);

        return res;
    }

    @GetMapping(value = "/student/{id}")
    public Optional<StudentEntity> GetStudentee(@PathVariable long id) {
        Optional<StudentEntity> student = studentrepository.findById(id);

        return student;
    }

    @GetMapping(value = "/schoolClass/id/{id}")
    public SchoolClassEntity GetSchool(@PathVariable long id) {
        StudentEntity student = studentrepository.findById(id).get();
        SchoolClassEntity school = student.getSchoolClass();
        return school;
    }

    @GetMapping(value = "/schoolClass/name/{name}")
    public SchoolClassEntity GetSchool(@PathVariable String name) {
        StudentEntity student = studentrepository.findStudentEntityByFirstname(name);
        SchoolClassEntity school = student.getSchoolClass();
        return school;
    }




}
