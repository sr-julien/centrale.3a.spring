package com.centrale.rest;

public interface ChoixInterface {

    public int playWith(Pierre pierre);
    public int playWith(Feuille feuille);
    public int playWith(Ciseaux ciseaux);

    public int playWith(ChoixInterface choix);


}
