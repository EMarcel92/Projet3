package manu.tuto;

public interface Partie {

    /**
     * Méthode utilisée au démarrage d'une partie pour
     * <ul>
     *     <li>Générer un code secret</li>
     *     <li>Initialiser l'ensemble des solution possibles</li>
     * </ul>
     */
  //  void initialiserUnePartie(Codeur codeur, Decodeur decodeur);

    /**
     * Méthode gérant l'initialisation de la partie et la séquence des tours jusqu'à la fin de la partie
     *<ul>
     *     <li>Le challenger fait une proposition</li>
     *     <li>Le codeur évalue cette proposition par rapport à son code secret</li>
     * </ul>
     */
    void jouerUnePartie();

}
