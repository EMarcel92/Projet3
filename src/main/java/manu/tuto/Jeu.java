package manu.tuto;

import org.apache.log4j.Logger;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Jeu {
    private int typeJeu;             //Type de jeu
    private int modeJeu;             //mode de jeu
    private Partie unePartie;
    public final int PLUSMOINS = 1;
    public final int MASTERMIND = 2;
    public final int CHALLENGER = 1;
    public final int DEFENSEUR = 2;
    public final int DUEL = 3;
    Scanner sc = new Scanner(System.in);
    private int typeDeJeu;     //Type de jeu (variable locale)
    private int modeDeJeu;     //mode de jeu (variable locale)
    private boolean reponseFausse = true;  //peut-on déclarer ici pour utiliser dans plusieurs méthodes ??
    Codeur codeur;     // Déclaration d'un codeur générique (classe abstraite)
    Decodeur decodeur; // Idem pour le décodeur
    private static Logger logger = Logger.getLogger(Main.class);

    /**
     * Lanceur du jeu (logiciel) : détermination de la partie (type et mode de jeu) à exécuter
     */
    public void lancerLeJeu(){
        logger.warn("lancerLeJeu");
        //Intialisation des paramètres des jeux (fichier de paramétrage)
        ParametresDuJeu.intialiserLesParametres();
        bienvenue();   //Affichage d'un message d'accueil
        boolean rejouer = false;
        int choix = 2;
        do {  //tant que le joueur veut continuer à jouer
            initialiserLeJeu();
            do {  //Tant que le joueur veut faire le même jeu
                lancerUnePartie(); // Lance une partie d'un jeu donné
                System.out.println();
                System.out.println("Que souhaitez-vous faire ( 1- Rejouer  2- Autre jeu   3- Quitter) ? ");
                boolean reponseFausse = true;
                while (reponseFausse) {
                    try {
                        choix = sc.nextInt();
                        reponseFausse = false;
                        if (choix != 1 && choix != 2 && choix != 3) {
                            System.out.println("Vous devez saisir un des choix proposés (1, 2 ou 3).");
                            reponseFausse = true;
                        }
                    } catch (InputMismatchException e) {
                        sc.next();
                        System.out.println("Vous devez saisir un nombre.");
                        reponseFausse = true;
                    }
                }
            } while (choix == 1);
        } while (choix == 2);
    }

    /**
     * Message d'accueil (afficher une seule fois au démarrage
     */
    public void bienvenue() {
        System.out.println("******************************************");
        System.out.println("* Bienvenue dans Plus-Moins / Mastermind *");
        System.out.println("******************************************");
        System.out.println();
    }

    /**
     * Proposition à l'utilisateur du type et du mode de jeu et affichage des règles
     */
    public void initialiserLeJeu () {
        typeJeu = selectionnerTypeJeu();
        modeJeu = selectionnerModeJeu();
        afficherRegles(typeJeu, modeJeu);
    }

    /**
     * Lance et déroule une partie (PlusMoins ou Mastermind) en fonction des choix précédents de l'utilisateur
     */
    public void lancerUnePartie(){
        switch (typeJeu) {
            case PLUSMOINS:
                PlusMoins plusMoins = new PlusMoins();
                switch (modeJeu){
                    case CHALLENGER: //L'humain attaque l'ordi
                        codeur= new CodeurOrdi();
                        decodeur = new DecodeurHumain();
                        plusMoins.initialiserUnePartie(codeur, decodeur);
                        plusMoins.jouerUnePartie(codeur, decodeur);
                        break;
                    case DEFENSEUR:  //L'humain définit un code que l'ordi doit trouver
                        codeur= new CodeurHumain();
                        decodeur = new DecodeurOrdi();
                        plusMoins.initialiserUnePartie(codeur, decodeur);
                        plusMoins.jouerUnePartie(codeur, decodeur);
                        break;
                    case DUEL:

                        break;
                    default:
                        System.out.println("Valeur inatendue pour modejeu : '" + modeJeu + "'");  //TODO a mettre en log
                        System.out.println("Le mode souhaité n'existe pas.");
                        break;
                }
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


    /**
     * Proposer à l'utilisatuer de choisir un <b>type</b> de jeu, par exemple PlusMoins ou Mastermind
     * @return le type de jeu sous forme d'un entier
     */
    public int selectionnerTypeJeu() {
        System.out.println("   1- Plus-Moins");
        System.out.println("   2- Mastermind");
        System.out.print("Choix du jeu :");
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

    /**
     * Proposer à l'utilisatuer de choisi un <b>mode</b> de jeu, c'est-à-dire une forme du jeu déjà seléctionné
     * @return le mode de jeu sous forme d'un entier
     */
    public int selectionnerModeJeu() {
        System.out.println("   1- Challenger (vous trouvez la combisaison de l'ordinateur)");  //utiliser des libellés d'Enum ??
        System.out.println("   2- Défenseur (l'ordinateur trouve votre combinaison)");
        System.out.println("   3- Duel (vous affrontez l'ordinateur au tour par tour)");
        System.out.print("Choix du mode de jeu :");
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

    /**
     * Résumé des règles du jeu en fonction des choix de l'utilisateur
     * @param selectedGameType le type de jeu (PlusMoins...)
     * @param selectedGameMode le mode de jeu (Challenger...)
     */
    public void afficherRegles(int selectedGameType, int selectedGameMode) {
        System.out.println("Jeu n° " + selectedGameType + " en mode " + selectedGameMode);
        System.out.println(ParametresDuJeu.NB_MAX_ESSAIS + " essais pour trouver un code secret de " + ParametresDuJeu.LONGUEUR_CODE_SECRET + " chiffres.");
        System.out.println("Les chiffres possibles vont de 0 à " + (ParametresDuJeu.NB_MAX_SYMBOLES-1) + ".");
    }
}
