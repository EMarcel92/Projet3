package manu.tuto;

import java.util.ArrayList;
import java.util.Random;

/**
 * Le décodeur (celui qui cherche le code secret) est un ordinateur
 */
public class DecodeurOrdi extends Decodeur{
//    char[] symboles = {'1', '2', '3', '4', '5', '6', '7', '8', '9'};  //Utilisable si volonté d'avoir des symboles autres que 0, 1, 2...)
    ArrayList<String> tabSolution = new ArrayList<>();
    private StringBuilder solutionMax = new StringBuilder();
    private StringBuilder solutionMin = new StringBuilder();
    private String nouvelleProposition;

    public ArrayList<String> getTabSolution() {
        return tabSolution;
    }

    /**
     * Détermine les solutions possibles du PlusMoins sous forme de 2 bornes Min et Max
     */
    @Override
    public void initialiserSolutions(){
        String chaine = "";
        for (int i = 0; i < ParametresDuJeu.LONGUEUR_CODE_SECRET; i++) {
            solutionMax.append(chaine.valueOf(ParametresDuJeu.NB_MAX_SYMBOLES-1)); // création d'une chaine avec que des symboles max
            solutionMin.append("0");   // chaine avec des symboles min
        }
//        System.out.println("Initialisation des bornes :" + solutionMax +"/"+ solutionMin);
    }

    /**
     * Faire une proposition en fonction de la dernière évaluation
     * @param evaluationDernierTour la dernière évaluation
     * @return la nouvelle proposition
     */
    @Override
    public String proposition(String evaluationDernierTour) {
        // Si le resultat est null, on est dans le premier tour de jeu
        if (evaluationDernierTour == null){ //cas du premier tour de la partie
            nouvelleProposition = genererPropositionAleatoirement();
        }
        else{
            nouvelleProposition = proposition(evaluationDernierTour, nouvelleProposition);
        }
        return nouvelleProposition;
    }

    /**
     * Générer une proposition de solution pour le <b>PlusMoins</b> basée sur une méthode dichotomique
     * @param evaluation la dernière évaluation
     * @param propositionPrecedente la dernière proposition
     * @return la nouvelle proposition
     */
    protected String proposition(String evaluation,String propositionPrecedente) {
        StringBuilder stb = new StringBuilder();
        for (int i = 0; i < ParametresDuJeu.LONGUEUR_CODE_SECRET; i++) {
            switch (evaluation.charAt(i)){
                case '+':
                    solutionMin.setCharAt(i,(char)(propositionPrecedente.charAt(i)+1));
                    break;
                case '-':
                    solutionMax.setCharAt(i,(char)(propositionPrecedente.charAt(i)-1));
                    break;
                case '=':
                    solutionMin.setCharAt(i,propositionPrecedente.charAt(i));
                    solutionMax.setCharAt(i,propositionPrecedente.charAt(i));
                    break;
                default:
                    System.out.println("Erreur inattendue lors de la génération d'une proposition : "+ evaluation.charAt(i) +'.');
                    break;
            }
          //  System.out.println("eval="+evaluation.charAt(i)+"min=" + solutionMin +  "max=" + solutionMax);
        }
   //     System.out.println("min=" + solutionMin +  "max=" + solutionMax);
        for (int i = 0; i < ParametresDuJeu.LONGUEUR_CODE_SECRET; i++) {
            stb.append((char)((solutionMax.charAt(i)+solutionMin.charAt(i))/2)); //méthode dichotomique
        }
        return stb.toString();
    }

    /**
    * Génération d'une première proposition totalement aléatoire
    * @return la proposition sous forme de chaine
    */
    private String genererPropositionAleatoirement() {
        Random rnd = new Random();
        StringBuilder uneProposition = new StringBuilder();
        int i=0;
        char unCar;
        do {   // Création d'une proposition aléatoire de longueur paramétrée
            unCar = String.valueOf(rnd.nextInt(ParametresDuJeu.NB_MAX_SYMBOLES)).charAt(0);  //Une valeur aléatoire entre 0 et NB_MAX8symbole-1
            uneProposition.append(unCar);
            i++;
        }while (i < ParametresDuJeu.LONGUEUR_CODE_SECRET);
        return uneProposition.toString();
    }

    /**
     * Alimente un tableau de String correspondant à l'ensemble de toutes les solutions possibles du <b>Mastermind</b>
     */
    public void GénérerSolutionsMastermind() {
        //Nombre de combinaisons possibles
        int tailleMaxDuTableau = (int) Math.pow(ParametresDuJeu.NB_MAX_SYMBOLES, ParametresDuJeu.LONGUEUR_CODE_SECRET);
//        System.out.println("taille du tableau prévue" + tailleMaxDuTableau);
        String ligne="";
        for (int i = 0; i < tailleMaxDuTableau; i++) {
            ligne =  ligne.valueOf(base(i,ParametresDuJeu.NB_MAX_SYMBOLES));
            if (ligne.length()<ParametresDuJeu.LONGUEUR_CODE_SECRET) ligne = completerZeroAGauche(ligne);
            tabSolution.add(ligne);
//            System.out.println("ligne(" + i + ")=" + ligne);
        }
     }

     /**
     * Conversion d'un nombre décimal en base b
     * @param n le nombre à convertir
     * @param b la dimension de la base cible
     * @return le nombre n (int) converti en base
     */
    private int base (int n, int b){
        int q = n/b;
        int r = n%b;
        int p=1;
        while (q!=0){
            r=r+10*p*(q%b);
            q=q/b   ;
            p=10*p;
        }
        return r;
    }

    /**
     * Complément d'une chaine de caratères avec des zéros à gauche sur une longueur récupérée du fichier de paramètre en entrée
     * @param string chaine de caratères
     * @return la chaine en entrée complétée avec des zéro à gauche
     */
    protected String completerZeroAGauche (String string){
        StringBuilder stb = new StringBuilder();
        for(int i=0; i<ParametresDuJeu.LONGUEUR_CODE_SECRET-string.length(); i++){
            stb.append("0");
        }
        stb.append(string);
//        System.out.println("completer" + stb.toString());
        return stb.toString();
    }
}
