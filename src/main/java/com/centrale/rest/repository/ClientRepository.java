package com.centrale.rest.repository;

import com.centrale.rest.entity.Client;
import org.springframework.data.repository.CrudRepository;

public interface ClientRepository  extends CrudRepository<Client, Long>{
}
