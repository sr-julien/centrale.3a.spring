package com.centrale.rest.controller;


import com.centrale.rest.StudentDTO;
import com.centrale.rest.entity.SchoolClassEntity;
import com.centrale.rest.service.DataService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import com.centrale.rest.repository.StudentRepository;
import com.centrale.rest.entity.StudentEntity;
import com.centrale.rest.repository.SchoolClassRepository;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

@Controller
@ResponseBody
@RequestMapping(value = "")
@AllArgsConstructor
public class BasicController {

    private DataService dataService;

    private StudentRepository studentRepository;

    private SchoolClassRepository schoolClassRepository;

    @RequestMapping(value="/gotoWelcome",method = RequestMethod.GET)
    public ModelAndView gotoNextPage(HttpServletRequest request, HttpServletResponse response){
        System.out.println("Inside gotoNextPage!!!!!!");

        ModelMap model = new ModelMap();
        //model.addAttribute("message", "next page");
        return new ModelAndView(
                new RedirectView("/welcome.html", true),
                model
        );
    }


    @GetMapping(value =  "/test")
    @ResponseBody
    public String index(Model model) {
    SchoolClassEntity schoolClass = schoolClassRepository.findSchoolClassEntityByName("3A INFO");
    model.addAttribute(schoolClass);
    return "index";
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
