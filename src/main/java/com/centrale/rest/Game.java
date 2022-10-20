package com.centrale.rest;

import java.util.Scanner;

public class Game {

    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);

        System.out.println("j1 ?");
        String j1 = sc.nextLine();
        System.out.println("j2 ?");
        String j2 = sc.nextLine();

        ChoixInterface a = null;
        ChoixInterface b = null;

        switch (j1) {
            case "pierre" -> {
                 a = new Pierre();
                break;
            }
            case "feuille" -> {
                 a = new Feuille();
                break;
            }
            case "ciseaux" -> {
                 a = new Ciseaux();
                break;
            }
        }

        switch (j2) {
            case "pierre" -> {
                 b = new Pierre();
                break;
            }
            case "feuille" -> {
                 b = new Feuille();
                break;
            }
            case "ciseaux" -> {
                 b = new Ciseaux();
                break;
            }
        }

        int result = a.playWith(b);
        System.out.println(result);
    }

}
