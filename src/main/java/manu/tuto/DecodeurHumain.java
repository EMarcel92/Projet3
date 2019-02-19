package manu.tuto;

import java.util.Scanner;

public class DecodeurHumain extends Decodeur{
    Scanner sc = new Scanner(System.in);

    @Override
    public String proposition() {
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

}
