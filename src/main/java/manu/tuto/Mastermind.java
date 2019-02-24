package manu.tuto;

public class Mastermind implements Partie{

    /**
     * Initialiser les éléments nécessaires au démarrage d'un Mastermind
     * @param codeur le profil du codeur
     * @param decodeur le profil du décodeur
     */
    @Override
    public void initialiserUnePartie(Codeur codeur, Decodeur decodeur) {
        codeur.genererCodeSecret();
        codeur.afficherCodeSecret();  //Affichage de la solution si mode développeur
        decodeur.initialiserSolutions(); // Définir les solutions possibles (pour l'ordi)
//TODO initialiser le tableau des solutions possibles
    }

    /**
     * Déroulement d'une partie de Mastermind
     * @see Partie interface
     * @param codeur en précisant un codeur de type humain ou ordinateur
     * @param decodeur en précisant un codeur de type humain ou ordinateur
     */
    public void jouerUnePartie(Codeur codeur, Decodeur decodeur) {
        int evaluation = 0;
        String evaluationString = null;
        String proposition = null;
        int nbEssais = 0;
        do {    //Boucle jusqu'à trouver la solution ou atteindre le nombre max de tentatives
            decodeur.setPropositionPrecedente(proposition);



            proposition = decodeur.proposition(evaluationString);
            evaluation = codeur.evaluerProposition(proposition,codeur.getCodeSecret());


            System.out.println("Proposition : " + proposition + " -> Réponse : " + evaluation/10 + " bien placés et " + evaluation%10 + " mal placés");
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
    private boolean cEstGagne (int evaluation) {
        // C'est gagné si le résultat de l'évaluation est égal à 40
        //TODO voir Stéphane si on peut faire plus direct , du type => return (evaluation == 40);
        System.out.println("evaluatiuon = " + evaluation);
        if (evaluation == 10 * ParametresDuJeu.LONGUEUR_CODE_SECRET) {
            return true;
        } else {
            return false;
        }
    }



}
