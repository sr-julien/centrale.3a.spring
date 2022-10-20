package com.centrale.rest.repository;

import com.centrale.rest.entity.StudentEntity;
import org.springframework.data.repository.CrudRepository;

public interface StudentRepository extends CrudRepository<StudentEntity, Long> {
    StudentEntity findStudentEntityByFirstname(String firstname);
}
