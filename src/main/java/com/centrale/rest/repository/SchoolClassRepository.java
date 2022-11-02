package com.centrale.rest.repository;

import com.centrale.rest.entity.SchoolClassEntity;
import com.centrale.rest.entity.StudentEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface SchoolClassRepository extends CrudRepository<SchoolClassEntity, Long> {
    public SchoolClassEntity findSchoolClassEntityByName(String Name);
}
