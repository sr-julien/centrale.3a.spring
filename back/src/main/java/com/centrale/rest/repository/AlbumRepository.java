package com.centrale.rest.repository;

import com.centrale.rest.entity.AlbumEntity;
import org.springframework.data.repository.CrudRepository;

public interface AlbumRepository extends CrudRepository<AlbumEntity, Long> {
}
