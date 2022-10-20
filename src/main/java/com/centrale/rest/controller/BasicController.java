package com.centrale.rest.controller;


import com.centrale.rest.StudentDTO;
import com.centrale.rest.entity.SchoolClassEntity;
import com.centrale.rest.service.DataService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.centrale.rest.repository.StudentRepository;
import com.centrale.rest.entity.StudentEntity;
import com.centrale.rest.repository.SchoolClassRepository;


import java.util.List;
import java.util.Map;

@Controller
@ResponseBody
@RequestMapping(value = "random")
@AllArgsConstructor
public class BasicController {

    private DataService dataService;

    private StudentRepository studentRepository;

    private SchoolClassRepository schoolClassRepository;

    @GetMapping(value = "/double")
    public double getRandomDouble(){
        return Math.random() * 10D;
    }

    @GetMapping(value = "/long/{min}/{max}")
    public Long getRandomIntFromInterval(@PathVariable int min, @PathVariable int max){
        Long random =  Math.round(Math.random() * (max - min) + min);
        dataService.addOccurrence(random);
        return random;
    }

    @GetMapping(value = "/statistics")
    public Map<Long, Integer> getStatisticsSeries(){
        return dataService.getOccurences();
    }

    @GetMapping(value = "/students/{id}")
    public String getFirstNameById(@PathVariable Long id){
        return studentRepository.findById(id).get().getFirstname();
    }

    @PostMapping(value = "/students/register")
    public StudentEntity postStudent(@RequestBody StudentDTO studentDTO){
        SchoolClassEntity schoolClass = schoolClassRepository.findSchoolClassEntityByName(studentDTO.getClassName());
        StudentEntity student = new StudentEntity();
        student.setFirstname(studentDTO.getFirstname());
        student.setLastname(studentDTO.getLastname());
        student.setSchoolClass(schoolClass);
        studentRepository.save(student);
        return student;
    }

}
