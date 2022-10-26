package com.centrale.rest.repository;

import com.centrale.rest.entity.StudentEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;

public interface StudentRepository extends CrudRepository<StudentEntity, Long> {
    ArrayList<StudentEntity> findAllById(Long id);
}
