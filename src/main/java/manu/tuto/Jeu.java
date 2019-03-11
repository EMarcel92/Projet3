package manu.tuto;

import org.apache.log4j.Logger;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Jeu correspond à l'application qui permet de jouer à plusieurs types de jeu
 *<ul>
 *     <li>Lancement du jeu (initialisation de l&acute;application</li>
 *     <li>Choix des options de jeu</li>
 *     <li>Lancement d'une partie</li>
 *     <li>Choix de rejouer ou jouer à un autre type de jeu</li>
 *</ul>
 */
public class Jeu {
    private int typeJeu;             //Type de jeu
    private int modeJeu;             //mode de jeu
    private final int PLUSMOINS = 1;  //Constantes pour simplifier la lecture du code
    private final int MASTERMIND = 2;
    private final int CHALLENGER = 1;
    private final int DEFENSEUR = 2;
    private final int DUEL = 3;
    Scanner sc ;
    private boolean reponseFausse ;  //peut-on déclarer ici pour utiliser dans plusieurs méthodes ??
    Joueur joueurHumain; // Déclaration d'un joueur humain(classe abstraite)
    Joueur joueurOrdi;// Idem pour le Joueur Ordinateur
    Joueur joueurHumain2 ; // Déclaration d'un 2e joueur humain(classe abstraite)
    Joueur joueurOrdi2 ;// Idem pour le 2e Joueur Ordinateur

    private static Logger logger = Logger.getLogger(Main.class);

    public Jeu() {
        this.sc = new Scanner(System.in);
        this.reponseFausse = true;
    }

    /**
     * Lanceur du jeu (logiciel) : détermination de la partie (type et mode de jeu) à exécuter
     */
    public void lancerLeJeu(){
       // ParametresDuJeu parametresDuJeu = new ParametresDuJeu();
        logger.debug("[Jeu] lancerLeJeu");
        //Intialisation des paramètres des jeux (fichier de paramétrage)
        ParametresDuJeu parametresDuJeu = new ParametresDuJeu();

        parametresDuJeu.intialiserLesParametres();
        bienvenue();   //Affichage d'un message d'accueil
        int choix = 2; //Choix de jouer à un "autre jeu" provoquant l'affichage des menus de sélection d'un jeu
        do {  //tant que le joueur veut continuer à jouer
            initialiserLeJeu();
            do {  //Tant que le joueur veut faire le même jeu
                lancerUnePartie(); // Lance une partie d'un jeu donné
                System.out.println();
                System.out.println("Que souhaitez-vous faire ( 1- Rejouer  2- Autre jeu   3- Quitter) ? ");
                boolean reponseInvalide;
                do {
                    try {
                        choix = sc.nextInt();
                        reponseInvalide = false;
                        if (choix != 1 && choix != 2 && choix != 3) {
                            System.out.println("Vous devez saisir un des choix proposés (1, 2 ou 3).");
                            reponseInvalide = true;
                        }
                    } catch (InputMismatchException e) {
                        sc.next();
                        System.out.println("Vous devez saisir un nombre.");
                        reponseInvalide = true;
                    }
                }while (reponseInvalide);
            } while (choix == 1); // Le joueur veut rejouer au même jeu donc on ne repasse pas dans le menu de sélection du type dejeu
        } while (choix == 2);  //Le joueur veut jouer à un autre jeu
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
        selectionnerTypeJeu();
        selectionnerModeJeu();
        afficherRegles();
    }

    /**
     * Lance et déroule une partie (PlusMoins ou Mastermind) en fonction des choix précédents de l'utilisateur
     */
    public void lancerUnePartie(){
        //instanciation des joueurs (réinit des variables si plusieurs parties jouées à la suite)
        joueurOrdi = new JoueurOrdi();
        this.joueurHumain = new JoueurHumain();

        switch (typeJeu) {
            case PLUSMOINS:
                PlusMoins plusMoins;
                switch (modeJeu){
                    case CHALLENGER: //L'humain décode le code fourni par l'ordi (codeur)
                        plusMoins = new PlusMoins(joueurHumain, joueurOrdi);
                        plusMoins.jouerUnePartie();
                        break;
                    case DEFENSEUR:  //L'humain définit un code que l'ordi doit trouver
                        plusMoins = new PlusMoins(joueurOrdi, joueurHumain);
                        plusMoins.jouerUnePartie();
                        break;
                    case DUEL:
                        joueurHumain2=new JoueurHumain();
                        joueurOrdi2 = new JoueurOrdi ();
                        plusMoins = new PlusMoins(joueurHumain, joueurOrdi, joueurOrdi2, joueurHumain2);
                        plusMoins.jouerUnDuel();
                        break;
                    default:
                        System.out.println("Le mode souhaité n'existe pas.");
                        logger.error("Valeur inattendue pour modejeu : '" + modeJeu + "'");
                        break;
                }
                break;
            case MASTERMIND:
                Mastermind mastermind;
                switch (modeJeu){
                    case CHALLENGER: //L'humain décode le code fourni par l'ordi
                        mastermind = new Mastermind(joueurHumain, joueurOrdi);
                        mastermind.jouerUnePartie();
                        break;
                    case DEFENSEUR:  //L'humain définit un code que l'ordi doit trouver
                        mastermind = new Mastermind(joueurOrdi, joueurHumain);
                        mastermind.jouerUnePartie();
                        break;
                    case DUEL:
                        joueurHumain2=new JoueurHumain();
                        joueurOrdi2 = new JoueurOrdi ();
                        mastermind = new Mastermind(joueurHumain, joueurOrdi, joueurOrdi2, joueurHumain2);
                        mastermind.jouerUnDuel();
                        break;
                    default:
                        System.out.println("Le mode souhaité n'existe pas.");
                        logger.error("Valeur inatendue pour modejeu : '" + modeJeu + "'");
                        break;
                }
                break;
            default:
                logger.error("Valeur inatendue pour typeJeu : '" + typeJeu + "'");
                System.out.println("Le jeu souhaité n'existe pas.");
                break;
        }
    }

    /**
     * Proposer à l'utilisatuer de choisir un <b>type</b> de jeu, par exemple PlusMoins ou Mastermind
     */
    public void selectionnerTypeJeu() {
        System.out.println("   1- Plus-Moins");
        System.out.println("   2- Mastermind");
        System.out.print("Choix du jeu :");
        reponseFausse = true;
        while (reponseFausse) {
            try {
                typeJeu = sc.nextInt();
                reponseFausse = false;
                if (typeJeu != PLUSMOINS && typeJeu != MASTERMIND) {   //A modifier si la liste des jeux peut évoluer
                    System.out.println("Vous devez saisir un des choix proposés (1 ou 2).");
                    reponseFausse = true;
                }
            } catch (InputMismatchException e) {
                sc.next();
                System.out.println("Vous devez saisir un nombre.");
                reponseFausse = true;
            }
        }
    }

    /**
     * Proposer à l'utilisatuer de choisi un <b>mode</b> de jeu, c'est-à-dire une forme du jeu déjà seléctionné
     */
    public void
    selectionnerModeJeu() {
        System.out.println("   1- Challenger (vous trouvez la combisaison de l'ordinateur)");
        System.out.println("   2- Défenseur (l'ordinateur trouve votre combinaison)");
        System.out.println("   3- Duel (vous affrontez l'ordinateur au tour par tour)");
        System.out.print("Choix du mode de jeu :");
        reponseFausse = true;
        while (reponseFausse) {
            try {
                modeJeu = sc.nextInt();
                reponseFausse = false;      // comment comparer l'entrée avec des Enum ?? faire un switch sur modeDeJeu et sélectionner un Enum ??
                if (modeJeu != CHALLENGER && modeJeu != DEFENSEUR && modeJeu != DUEL) {   //A modifier si la liste des modes peut évoluer
                    System.out.println("Vous devez saisir un des choix proposés (1, 2 ou 3).");
                    reponseFausse = true;
                }
            } catch (InputMismatchException e) {
                sc.next();
                System.out.println("Vous devez saisir un nombre.");
                reponseFausse = true;
            }
        }
    }

    /**
     * Résumé des règles du jeu en fonction des choix de l'utilisateur
     */
    public void afficherRegles() {
//        System.out.println("Jeu n° " + typeJeu + " en mode " + modeJeu);
        System.out.println("Règles du jeu :");
        System.out.println(ParametresDuJeu.NB_MAX_ESSAIS + " essais pour trouver un code secret de " + ParametresDuJeu.LONGUEUR_CODE_SECRET + " chiffres.");
        System.out.println("Les chiffres possibles vont de 0 à " + (ParametresDuJeu.NB_MAX_SYMBOLES-1) + ".");
        System.out.println("Les doublons sont autorisés.");
    }
}
