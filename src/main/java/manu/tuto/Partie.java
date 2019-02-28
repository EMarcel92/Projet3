package manu.tuto;

/**
 *
 */
public interface Partie {

    /**
     * Méthode gérant l'initialisation de la partie et la séquence des tours jusqu'à la fin de la partie
     *<ul>
     *     <li>Le challenger fait une proposition</li>
     *     <li>Le codeur évalue cette proposition par rapport à son code secret</li>
     * </ul>
     */
    void jouerUnePartie();

    /**
     * Mode de jeu spécifique permettant de réaliser 2 parties en parallèle
     */
    void JouerUnDuel() ;
}
