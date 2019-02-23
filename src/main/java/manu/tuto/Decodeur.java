package manu.tuto;

public abstract class Decodeur {
    private String propositionPrecedente;

    public String getPropositionPrecedente() {
        return propositionPrecedente;
    }

    public void setPropositionPrecedente(String propositionPrecedente) {
        this.propositionPrecedente = propositionPrecedente;
    }

    /**
     * Le décodeur initialise les solutions possibles
     */
    public abstract void initialiserSolutions();

    /**
     * Le décodeur propose un code de la longueur paramétrée après analyse des résultats des tours précédents.
      * @return La proposition de code sous forme d'une chaine de caractères
     */
    public abstract String proposition (String resultatDernierTour);

}
