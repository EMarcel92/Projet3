package manu.tuto;

import org.apache.log4j.Logger;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Joueur Ordinateur qui joue contre le joueur l'humain
 * Il peut avoir les rôles codeur et/ou décodeur
 */
public class JoueurOrdi extends Joueur {
    //    char[] symboles = {'1', '2', '3', '4', '5', '6', '7', '8', '9'};  //Utilisable si volonté d'avoir des symboles autres que 0, 1, 2...)
    private ArrayList<String> tabSolution = new ArrayList<>();
    private StringBuilder solutionMax = new StringBuilder();
    private StringBuilder solutionMin = new StringBuilder();
    private String nouvelleProposition;
    private String propositionPrecedente;
    private List<String> anciennesPropositions= new ArrayList<>(); //Les propostions précédentes
    private List<Integer> anciennesEvaluations = new ArrayList<>();  // et leur évaluation associée

    private static Logger logger = Logger.getLogger(Main.class);



    public void setPropositionPrecedente(String propositionPrecedente) {
        this.propositionPrecedente = propositionPrecedente;
    }

    public StringBuilder getSolutionMax() {
        return solutionMax;
    }

    public StringBuilder getSolutionMin() {
        return solutionMin;
    }

    public ArrayList<String> getTabSolution() {
        return tabSolution;
    }

    /**
     * Faire une proposition pour le <b>PlusMoins</b> basée sur une méthode dichotomique
     * @param evaluationDernierTour la dernière évaluation
     * @return la nouvelle proposition
     */
    @Override
    public String proposition(String evaluationDernierTour) {
        logger.debug("[JoueurOrdi] proposition (String) avec en entrée " + evaluationDernierTour + '.');
        // Si le resultat est null, on est dans le premier tour de jeu
        if (evaluationDernierTour == null){ //cas du premier tour de la partie
            nouvelleProposition = genererPropositionAleatoirement();
        }else{
            StringBuilder stb = new StringBuilder();
            for (int i = 0; i < ParametresDuJeu.LONGUEUR_CODE_SECRET; i++) {
                switch (evaluationDernierTour.charAt(i)){
//                    case '+':
//                        solutionMin.setCharAt(i,(char)(this.getPropositionPrecedente().charAt(i)+1));
//                        break;
//                    case '-':
//                        solutionMax.setCharAt(i,(char)(this.getPropositionPrecedente().charAt(i)-1));
//                        break;
//                    case '=':
//                        solutionMin.setCharAt(i,this.getPropositionPrecedente().charAt(i));
//                        solutionMax.setCharAt(i,this.getPropositionPrecedente().charAt(i));
//                        break;
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
                        System.out.println("Erreur inattendue lors de la génération d'une proposition : "+ evaluationDernierTour.charAt(i) +'.');
                        break;
                }
            }
            //     System.out.println("min=" + solutionMin +  "max=" + solutionMax);
            for (int i = 0; i < ParametresDuJeu.LONGUEUR_CODE_SECRET; i++) {
                stb.append((char)((solutionMax.charAt(i)+solutionMin.charAt(i))/2)); //méthode dichotomique
            }
            nouvelleProposition =  stb.toString();
        }
        logger.debug("[JoueurOrdi] proposition (String) avec en sortie" + nouvelleProposition + '.');
        propositionPrecedente=nouvelleProposition;
        return nouvelleProposition;
    }

    /**
     * Générer une proposition de solution pour le <b>Mastermind</b>
     * @param evaluation La dernière évaluation
     * @return Une nouvelle proposition
     */
    @Override
    public String proposition (int evaluation) {
        logger.debug("[JoueurOrdi] Proposition (int) avec en entrée :" + evaluation + '.');
        // Si l'évaluation est à 999 , on est dans le premier tour de jeu
        if (evaluation == 999){ //cas du premier tour de la partie
            nouvelleProposition = genererPropositionAleatoirement();
        }else{
            anciennesEvaluations.add(evaluation);
            logger.trace("JoueurOrdi] Proposition (int). evaluation=" + evaluation + ". Taille tab anciennesPropositions = "
                    + anciennesPropositions.size() + " Taille tab anciennesEvaluations=" + anciennesEvaluations.size());

            int i = 0; //index du tableau des solutions tabSolution
            boolean trouve;
            do {
                trouve = true;
                logger.trace("[JoueurOrdi] Proposition (int). tabSolution=" + tabSolution.get(i));
                for (int j = 0; j < anciennesPropositions.size(); j++) {  //parcourir le tableau des anciennes solutiuons
                    logger.trace("[JoueurOrdi] Proposition (int). comparaison aux anciennes propositions : Pn=" + tabSolution.get(i) + "/P1=" + anciennesPropositions.get(j) + "/Scn=" +
                            evaluerProposition(tabSolution.get(i), anciennesPropositions.get(j)) + "/Sci=" + anciennesEvaluations.get(j));
                    if (evaluerProposition(tabSolution.get(i), anciennesPropositions.get(j)) != anciennesEvaluations.get(j)) {
                            trouve = false;
                    }
                }
                i++; //tabSolution suivant
            }while (!trouve); // tant qu'on a pas trouvé une proposition répondant aux critères
            logger.trace("[JoueurOrdi] Proposition (int). nouvelle propal=" + tabSolution.get(i-1));
            nouvelleProposition =tabSolution.get(i-1);
        }
        logger.debug("[JoueurOrdi] proposition (int) avec en sortie evaluatiion=" + evaluation + '.');
        System.out.println("proposition : " + nouvelleProposition);
        anciennesPropositions.add(nouvelleProposition);
        return nouvelleProposition;
    }

    /**
     * Evaluer la proposition pour le Mastermind
     * @param proposition La proposition du Challenger
     * @param codeSecret le code secret du défenseur
     * @return L'évaluation sous forme d'un nombre sur 2 chiffres XY (X symboles bien placés, Y symboles mal placés)
     */
    public int evaluerProposition (String proposition, String codeSecret){
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
     * Génération aléatoire d'un code secret par l'ordinateur
     * @return Un code secret
     */
    @Override
    public String genererCodeSecret() {
        logger.debug("[JoueurOrdi] genererCodeSecret");
        return genererPropositionAleatoirement();
    }

    /**
     * Génération d'une proposition aléatoire
     * @return la proposition sous forme de chaine
     */
    private String genererPropositionAleatoirement() {
        logger.debug("[JoueurOrdi] genererPropositionAleatoire");
        Random rnd = new Random();
        StringBuilder uneProposition = new StringBuilder();
        int i=0;
        char unCar;
        do {   // Création d'une proposition aléatoire de longueur paramétrée
            unCar = String.valueOf(rnd.nextInt(ParametresDuJeu.NB_MAX_SYMBOLES)).charAt(0);  //Une valeur aléatoire entre 0 et NB_MAXSymbole-1
            uneProposition.append(unCar);
            i++;
        }while (i < ParametresDuJeu.LONGUEUR_CODE_SECRET);
        return uneProposition.toString();
    }

    /**
     * Détermine les solutions possibles du PlusMoins sous forme de 2 bornes Min et Max
     */
//    @Override
    public void initialiserSolutionsPlusMoins(){
        logger.debug("[JoueurOrdi] initialisaerSolutionsPlusMoins");
        String chaine = "";
        for (int i = 0; i < ParametresDuJeu.LONGUEUR_CODE_SECRET; i++) {
            solutionMax.append(chaine.valueOf(ParametresDuJeu.NB_MAX_SYMBOLES-1)); // création d'une chaine avec que des symboles max
            solutionMin.append("0");   // chaine avec des symboles min
        }
//        System.out.println("Initialisation des bornes :" + solutionMax +"/"+ solutionMin);
    }

    /**
     * Alimente un tableau de String correspondant à l'ensemble des solutions possibles du <b>Mastermind</b>
     */
    public void initialiserSolutionsMastermind() {

        logger.debug("[JoueurOrdi] genererSolutionsMastermind");
        //Nombre de combinaisons possibles
        int tailleMaxDuTableau = (int) Math.pow(ParametresDuJeu.NB_MAX_SYMBOLES, ParametresDuJeu.LONGUEUR_CODE_SECRET);
        String ligne="";
        for (int i = 0; i < tailleMaxDuTableau; i++) {
            ligne =  ligne.valueOf(base(i,ParametresDuJeu.NB_MAX_SYMBOLES));
            if (ligne.length()<ParametresDuJeu.LONGUEUR_CODE_SECRET) ligne = completerZeroAGauche(ligne);
            tabSolution.add(ligne);
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
        logger.trace("[JoueurOrdi] completerZeroAGauche. En sortie : " + stb.toString() + '.');
        return stb.toString();
    }

}

