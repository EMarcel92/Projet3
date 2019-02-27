package manu.tuto;

import org.apache.log4j.Logger;

public class Mastermind { //implements Partie{
    private Joueur challenger;
    private Joueur defenseur;
    private String codeSecret;

    private static Logger logger = Logger.getLogger(Main.class);

    public Mastermind(Joueur challenger, Joueur defenseur) {
        logger.debug("[Mastermind] Constructeur");
        this.challenger = challenger;
        this.defenseur = defenseur;
    }

    public String getCodeSecret() {
        return codeSecret;
    }

    public void setCodeSecret(String codeSecret) {
        this.codeSecret = codeSecret;
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
         //   challenger.setPropositionPrecedente(proposition);


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
     * Définir si la partie est gagnée
     * @param evaluation l'évalution de la dernière proposition
     * @return True si la partie est gagnée
     */
    private boolean cEstGagne (int evaluation) {
        logger.debug("[Mastermind] cEstGagne avec en entrée evaluation=" + evaluation + '.');
        // La partie est gagnée si LONGUEUR_CODE_SECRET symboles bien placés et 0 mal placés
        if (evaluation == 10 * ParametresDuJeu.LONGUEUR_CODE_SECRET) {
            return true;
        } else {
            return false;
        }
    }



}
