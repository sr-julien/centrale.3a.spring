package com.centrale.rest.domain;

import lombok.Data;

@Data
public class Player {
    private int id;
    private String firstName;
    private String lastName;
    public Coup coup;

    public void setCoup(Coup coup) {
        this.coup = coup;
    }

    public int jeu(Player p){
        return this.coup.playWith(p.coup);
    }
}
