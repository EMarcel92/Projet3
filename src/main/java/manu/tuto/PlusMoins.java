package manu.tuto;

public class PlusMoins implements Partie {
    private Joueur challenger;
    private Joueur defenseur;
    private String codeSecret;

    public String getCodeSecret() {
        return codeSecret;
    }

    public void setCodeSecret(String codeSecret) {
        this.codeSecret = codeSecret;
    }

    public PlusMoins(Joueur challenger, Joueur defenseur) {
        this.challenger = challenger;
        this.defenseur = defenseur;
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
        System.out.println("ModeDev=" + ParametresDuJeu.MODE_DEV);
        if (ParametresDuJeu.MODE_DEV) {
            System.out.println("!!!!!! mode développeur - " + codeSecret + " !!!!!!");
        }
        challenger.initialiserSolutionsPlusMoins(); // Définir les solutions possibles (pour l'ordi)
        do {    //Boucle jusqu'à trouver la solution ou atteindre le nombre max de tentatives
            /////    challenger.setPropositionPrecedente(proposition);

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
