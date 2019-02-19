package manu.tuto;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        //Intialisation des paramètres des jeux:
        chargementParametres();

        Scanner sc = new Scanner(System.in);
        Jeu jeu = new Jeu();
        jeu.bienvenue();   //Affichage d'un message d'accueil
        boolean rejouer = false;
        int choix = 2;
        do {  //tant que le joueur veut jouer
            jeu.initialiserLeJeu();
            do {  //Tant que le joueur veut faire le même jeu

                jeu.lancerLeJeu();

                System.out.println();
                System.out.println("Que souhaitez-vous faire ( 1- Rejouer  2- Autre jeu   3- Quitter) ? ");
                boolean reponseFausse = true;
                while (reponseFausse) {
                    try {
                        choix = sc.nextInt();
                        reponseFausse = false;
                        if (choix != 1 && choix != 2 && choix != 3) {
                            System.out.println("Vous devez saisir un des choix proposés (1, 2 ou 3).");
                            reponseFausse = true;
                        }
                    } catch (InputMismatchException e) {
                        sc.next();
                        System.out.println("Vous devez saisir un nombre.");
                        reponseFausse = true;
                    }
                }
            } while (choix == 1);
        } while (choix == 2);

  //      System.out.println(ParametresDuJeu.LONGUEUR_CODE_SECRET);
    }

    private static void chargementParametres(){
        //TODO lire les paramètres depuis le fichier de config

    }
}
