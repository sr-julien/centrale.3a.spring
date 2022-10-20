package com.centrale.rest.domain;

public class Pierre implements Hand {
    public Pierre(){

    }
    public int playWith(Pierre p) {
        return 0;
    }
    public int playWith(Feuille f) {
        return -1;
    }
    public int playWith(Ciseaux c) {
        return 1;
    }
    public int playWith(Hand h) {
        return -(h.playWith(this));
    }
}
