package com.centrale.rest.repository;

import com.centrale.rest.PostDTO;
import com.centrale.rest.entity.PostEntity;
import com.centrale.rest.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PostRepository extends CrudRepository<PostEntity, Long> {
    public PostEntity findPostEntitiesByTitle(String title);
    public PostEntity findPostEntityByTitleAndAuthor (String title, UserEntity author);
    public List<PostEntity> findAllByAuthor(UserEntity author);
}
