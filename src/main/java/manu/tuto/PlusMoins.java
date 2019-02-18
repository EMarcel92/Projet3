package manu.tuto;

import java.util.Scanner;

public class PlusMoins implements Partie {
    Scanner sc = new Scanner(System.in);
    CodeSecret codeSecret = new CodeSecret();

    @Override
    public void initialiserUnePartie() {
        codeSecret.genererCodeSecret();
        codeSecret.afficherCodeSecret(codeSecret);  //Affichage si mode développeur
    }
//TODO initialiser le tableau des solutions possibles

    @Override
    public void jouerUnePartie() {
        String evaluation;
        String proposition;
        int nbEssais = 0;
        do {    //Boucle jusqu'à trouver la solution ou atteindre le nombre max de tentatives
            proposition = faireUneProposition();
            evaluation = codeSecret.evaluerProposition(proposition, codeSecret.getCodeTableau());
            System.out.println(evaluation);
            nbEssais++;

        } while (!cEstGagne(evaluation) && nbEssais < ParametresDuJeu.NB_MAX_ESSAIS);
        if (cEstGagne(evaluation)) {
            System.out.println("Le challenger a gagné : le code secret a été découvert");
        }else {
            System.out.println("Le challenger a perdu. " + codeSecret.toString());
        }
    }

    private String faireUneProposition () {
        String uneProposition = "";
        do {        // Boucle pour obtenir la saisie d'une proposition valide
            System.out.println("proposition :");
            uneProposition = sc.next();
            if (!checkSaisieOK(uneProposition)) {
                System.out.println("   Saisie incorrecte");
            }
        } while (checkSaisieOK(uneProposition)==false);
        return uneProposition;
    }

    private boolean checkSaisieOK(String codeEntre) {
        boolean monCheck = true;
        if (codeEntre.length() != ParametresDuJeu.LONGUEUR_CODE_SECRET){   //Vérification de la longueur du code
            monCheck =false;
        }
        try {
            Integer numericEntry = new Integer(codeEntre);               // Vérification de la numéricité
            int i = numericEntry.intValue(); // conversion d'Integer en int
        } catch (NumberFormatException e) {
            monCheck =false;
        }
        //TODO checker la valeur max d'un symbole
        return monCheck;
    }

    private boolean cEstGagne (String evaluation) {
        return evaluation.matches( "^=*$"); // C'est gagné si le résultat de l'évalusation est une suite de ===
    }
}
