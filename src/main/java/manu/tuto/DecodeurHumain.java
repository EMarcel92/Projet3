package manu.tuto;

import java.util.Scanner;

public class DecodeurHumain extends Decodeur{
    Scanner sc = new Scanner(System.in);

    @Override
    public void initialiserSolutions() {
    }

    /**
     * Demander à un décodeur humain de faire une proposition (tentative de décodage)
     * @param resultatDernierTour inutilisé (héritage de la classe Decodeur)
     * @return Une nouvelle proposition
     */
    @Override
    public String proposition(String resultatDernierTour) {
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
        for (int i = 0; i < ParametresDuJeu.LONGUEUR_CODE_SECRET; i++) {
            if (codeEntre.charAt(i) > chaine.valueOf(ParametresDuJeu.NB_MAX_SYMBOLES-1).charAt(0))
                monCheck =false;
        }
        //Vérification de la valeur d'un symbole
        return monCheck;
    }
}
