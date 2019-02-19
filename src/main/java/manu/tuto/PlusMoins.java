package manu.tuto;

import java.util.Scanner;

public class PlusMoins implements Partie {
    Scanner sc = new Scanner(System.in);
    CodeSecret codeSecret = new CodeSecret();

    @Override
    public void initialiserUnePartie(Codeur codeur, Decodeur decodeur) {
        codeur.genererCodeSecret();
        codeur.afficherCodeSecret();  //Affichage de la solution si mode développeur
//TODO initialiser le tableau des solutions possibles

    }

    /**
     * Déroulement d'une partie de PlusMoins
     * @see Partie
     * @param codeur en précisant un codeur de type humain ou ordinateur
     * @param decodeur en précisant un codeur de type humain ou ordinateur
     */
    @Override
    public void jouerUnePartie(Codeur codeur, Decodeur decodeur) {
        String evaluation;
        String proposition;
        int nbEssais = 0;
        do {    //Boucle jusqu'à trouver la solution ou atteindre le nombre max de tentatives
            proposition = decodeur.proposition();
            evaluation = codeur.evaluerProposition(proposition);
            System.out.println(evaluation);
            nbEssais++;
        } while (!cEstGagne(evaluation) && nbEssais < ParametresDuJeu.NB_MAX_ESSAIS);
        if (cEstGagne(evaluation)) {
            System.out.println("Le challenger a gagné : le code secret a été découvert");
        }else {
            System.out.println("Le challenger a perdu. " + codeur.toString());
        }
    }

    private boolean cEstGagne (String evaluation) {
        return evaluation.matches( "^=*$"); // C'est gagné si le résultat de l'évalusation est une suite de ===
    }
}
