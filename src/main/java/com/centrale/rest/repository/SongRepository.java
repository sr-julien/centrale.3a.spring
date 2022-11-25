package com.centrale.rest.repository;

import com.centrale.rest.entity.SongEntity;
import org.springframework.data.repository.CrudRepository;

public interface SongRepository extends CrudRepository<SongEntity, Long> {
}
