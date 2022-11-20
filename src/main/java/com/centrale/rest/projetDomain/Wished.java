package com.centrale.rest.projetDomain;

public class Wished implements State{

    private Series series;

    public Wished(){};

    public Wished(Series series){
        this.series=series;
    };


    public void start() {
        this.series.state = new Started(this.series);
        this.series.episode = 0;

    }

    public void wish() {
        System.out.println("Can't wish when wished");

    }

    public void drop() {
        this.series.state = new Dropped(this.series);

    }

    public void consume(int episodeWatched) {

        System.out.println("can't consume when not started");

    }

    public void finnish() {

        this.series.episode = this.series.totalEpisode;
        this.series.state = new Finnished(this.series);

    }
    public String toString(){
        return "Wished";
    }

}
