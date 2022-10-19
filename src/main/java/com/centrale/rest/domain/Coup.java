package com.centrale.rest.domain;

public interface Coup {
    int playWith(Pierre p);
    int playWith(Feuille f);
    int playWith(Ciseaux c);
    int playWith(Coup c);
}
