package com.centrale.rest.repository;

import com.centrale.rest.entity.SchoolClassEntity;
import org.springframework.data.repository.CrudRepository;

public interface LoanRepository  extends CrudRepository<SchoolClassEntity, Long>{
}
