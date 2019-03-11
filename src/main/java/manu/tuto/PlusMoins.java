package manu.tuto;

/**
 * PlusMoins déroule une partie du jeu Plus-Moins
 */
public class PlusMoins implements Partie {
    private Joueur challenger;
    private Joueur defenseur;
    private String codeSecret;
    private Joueur challenger2;
    private Joueur defenseur2;
    private String codeSecret2;

//    public String getCodeSecret() {        return codeSecret;    }
//
//    public void setCodeSecret(String codeSecret) {        this.codeSecret = codeSecret; }
//
//    public String getCodeSecret2() {        return codeSecret2;    }
//
//    public void setCodeSecret2(String codeSecret2) {        this.codeSecret2 = codeSecret2;    }

    public PlusMoins(Joueur challenger, Joueur defenseur) {
        this.challenger = challenger;
        this.defenseur = defenseur;
    }

    public PlusMoins(Joueur challenger, Joueur defenseur, Joueur challenger2, Joueur defenseur2) {
        this.challenger = challenger;
        this.defenseur = defenseur;
        this.challenger2 = challenger2;
        this.defenseur2 = defenseur2;
    }

    /**
     * Déroulement d'une partie de PlusMoins
     * @see Partie
     */
    @Override
    public void jouerUnePartie() {
        String evaluation = null;
        String proposition = null;
        int nbEssais = 0;
        codeSecret = defenseur.genererCodeSecret();
         //Affichage de la solution si mode développeur
        if (ParametresDuJeu.MODE_DEV) {
            System.out.println("!!!!!! mode développeur - " + codeSecret + " !!!!!!");
        }
        challenger.initialiserSolutionsPlusMoins(); // Définir les solutions possibles (pour l'ordi)
        do {    //Boucle jusqu'à trouver la solution ou atteindre le nombre max de tentatives

            proposition = challenger.proposition(evaluation);
            evaluation = evaluerProposition(proposition);

            System.out.println("Proposition : " + proposition + " -> Réponse : " + evaluation);
            nbEssais++;
        } while (!cEstGagne(evaluation) && nbEssais < ParametresDuJeu.NB_MAX_ESSAIS);
        if (cEstGagne(evaluation)) {
            System.out.println("Le challenger a gagné : le code secret a été découvert en " + nbEssais + " coups.");
        }else {
            System.out.println("Le challenger a perdu. Le code secret était : " + codeSecret + ".");
        }
    }

    @Override
    public void jouerUnDuel() {
        String evaluation = null;
        String proposition = null;
        String evaluation2 = null;
        String proposition2 = null;
        int nbEssais = 0;
        codeSecret = defenseur.genererCodeSecret();
        codeSecret2 = defenseur2.genererCodeSecret();
         //Affichage de la solution si mode développeur
        if (ParametresDuJeu.MODE_DEV) {
            System.out.println("!!!!!! mode développeur - 1er code=" + codeSecret + " 2e code=" + codeSecret2 + " !!!!!!");
        }
        challenger.initialiserSolutionsPlusMoins(); // Définir les solutions possibles (pour l'ordi)
        challenger2.initialiserSolutionsPlusMoins();
        do {    //Boucle jusqu'à trouver la solution ou atteindre le nombre max de tentatives
            nbEssais++;
            System.out.println("----- tour " + nbEssais + "-----");
            proposition = challenger.proposition(evaluation);
            evaluation = evaluerProposition(proposition);
            System.out.println("on inverse les rôles :");
            proposition2 = challenger2.proposition(evaluation2);
            evaluation2 = evaluerProposition(proposition2);

            System.out.println("Proposition : " + proposition + " -> Réponse : " + evaluation + "       Proposition2 : " + proposition2 + " -> Réponse2 : " + evaluation2);

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
     * Comparer la proposition passée en paramètre par rapport au code secret
     * @param proposition faite par le challenger
     * @return Le résultat de l'évaluation sous forme d'une String (ex : '-+=+')
     */
    public String evaluerProposition(String proposition) {
        String reponse = "";
        for (int i = 0; i < ParametresDuJeu.LONGUEUR_CODE_SECRET; i++) {
            if (proposition.charAt(i) == codeSecret.charAt(i))  {
                reponse += "=";
            }else {
                if (proposition.charAt(i) < codeSecret.charAt(i)){
                    reponse += "+";
                }else{
                    if (proposition.charAt(i) > codeSecret.charAt(i)){
                        reponse += "-";
                    }
                }
            }
        }
        return reponse;
    }

    /**
     * Définir si la partie est gagnée
     * @param evaluation l'évalution de la dernière proposition
     * @return True si la partie est gagnée
     */
    private boolean cEstGagne (String evaluation) {
        return evaluation.matches( "^=*$"); // C'est gagné si le résultat de l'évalusation est une suite de ===
    }

}
