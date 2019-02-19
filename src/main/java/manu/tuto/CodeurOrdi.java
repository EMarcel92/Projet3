package manu.tuto;

import java.util.ArrayList;
import java.util.Random;

public class CodeurOrdi extends Codeur {

    @Override
    public void genererCodeSecret() {
        Random rnd = new Random();
        ArrayList<Character > unCodeSecret = new ArrayList<>();

    //    unCodeSecret.add(String.valueOf(rnd.nextInt(ParametresDuJeu.NB_MAX_SYMBOLES)+1).charAt(0)); //Initialisation du premier symbole du code secret
        int i=0;
        char unCar;
        do {   // Création d'un code secret de longueur paramétrée
            unCar = String.valueOf(rnd.nextInt(ParametresDuJeu.NB_MAX_SYMBOLES)+1).charAt(0);
            unCodeSecret.add(unCar);
            i++;
        }while (i < ParametresDuJeu.LONGUEUR_CODE_SECRET);
        this.setCodeTableau(unCodeSecret);
    }

    /**
     * Comparer la proposition passée en paramètre par rapport au code secret
     * @param proposition faite par le challenger
     * @return Le résultat de l'évaluation sous forme d'une phrase en français
     */
    @Override
    public String evaluerProposition(String proposition) {
        String reponse = "";
        for (int i = 0; i < ParametresDuJeu.LONGUEUR_CODE_SECRET; i++) {
            if (proposition.charAt(i) == this.getCodeTableau().get(i))  {
                reponse += "=";
            }else {
                if (proposition.charAt(i) < this.getCodeTableau().get(i)){
                    reponse += "+";
                }else{
                    if (proposition.charAt(i) > this.getCodeTableau().get(i)){
                        reponse += "-";
                    }
                }
            }
        }
        return reponse;
    }

    @Override
    public boolean cEstGagne(String evaluation) {
        return false;
    }
}
