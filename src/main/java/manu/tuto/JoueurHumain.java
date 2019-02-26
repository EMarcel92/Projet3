package manu.tuto;

import java.util.Scanner;

public class JoueurHumain extends Joueur{
    Scanner sc = new Scanner(System.in);

    /**
     * Demande au joueur de saisir un code secret (en tant que codeur)
     */
    @Override
    public String genererCodeSecret() {
        String codeSecret = new String();
        Scanner sc = new Scanner(System.in);
        do {        // Boucle pour obtenir la saisie d'une proposition valide
            System.out.print("Entrez votre code secret :");
            codeSecret = sc.next();
            if (!checkSaisieOK(codeSecret)) {
                System.out.println("   Saisie incorrecte");
            }
        } while (checkSaisieOK(codeSecret)==false);
        return codeSecret;
    }

    @Override
    public void initialiserSolutionsPlusMoins() {
    }

    @Override
    public String proposition() {
        return null;
    }

    public String proposition(String evaluation) {
        return propositionHumaine();
    }

    @Override
    public String proposition(int evaluation) {
        return propositionHumaine();
    }

    public String propositionHumaine() {
        String uneProposition = "";
        do {        // Boucle pour obtenir la saisie d'une proposition valide
            System.out.print("proposition :");
            uneProposition = sc.next();
            if (!checkSaisieOK(uneProposition)) {
                System.out.println("   Saisie incorrecte");
            }
        } while (checkSaisieOK(uneProposition)==false);
        return uneProposition;
    }

    /**
     * Validation de la saisie d'une proposition
     * @param codeEntre La propostion faite en entrée
     * @return True si la proposition est valide
     */
    public boolean checkSaisieOK(String codeEntre) {
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
        String chaine = "";
        try {
            for (int i = 0; i < ParametresDuJeu.LONGUEUR_CODE_SECRET; i++) {
                if (codeEntre.charAt(i) > chaine.valueOf(ParametresDuJeu.NB_MAX_SYMBOLES-1).charAt(0))
                    monCheck =false;
            }
        } catch (Exception e) {
            monCheck =false;
        }
        //Vérification de la valeur d'un symbole
        return monCheck;
    }
}
