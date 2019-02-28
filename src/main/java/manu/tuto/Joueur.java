package manu.tuto;

/**
 * Joueur (humain ou ordi) chargé de :
 * <ul>
 *     <li>générer un code secret</li>
 *     <li>initaliser les solutions possibles</li>
 *     <li>faire des proposition</li>
 *     <li>évaluer les propositions</li>
 * </ul>
 */
public abstract class Joueur {

    /**
     * Créer un code secret à faire deviner à l'autre joueur
     * @return un code secret respectant des critères (longueur, plage de symboles)
     */
    public abstract String genererCodeSecret();

    /**
     * Définir la plage de solutions possibles pour un jeu (utilisé par un ordinateur)
     */
    public void initialiserSolutionsPlusMoins(){
            }

    /**
     * Définir la plage de solutions possibles pour un jeu (utilisé par un ordinateur)
     */
    public void initialiserSolutionsMastermind(){
    }

    /**
     * Faire une proposition de solution pour le PlusMoins
     * @param evaluation La dernière évaluation (Chaine de caractère)
     * @return une proposition respectant des critères (longueur, plage de symboles)
     */
    public abstract String proposition(String evaluation);

    /**
     * Faire une proposition de solution pour le Mastermind
     * @param evaluation La dernière évaluation (numérique)
     * @return Une nouvelle proposition de solution
     */
    public abstract String proposition(int evaluation);

    /**
     * Compare une proposition à un code et renvoie une réponse indiquant s'ils sont similaires
     * @param proposition la proposition
     * @param codeSecret le code de référence à laquelle il faut compoarer la proposition
     * @return le résultat de la comparaison
     */
    public abstract int evaluerProposition (String proposition, String codeSecret);

}
