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
     * @return Le résultat de l'évaluation sous forme d'une String (ex : '-+=+')
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

    public int evaluerProposition (String proposition,String codeSecret){
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

    /**
     * Affichage du code secret si le jeu est lancé en <b></b>mode Développeur</b>
     */
    public void afficherCodeSecret () {
        if (ParametresDuJeu.MODE_DEV) {
            System.out.println("!!!!!! mode développeur - " + this.toString() + " !!!!!!");
        }
    }
}
