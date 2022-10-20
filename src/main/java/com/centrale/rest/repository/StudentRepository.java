package com.centrale.rest.repository;

import com.centrale.rest.entity.StudentEntity;
import lombok.Getter;
import org.springframework.data.repository.CrudRepository;

import java.util.List;


public interface StudentRepository extends CrudRepository<StudentEntity, Long> {
    public List<StudentEntity> findByFirstname(String firstName);
}
