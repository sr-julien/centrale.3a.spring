package com.centrale.rest.repository;

import com.centrale.rest.entity.PostEntity;
import com.centrale.rest.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserRepository extends CrudRepository<UserEntity, Long> {
    public UserEntity findUserEntitiesByEmail(String email);
}
