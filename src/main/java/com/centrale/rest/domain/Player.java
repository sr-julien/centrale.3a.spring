package com.centrale.rest.domain;

public interface Player {
    public int playWith(Pierre p);
    public int playWith(Feuille f);
    public int playWith(Ciseaux c);
    public int playWith(Player p);
}
