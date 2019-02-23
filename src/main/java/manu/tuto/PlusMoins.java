package manu.tuto;

public class PlusMoins implements Partie {

    /**
     * Initialiser les éléments nécessaires au démarrage d'un PlusMoins
     * @param codeur le profil du codeur
     * @param decodeur le profil du décodeur
     */
    @Override
    public void initialiserUnePartie(Codeur codeur, Decodeur decodeur) {
        codeur.genererCodeSecret();
        codeur.afficherCodeSecret();  //Affichage de la solution si mode développeur
        decodeur.initialiserSolutions(); // Définir les solutions possibles
//TODO initialiser le tableau des solutions possibles
    }

    /**
     * Déroulement d'une partie de PlusMoins
     * @see Partie
     * @param codeur en précisant un codeur de type humain ou ordinateur
     * @param decodeur en précisant un codeur de type humain ou ordinateur
     */
    @Override
    public void jouerUnePartie(Codeur codeur, Decodeur decodeur) {
        String evaluation = null;
        String proposition = null;
        int nbEssais = 0;
        do {    //Boucle jusqu'à trouver la solution ou atteindre le nombre max de tentatives
            decodeur.setPropositionPrecedente(proposition);



            proposition = decodeur.proposition(evaluation);
            evaluation = codeur.evaluerProposition(proposition);


            System.out.println("Proposition : " + proposition + " -> Réponse : " + evaluation);
            nbEssais++;
        } while (!cEstGagne(evaluation) && nbEssais < ParametresDuJeu.NB_MAX_ESSAIS);
        if (cEstGagne(evaluation)) {
            System.out.println("Le challenger a gagné : le code secret a été découvert en " + nbEssais + " coups.");
        }else {
            System.out.println("Le challenger a perdu. Le code secret était : " + codeur.getCodeSecret() + ".");
        }
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
