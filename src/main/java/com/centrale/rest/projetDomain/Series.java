package com.centrale.rest.projetDomain;

import java.util.Date;

public class Series {

    String name;

    int episode;

    int totalEpisode;

    State state;

    public Series(){
        this.state= new Exist(this);
        this.episode=0;
        this.totalEpisode=100;
    };

    public Series(String name, int totalEpisode){
        this.name=name;
        this.totalEpisode=totalEpisode;
        this.episode=0;
        this.state= new Exist(this);
    };

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getEpisode() {
        return this.episode;
    }

    public void setEpisode(int episode) {
        this.episode = episode;
    }

    public int getTotalEpisode() {
        return this.totalEpisode;
    }

    public void setTotalEpisode(int totalEpisode) {
        this.totalEpisode = totalEpisode;
    }

    public State getState() {
        return this.state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public void start() {
        this.state.start();
    }

    public void wish() {
        this.state.wish();

    }

    public void drop() {
        this.state.drop();

    }

    public void consume(int episodeWatched) {
        this.state.consume(episodeWatched);
        //System.out.println("consumption finnished,we watched "+this.episode);

    }

    public void finnish() {
        this.state.finnish();

    }

}
