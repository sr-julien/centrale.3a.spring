package com.centrale.rest.repository;

import com.centrale.rest.entity.PostEntity;
import org.springframework.data.repository.CrudRepository;

public interface PostRepository extends CrudRepository<PostEntity, Long> {
    public PostEntity findPostEntitiesByTitle(String title);
}
