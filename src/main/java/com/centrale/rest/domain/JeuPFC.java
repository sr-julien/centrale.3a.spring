package com.centrale.rest.domain;

import java.util.Objects;
import java.util.Scanner;

public class JeuPFC {
    public String playPFC(String choixJ1, String choixJ2) {

        Player OBJchoixJ1 = null;
        Player OBJchoixJ2 = null;

        if (Objects.equals(choixJ1, "P")) {
            OBJchoixJ1 = new Pierre();
        }
        if (Objects.equals(choixJ1, "F")) {
            OBJchoixJ1 = new Feuille();
        }
        if (Objects.equals(choixJ1, "C")) {
            OBJchoixJ1 = new Ciseaux();
        }
        if (Objects.equals(choixJ2, "C")) {
            OBJchoixJ2 = new Ciseaux();
        }
        if (Objects.equals(choixJ2, "P")) {
            OBJchoixJ2 = new Pierre();
        }
        if (Objects.equals(choixJ2, "F")) {
            OBJchoixJ2 = new Feuille();
        }
        int a = -(OBJchoixJ1.playWith(OBJchoixJ2));

        if (a == 0) {
            System.out.println("Both win");
            return "Both win";
        }
        if (a == 1) {
            System.out.println("J1 wins");
            return "J1 wins";
        }
        if (a == -1) {
            System.out.println("J2 wins");
            return "J2 wins";
        }
        return "Error";
    }
}
