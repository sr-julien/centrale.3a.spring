package com.centrale.rest.controller;

import com.centrale.rest.entity.SchoolClassEntity;
import com.centrale.rest.entity.StudentDTO;
import com.centrale.rest.entity.StudentEntity;
import com.centrale.rest.repository.SchoolClassRepository;
import com.centrale.rest.repository.StudentRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@ResponseBody
@RequestMapping(value = "/student")
@AllArgsConstructor
public class StudentController {

    private StudentRepository studentRepository;
    private SchoolClassRepository schoolClassRepository;


    @GetMapping()
    public Iterable<StudentEntity> getStudents(){
        return this.studentRepository.findAll();
    }

    @GetMapping(value = "/{id}")
    public Optional<StudentEntity> getStudent(@PathVariable Long id){
        return this.studentRepository.findById(id);
    }

    @PostMapping()
    public void registerStudent(@RequestBody StudentDTO studentDTO){
        SchoolClassEntity schoolClass = this.schoolClassRepository.findById(studentDTO.class_id).get();

        StudentEntity student = new StudentEntity();
        student.setFirstname(studentDTO.firstname);
        student.setLastname(studentDTO.lastname);
        student.setSchoolClass(schoolClass);
        this.studentRepository.save(student);
    }
}
