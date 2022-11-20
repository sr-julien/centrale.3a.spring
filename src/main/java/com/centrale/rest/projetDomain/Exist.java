package com.centrale.rest.projetDomain;

public class Exist implements State{

    private Series series;

    public Exist(){};

    public Exist(Series series){
        this.series=series;
    };


    public void start() {

        this.series.episode = 0;
        this.series.state = new Started(this.series);

    }

    public void wish() {
        this.series.state = new Wished(this.series);

    }

    public void drop() {
        this.series.state = new Dropped(this.series);

    }

    public void consume(int episodeWatched) {

        System.out.println("Can't consume when not started");

    }

    public void finnish() {
        System.out.println("Can't finnish when not started");

    }

    public String toString(){
        return "Exist";
    }

}
