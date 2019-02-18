package manu.tuto;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Affichage {
    private static final int GAME_MODE_CHALLENGER = 1;   // a tester
    ParametresDuJeu param;
    CodeSecret codeSecret = new CodeSecret();
    Scanner sc = new Scanner(System.in);
    private int typeDeJeu;     //Type de jeu
    private int modeDeJeu;   //mode de jeu
    private boolean reponseFausse = true;  //peut-on déclarer ici pour utiliser dans plusieurs méthodes ??



    /**
     * Display available games.
     */
    public int selectionnerTypeJeu() {
        System.out.println("   1- Plus-Moins");
        System.out.println("   2- Mastermind");
        System.out.println("Choix du jeu :");
        reponseFausse = true;
        while (reponseFausse) {
            try {
                typeDeJeu = sc.nextInt();
                reponseFausse = false;
                if (typeDeJeu != 1 && typeDeJeu != 2) {   //A modifier si la liste des jeux peut évoluer
                    System.out.println("Vous devez saisr un des choix proposés (1 ou 2).");
                    reponseFausse = true;
                }
            } catch (InputMismatchException e) {
                sc.next();
                System.out.println("Vous devez saisir un nombre.");
                reponseFausse = true;
            }
        }
        return typeDeJeu;
    }

    public int selectionnerModeJeu() {

       System.out.println("   1- Challenger (vous trouvez la combisaison de l'ordinateur)");  //utiliser des libellés d'Enum ??
       System.out.println("   2- Défenseur (l'ordinateur trouve votre combinaison)");
       System.out.println("   3- Duel (vous affrontez l'ordinateur au tour par tour)");
       System.out.println("Choix du mode de jeu :");
       reponseFausse = true;
       while (reponseFausse) {
           try {
               modeDeJeu = sc.nextInt();
               reponseFausse = false;      // comment comparer l'entrée avec des Enum ?? faire un switch sur modeDeJeu et sélectionner un Enum ??
               if (modeDeJeu != 1 && modeDeJeu != 2 && modeDeJeu != 3) {   //A modifier si la liste des modes peut évoluer
                   System.out.println("Vous devez saisr un des choix proposés (1, 2 ou 3).");
                   reponseFausse = true;
               }
           } catch (InputMismatchException e) {
               sc.next();
               System.out.println("Vous devez saisir un nombre.");
               reponseFausse = true;
           }
       }
       return modeDeJeu;
    }

    public void afficherRegles(int selectedGameType, int selectedGameMode) {
        param = new ParametresDuJeu();
        System.out.println("Jeu n° " + selectedGameType + " en mode " + selectedGameMode);
        System.out.println(param.NB_MAX_ESSAIS + " essais pour trouver un code secret de " + param.LONGUEUR_CODE_SECRET + " chiffres.");
        System.out.println("Les chiffres possibles vont de 1 à " + param.NB_MAX_SYMBOLES + ".");
        System.out.println("Il n'y a pas de valeur en double.");
    }

}
