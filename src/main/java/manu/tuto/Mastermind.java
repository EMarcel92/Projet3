package manu.tuto;

public class Mastermind { //implements Partie{
    private Joueur challenger; //todo peut-on mettre ces attributs dans l'interface Partie ?
    private Joueur defenseur;
    private String codeSecret;

    public Mastermind(Joueur challenger, Joueur defenseur) {
        this.challenger = challenger;
        this.defenseur = defenseur;
    }

    /**
     * Initialiser les éléments nécessaires au démarrage d'un Mastermind
     * @param codeur le profil du codeur
     * @param decodeur le profil du décodeur
     */
    //@Override
    public void initialiserUnePartie(Codeur codeur, Decodeur decodeur) {
        codeur.genererCodeSecret();
        codeur.afficherCodeSecret();  //Affichage de la solution si mode développeur
        decodeur.initialiserSolutions(); // Définir les solutions possibles (pour l'ordi)
//TODO initialiser le tableau des solutions possibles
    }

    /**
     * Déroulement d'une partie de Mastermind
     * @see Partie interface
     */
    public void jouerUnePartie() {
        int evaluation = 0;
        String evaluationString = null;
        String proposition = null;
        int nbEssais = 0;
        codeSecret = defenseur.genererCodeSecret();
        //Affichage de la solution si mode développeur
        if (ParametresDuJeu.MODE_DEV) {
            System.out.println("!!!!!! mode développeur - " + codeSecret + " !!!!!!");
        }
        challenger.initialiserSolutionsPlusMoins(); // Définir les solutions possibles (pour l'ordi)
        do {    //Boucle jusqu'à trouver la solution ou atteindre le nombre max de tentatives
            challenger.setPropositionPrecedente(proposition);


            proposition = challenger.proposition(evaluation);
            evaluation = evaluerProposition(proposition);


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
        System.out.println("evaluation = " + evaluation);
        // La partie est gagnée si LONGUEUR_CODE_SECRET symboles bien placés et 0 mal placés
        if (evaluation == 10 * ParametresDuJeu.LONGUEUR_CODE_SECRET) {
            return true;
        } else {
            return false;
        }
    }

    public int evaluerProposition (String proposition){
        int r = 0; // Nombre de symboles à la bonne place
        for (int i = 0; i < ParametresDuJeu.LONGUEUR_CODE_SECRET; i++) { //Calcul du nombre de Symboles à la bonne place
            if (proposition.charAt(i) == codeSecret.charAt(i)){
                r++;
            }
        }
        int b = -r; //Nombre de Symboles qui ne sont pas à la bonne place
        for (int i = 0; i < ParametresDuJeu.NB_MAX_SYMBOLES; i++) {  // Calcul du nombre de symboles mal placés
            int n = 0;
            int m = 0;
            for (int j = 0; j < ParametresDuJeu.LONGUEUR_CODE_SECRET; j++) { //recherche des occurences de symboles dans chaque code
                String unCar = new String();
                unCar = unCar.valueOf(i);
                if (codeSecret.charAt(j)==unCar.charAt(0)) n++;
                if (proposition.charAt(j)==unCar.charAt(0)) m++;
            }
            //   System.out.println(i + "/" + b +"/" + n + "/" + m );
            if (n < m) {
                b += n;
            }else{
                b += m;
            }
        }
        return 10 * r + b;
    }

}
