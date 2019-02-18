package manu.tuto;

public class Jeu {
    private int typeJeu;             //Type de jeu
    private int modeJeu;             //mode de jeu
    private CodeSecret codeSecret = new CodeSecret();   //Code secret
    private  Affichage affichage;
    private Partie unePartie;
    public final int PLUSMOINS = 1;
    public final int MASTERMIND = 2;
    public final int CHALLENGER = 1;
    public final int DEFENSEUR = 2;
    public final int DUEL = 3;


    public Jeu() {
        affichage = new Affichage();
    }

    public void bienvenue() {
        System.out.println("******************************************");
        System.out.println("* Bienvenue dans Plus-Moins / Mastermind *");
        System.out.println("******************************************");
        System.out.println();
    }

    public void initialiserLeJeu () {
        typeJeu = affichage.selectionnerTypeJeu();
        modeJeu = affichage.selectionnerModeJeu();
        affichage.afficherRegles(typeJeu, modeJeu);
    }

    public void demarrerLeJeu(){
        switch (typeJeu) {
            case PLUSMOINS:


                PlusMoins plusMoins = new PlusMoins();
                plusMoins.initialiserUnePartie();
                plusMoins.jouerUnePartie();
                break;
            case MASTERMIND:
                System.out.println("Le jeu 2 n'existe pas ENCORE.");
                break;
            default:
                System.out.println("Valeur inatendue pour typeJeu : '" + typeJeu + "'");  //a mettre en log
                System.out.println("Le jeu souhaité n'existe pas.");
                break;
        }
    }


}
