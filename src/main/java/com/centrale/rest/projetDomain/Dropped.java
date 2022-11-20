package com.centrale.rest.projetDomain;

public class Dropped implements State {

    private Series series;

    public Dropped(){};

    public Dropped(Series series){
        this.series=series;
    };


    public void start() {
        this.series.state = new Started(this.series);
        this.series.episode = 0;

    }

    public void wish() {
        this.series.state=new Wished(this.series);

    }

    public void drop() {
        System.out.println("Can't drop when dropped");

    }

    public void consume(int episodeWatched) {
        System.out.println("Can't consume when dropped");

    }

    public void finnish() {
        System.out.println("Can't finnish when dropped");

    }

    public String toString(){
        return "Dropped";
    }

}
