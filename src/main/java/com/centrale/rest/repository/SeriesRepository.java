package com.centrale.rest.repository;

import com.centrale.rest.entity.SeriesEntity;
import org.springframework.data.repository.CrudRepository;

public interface SeriesRepository extends CrudRepository<SeriesEntity, Long> {

    Iterable<SeriesEntity> findSeriesEntitiesByStateIs(String state);
    Iterable<SeriesEntity> findSeriesEntitiesByTotalEpisodeLessThan(int length);

}
