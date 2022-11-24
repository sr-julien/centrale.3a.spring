package com.centrale.rest;

import com.centrale.rest.entity.SchoolClassEntity;
import com.centrale.rest.entity.StudentEntity;
import com.centrale.rest.repository.PersonRepository;
import com.centrale.rest.repository.SchoolClassRepository;
import com.centrale.rest.entity.PersonEntity;
import com.centrale.rest.entity.ColocationEntity;
import com.centrale.rest.repository.ColocationRepository;
import com.centrale.rest.repository.StudentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.HashSet;


@SpringBootApplication
public class RestApplication {

    private static final Logger log = LoggerFactory.getLogger(RestApplication.class);
    public static void main(String[] args) {
        SpringApplication.run(RestApplication.class, args);
    }

    // Simply to execute some code after startup
    @Bean
    public CommandLineRunner demo(SchoolClassRepository schoolClassRepository, PersonRepository personRepository, ColocationRepository colocationRepository) {
        return (args) -> {
            log.info("Create and save school class");
            SchoolClassEntity schoolClass = new SchoolClassEntity();
            schoolClass.setName("3A Info");
            StudentEntity studentEntity = new StudentEntity();
            studentEntity.setFirstname("John");
            studentEntity.setLastname("Doe");
            studentEntity.setSchoolClass(schoolClass);
            HashSet<StudentEntity> students = new HashSet<>();
            students.add(studentEntity);
            schoolClass.setStudents(students);
            schoolClassRepository.save(schoolClass);



            ColocationEntity colocation=new ColocationEntity();
            colocation.setName("KoussKouss");

            PersonEntity personEntity = new PersonEntity();
            personEntity.setName("John");
            personEntity.setColocation(colocation);

            HashSet<PersonEntity> persons = new HashSet<>();
            persons.add(personEntity);
            colocation.setPersons(persons);

            colocationRepository.save(colocation);

            personRepository.save(personEntity);




        };
    }


}
