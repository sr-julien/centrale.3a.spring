package com.centrale.rest.repository;

import com.centrale.rest.entity.ArtistEntity;
import org.springframework.data.repository.CrudRepository;

public interface ArtistRepository extends CrudRepository<ArtistEntity, Long> {
}
