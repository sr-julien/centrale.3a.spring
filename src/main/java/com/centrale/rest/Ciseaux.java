package com.centrale.rest;

public class Ciseaux implements ChoixInterface{


    public int playWith(Pierre pierre) {
        return -1;
    }


    public int playWith(Feuille feuille) {
        return 1;
    }


    public int playWith(Ciseaux ciseaux) {
        return 0;
    }

    public int playWith(ChoixInterface choix){
        return choix.playWith(this);
    }
}
