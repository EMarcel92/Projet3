package manu.tuto;

import java.util.ArrayList;

/**
 * Joueur (humain ou ordi) chargé de :
 * <ul>
 *     <li>générer un code</li>
 *     <li>évaluer les propositions</li>
 *     <li>dire si la partie est gagnée</li>
 * </ul>
 */
public abstract class Codeur {
    private ArrayList<Character> codeTableau;

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

    public abstract void genererCodeSecret();

    public abstract String evaluerProposition (String proposition);

    public abstract boolean cEstGagne (String evaluation);

    public void afficherCodeSecret () {
        if (ParametresDuJeu.MODE_DEV) {
            System.out.println("!!!!!! mode développeur - " + this.toString() + "' !!!!!!");
        }
    }

}
