package com.centrale.rest.entity;

import com.centrale.rest.projetDomain.Series;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor

public class SeriesEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;
    private String state;
    private int episode;
    private int totalEpisode;

    public SeriesEntity(Series series)
    {
        this.name= series.getName();
        this.totalEpisode= series.getTotalEpisode();
        this.state = series.getState().toString();
        this.episode= series.getEpisode();
    }

    public void update(Series series){
        this.name= series.getName();
        this.totalEpisode= series.getTotalEpisode();
        this.state = series.getState().toString();
        this.episode= series.getEpisode();
    }


}
