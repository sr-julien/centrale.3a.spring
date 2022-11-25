package com.centrale.rest.repository;

import com.centrale.rest.entity.Person;
import org.springframework.data.repository.CrudRepository;

public interface PersonRepository  extends CrudRepository<Person, Long>{
}
