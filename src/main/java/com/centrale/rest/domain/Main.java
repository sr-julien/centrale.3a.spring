package com.centrale.rest.domain;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Player joueur1 = new Player();
        Player joueur2 = new Player();
        Pierre p = new Pierre();
        Ciseaux c = new Ciseaux();
        Feuille f = new Feuille();
        Map<String, Hand> dict = new HashMap<String,Hand>();
        dict.put("feuille",f);dict.put("pierre",p);dict.put("ciseaux",c);
        Map<Integer, String> result = new HashMap<Integer, String>();
        result.put(-1, "Perdu");result.put(0, "Egalité");result.put(1, "Gagné");

        Scanner scanner = new Scanner(System.in);
        System.out.println("Coup du joueur 1 :");
        String choix1 = scanner.nextLine();

        joueur1.setMamain(dict.get(choix1));

        System.out.println("Coup du joueur 2 :");
        String choix2 = scanner.nextLine();
        joueur2.setMamain(dict.get(choix2));

        int jeu = joueur1.jeu(joueur2);
        System.out.println("le Joueur 1 à : ");
        System.out.println(result.get(jeu));
    }
}