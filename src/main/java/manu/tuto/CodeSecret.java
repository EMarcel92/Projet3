package manu.tuto;

import java.util.ArrayList;
import java.util.Random;


/*Gestion du code secret à découvrir
    - Génération
    - Affichage
*/
public class CodeSecret {
    private ArrayList<Character> codeTableau;

    public CodeSecret() {
        this.codeTableau = codeTableau;
    }

    public ArrayList<Character> getCodeTableau() {
        return codeTableau;
    }

    public void setCodeTableau(ArrayList<Character> codeTableau) {
        this.codeTableau = codeTableau;
    }

    @Override
    public String toString() {
        String maString = "";
        for (Character myChar : this.getCodeTableau()) {
            maString += myChar;
        }
        return "Le code secret est " + maString ;
    }

    public void genererCodeSecret() {
        Random rnd = new Random();
        ArrayList<Character > unCodeSecret = new ArrayList<>();

        unCodeSecret.add(String.valueOf(rnd.nextInt(ParametresDuJeu.NB_MAX_SYMBOLES)+1).charAt(0)); //Initialisation du premier symbole du code secret
        int i=0;
        boolean DoublonDansLeCode;
        char unCar = ' ';
        while (i < ParametresDuJeu.LONGUEUR_CODE_SECRET-1) {   // Création d'un code secret de longueur paramétrée
            DoublonDansLeCode = true;
            while (DoublonDansLeCode) {   //Génération d'un code secret sans doublon
                unCar = String.valueOf(rnd.nextInt(ParametresDuJeu.NB_MAX_SYMBOLES)+1).charAt(0);
                DoublonDansLeCode=false;
                for (int j = 0; j < ParametresDuJeu.LONGUEUR_CODE_SECRET-1 ; j++) {
                    if (unCodeSecret.contains(unCar)) { DoublonDansLeCode=true;}
                }
            }
            unCodeSecret.add(unCar);
            i++;
        }
        this.setCodeTableau(unCodeSecret);
    }

    public void afficherCodeSecret (CodeSecret codeSecret) {
 //       param = new ParametresDuJeu();
        if (ParametresDuJeu.MODE_DEV) {
            System.out.println("!!!!!! mode développeur - code = '" + codeSecret.getCodeTableau() + "' !!!!!!");  //TODO Bug : perte du code secret ??
        }
    }


    public String evaluerProposition(String proposition, ArrayList<Character> codeSecret) {
        String reponse = "";
        for (int i = 0; i < ParametresDuJeu.LONGUEUR_CODE_SECRET; i++) {
            if (proposition.charAt(i) == codeSecret.get(i))  {
                reponse += "=";
            }else {
                if (proposition.charAt(i) < codeSecret.get(i)){
                    reponse += "+";
                }else{
                    if (proposition.charAt(i) > codeSecret.get(i)){
                        reponse += "-";
                    }
                }
            }
        }
        return reponse;
    }
}
