package com.centrale.rest.projetDomain;

public class Finnished implements State {

    private Series series;

    public Finnished(){};

    public Finnished(Series series){
        this.series=series;
    };


    public void start() {
        this.series.state = new Started(this.series);
        this.series.episode = 0;

    }

    public void wish() {
        System.out.println("Can't wish a series that was already started");

    }

    public void drop() {
        System.out.println("Can't drop when finnished");

    }

    public void consume(int episodeWatched) {
        System.out.println("Can't consume finnished state");

    }

    public void finnish() {

        System.out.println("Cant finnish when finnished");

    }

    public String toString(){
        return "Finished";
    }

}
