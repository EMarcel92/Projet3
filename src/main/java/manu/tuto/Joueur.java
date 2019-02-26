package manu.tuto;

/**
 * Joueur (humain ou ordi) chargé de :
 * <ul>
 *     <li>générer un code secret</li>
 *     <li>faire des proposition</li>
 *     <li>dire si la partie est gagnée</li>
 * </ul>
 */
public abstract class Joueur {
    private boolean challenger;
    private boolean defenseur;
    private String propositionPrecedente;  //TODO eviter de mettre en public

    public String getPropositionPrecedente() {
        return propositionPrecedente;
    }

    public void setPropositionPrecedente(String propositionPrecedente) {
        this.propositionPrecedente = propositionPrecedente;
    }

    public boolean isChallenger() {
        return challenger;
    }

    public void setChallenger(boolean challenger) {
        this.challenger = challenger;
    }

    public boolean isDefenseur() {
        return defenseur;
    }

    public void setDefenseur(boolean defenseur) {
        this.defenseur = defenseur;
    }

    public abstract String genererCodeSecret();

    public abstract void initialiserSolutionsPlusMoins();

    public abstract String proposition();

    public abstract String proposition(String evaluation);

    public abstract String proposition(int evaluation);
}
