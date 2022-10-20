package com.centrale.rest.domain;

public class Player {
    Hand hand;
    public Player(){
    }
    public void setMamain(Hand hand) {
        this.hand = hand;
    }

    @Override
    public String toString() {
        return "Player{" +
                "hand=" + hand +
                '}';
    }

    public int jeu(Player Joueur2){
        return this.hand.playWith(Joueur2.hand);
    }
}
