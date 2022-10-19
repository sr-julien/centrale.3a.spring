package com.centrale.rest.domain;

public class Player {
    public Coup coup;

    public void setCoup(Coup coup) {
        this.coup = coup;
    }

    public int jeu(Player p){
        return this.coup.playWith(p.coup);
    }
}
