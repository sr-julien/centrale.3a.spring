package com.centrale.rest.domain;

public interface Hand {
    public int playWith(Pierre p);
    public int playWith(Feuille p);
    public int playWith(Ciseaux p);
    public int playWith(Hand h);
}
