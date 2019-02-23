package manu.tuto;

/**
 * Joueur (humain ou ordi) chargé de :
 * <ul>
 *     <li>générer un code</li>
 *     <li>évaluer les propositions</li>
 *     <li>dire si la partie est gagnée</li>
 * </ul>
 */
public abstract class Codeur {
//    private ArrayList<Character> codeSecret;
    String codeSecret;

    public String getCodeSecret() {
        return codeSecret;
    }

    public void setCodeSecret(String codeSecret) {
        this.codeSecret = codeSecret;
    }

    @Override
    public String toString() {
        return "Le code secret est " + this.getCodeSecret();
    }

    public abstract void genererCodeSecret();

     /**
     * Comparer la proposition passée en paramètre par rapport au code secret
     * @param proposition faite par le challenger
     * @return Le résultat de l'évaluation sous forme d'une phrase en français
     */
    public String evaluerProposition(String proposition) {
        String reponse = "";
        for (int i = 0; i < ParametresDuJeu.LONGUEUR_CODE_SECRET; i++) {
            if (proposition.charAt(i) == this.getCodeSecret().charAt(i))  {
                reponse += "=";
            }else {
                if (proposition.charAt(i) < this.getCodeSecret().charAt(i)){
                    reponse += "+";
                }else{
                    if (proposition.charAt(i) > this.getCodeSecret().charAt(i)){
                        reponse += "-";
                    }
                }
            }
        }
        return reponse;
    }

    /**
     * Affichage du code secret si le jeu est lancé en <b></b>mode Développeur</b>
     */
    public void afficherCodeSecret () {
        if (ParametresDuJeu.MODE_DEV) {
            System.out.println("!!!!!! mode développeur - " + this.toString() + " !!!!!!");
        }
    }
}
