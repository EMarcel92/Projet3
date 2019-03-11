package manu.tuto;

import org.apache.log4j.Logger;

/**
 * Mastremind déroule une pertie de Mastermind
 */
public class Mastermind implements Partie{
    private Joueur challenger;
    private Joueur defenseur;
    private String codeSecret;
    private Joueur challenger2;
    private Joueur defenseur2;
    private String codeSecret2;

    private static Logger logger = Logger.getLogger(Main.class);

    public Mastermind(Joueur challenger, Joueur defenseur) {
        logger.debug("[Mastermind] Constructeur 2 paramètres");
        this.challenger = challenger;
        this.defenseur = defenseur;
    }

    public Mastermind(Joueur challenger, Joueur defenseur, Joueur challenger2, Joueur defenseur2) {
        logger.debug("[Mastermind] Constructeur 4 paramètres");
        this.challenger = challenger;
        this.defenseur = defenseur;
        this.challenger2 = challenger2;
        this.defenseur2 = defenseur2;
    }

    /**
     * Déroulement d'une partie de Mastermind
     * @see Partie interface
     */
    public void jouerUnePartie() {
        logger.debug("[Mastermind] jouerUnePartie");
        int evaluation = 999;  // valeur 999 pour "pas encore d'évaluation"
      //  String evaluationString = null;
        String proposition = null;
        int nbEssais = 0;
        codeSecret = defenseur.genererCodeSecret();
        //Affichage de la solution si mode développeur
        if (ParametresDuJeu.MODE_DEV) {
            System.out.println("!!!!!! mode développeur - " + codeSecret + " !!!!!!");
        }
        challenger.initialiserSolutionsMastermind(); // Définir les solutions possibles (pour l'ordi)
        do {    //Boucle jusqu'à trouver la solution ou atteindre le nombre max de tentatives

            proposition = challenger.proposition(evaluation);
            evaluation = defenseur.evaluerProposition(proposition,codeSecret);

            System.out.println("Proposition : " + proposition + " -> Réponse : " + evaluation/10 + " bien placé(s) et " + evaluation%10 + " mal placé(s)");
            nbEssais++;
        } while (!cEstGagne(evaluation) && nbEssais < ParametresDuJeu.NB_MAX_ESSAIS);
        if (cEstGagne(evaluation)) {
            System.out.println("Le challenger a gagné : le code secret a été découvert en " + nbEssais + " coups.");
        }else {
            System.out.println("Le challenger a perdu. Le code secret était : " + codeSecret + ".");
        }
    }

    /**
     * Mode de jeu ou le jouer (humain) et l'ordinateur jouent en étant à tour de rôle Challenger et Défenseur
     */
    public void jouerUnDuel() {
        logger.debug("[Mastermind] jouerUnDuel");
        int evaluation = 999;  // valeur 999 pour "pas encore d'évaluation"
        int evaluation2 = 999;
        String proposition = null;
        String proposition2 = null;
        int nbEssais = 0;
        codeSecret = defenseur.genererCodeSecret();
        codeSecret2 = defenseur2.genererCodeSecret();
        //Affichage de la solution si mode développeur
        if (ParametresDuJeu.MODE_DEV) {
            System.out.println("!!!!!! mode développeur - 1er code=" + codeSecret + " 2e code=" + codeSecret2 + " !!!!!!");
        }
        challenger.initialiserSolutionsMastermind(); // Définir les solutions possibles (pour l'ordi)
        challenger2.initialiserSolutionsMastermind();
        do {    //Boucle jusqu'à trouver la solution dans un des 2 parties ou atteindre le nombre max de tentatives
            nbEssais++;
            System.out.println("----- tour " + nbEssais + "-----");
            proposition = challenger.proposition(evaluation);
            evaluation = defenseur.evaluerProposition(proposition,codeSecret);
            System.out.println("Inversion des rôles :");
            proposition2 = challenger2.proposition(evaluation2);
            evaluation2 = defenseur2.evaluerProposition(proposition,codeSecret2);

            System.out.println("Proposition : " + proposition + " -> Réponse : " + evaluation/10 + " bien placé(s) et "
                            + evaluation%10 + " mal placé(s)     Proposition2 : " + proposition2 + " -> Réponse2 : " +
                            evaluation2);

        } while (!cEstGagne(evaluation) && !cEstGagne(evaluation2) && nbEssais < ParametresDuJeu.NB_MAX_ESSAIS);
        if (cEstGagne(evaluation)) {
            System.out.println("Le challenger1 a gagné : le code secret a été découvert en " + nbEssais + " coups.");
        }else {
            if (cEstGagne(evaluation2)) {
                System.out.println("Le challenger2 a gagné : le code secret a été découvert en " + nbEssais + " coups.");
            }else {
                System.out.println("Le challenger a perdu. Le code secret était : " + codeSecret + ".");
            }
        }
    }

    /**
     * Définir si la partie est gagnée
     * @param evaluation l'évalution de la dernière proposition
     * @return True si la partie est gagnée
     */
    protected boolean cEstGagne (int evaluation) {
        logger.debug("[Mastermind] cEstGagne avec en entrée evaluation=" + evaluation + '.');
        // La partie est gagnée si LONGUEUR_CODE_SECRET symboles bien placés et 0 mal placés
        if (evaluation == 10 * ParametresDuJeu.LONGUEUR_CODE_SECRET) {
            return true;
        } else {
            return false;
        }
    }

}
