package com.centrale.rest.projetDomain;

public class Started implements State{


    private Series series;

    public Started(){};

    public Started(Series series){
        this.series=series;
    };


    public void start() {

        //this.series.episode = 0;

    }

    public void wish() {
        System.out.println("Can't wish a series that was already started");

    }

    public void drop() {
        this.series.state = new Dropped(this.series);

    }

    public void consume(int episodeWatched) {

        this.series.episode += episodeWatched;
        //System.out.println("consumption started, we are at " + this.series.episode );
        //System.out.println(this.series);
        if (this.series.episode >= this.series.totalEpisode) {

             this.finnish();

        }

    }

    public void finnish() {
        this.series.episode = this.series.totalEpisode;
        this.series.state = new Finnished(this.series);

    }

    public String toString(){
        return "Started";
    }

}
