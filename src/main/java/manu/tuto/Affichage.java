package manu.tuto;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Affichage {
    Scanner sc = new Scanner(System.in);
    int LengthCypher = 3;     //Nombre de symboles du code secret
    int maxSymbol = 5;      // Nombre de valeurs possibles pour un symbole
    int nbTrials = 4;       //Nombre d'essais max possibles pour trouver la combinaison
    int gameNumber = 0;     //Type de jeu
    String gameMode = "";   //mode de jeu

    public void bienvenue () {
        System.out.println("Bienvenue dans Plus-Moins / Mastermind");
        //   System.out.println();
    }

    /**
     * Display available games.
     */
    public void displayAvailablegame() {
        System.out.println("Choix du jeu :");
        System.out.println("   1- Plus-Moins");
        System.out.println("   2- Mastermind");
        boolean responseIsFalse = true;
        while (responseIsFalse) {
            try {
                gameNumber = sc.nextInt();
                responseIsFalse = false;
            } catch (InputMismatchException e) {
                sc.next();
                System.out.println("Vous devez saisir un nombre 1 ou 2.");
                responseIsFalse = true;
            }
        }
        System.out.println("jeu n° " + gameNumber);
    }

}
