package manu.tuto;

import java.util.Random;

public class CodeurOrdi extends Codeur {

    /**
     * Génération d'un code secret par l'ordinateur
     */
    @Override
    public void genererCodeSecret() {
        Random rnd = new Random();
        String unCodeSecret = "";
        int i=0;
        char unCar;
        do {   // Création d'un code secret de longueur paramétrée
            unCar = String.valueOf(rnd.nextInt(ParametresDuJeu.NB_MAX_SYMBOLES)).charAt(0);
            unCodeSecret += unCar;
            i++;
        }while (i < ParametresDuJeu.LONGUEUR_CODE_SECRET);
        this.setCodeSecret(unCodeSecret);
    }

}
