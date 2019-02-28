package manu.tuto;

import java.util.Scanner;

/**
 * Joueur de Jeu humain, qui joue contre l'ordinateur en réalisant des interactions avec l'application par des saisies clavier
 * Il peut avoir les rôles codeur et/ou décodeur
 */
public class JoueurHumain extends Joueur{

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
        return codeSecret;    }

    @Override
    public String proposition(String evaluation) {
        return propositionHumaine();
    }

    @Override
    public String proposition(int evaluation) {
        return propositionHumaine();
    }


    public String propositionHumaine() {
        Scanner sc = new Scanner(System.in);
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
        //String chaine = "";
        try {
            for (int i = 0; i < ParametresDuJeu.LONGUEUR_CODE_SECRET; i++) {
                if (codeEntre.charAt(i) > String.valueOf(ParametresDuJeu.NB_MAX_SYMBOLES-1).charAt(0))
                    monCheck =false;
            }
        } catch (Exception e) {
            monCheck =false;
        }
        //Vérification de la valeur d'un symbole
        return monCheck;
    }

    /**
     * Saisie d'une évaluation (correction) par le joueur humain
     * @param proposition la proposition
     * @param codeSecret le code de référence à laquelle il faut comparer la proposition
     * @return le résultat de l'évaluation sur 2 chiffres
     */
    @Override
    public int evaluerProposition(String proposition, String codeSecret) {
        Scanner sc = new Scanner(System.in);
        String uneEvaluation = "";
        do {        // Boucle pour obtenir la saisie d'une évaluation valide
            System.out.print("Evaluation :");
            uneEvaluation = sc.next();
            if (!checkEvaluationOK(uneEvaluation)) {
                System.out.println("   Saisie incorrecte (2 chiffres attendus) ");
            }
        } while (checkEvaluationOK(uneEvaluation)==false);
        return Integer.valueOf(uneEvaluation);
    }

    /**
     * Validation de la saisie d'une évaluation
     * @param codeEntre L'évaluation saisie
     * @return True si le format de l'évaluation est valide
     */
    public boolean checkEvaluationOK(String codeEntre) {
        boolean monCheck = true;
        if (codeEntre.length() != 2){   //Vérification de la longueur du code
            monCheck =false;
        }
        try {
            Integer numericEntry = new Integer(codeEntre);               // Vérification de la numéricité
            int i = numericEntry.intValue(); // conversion d'Integer en int
        } catch (NumberFormatException e) {
            monCheck =false;
        }
        return monCheck;
    }
}
