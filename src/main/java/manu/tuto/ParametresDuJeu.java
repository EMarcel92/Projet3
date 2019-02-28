package manu.tuto;

import org.apache.log4j.Logger;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class ParametresDuJeu {
    public static int LONGUEUR_CODE_SECRET;   //Nombre de symboles du code secret
    public static int NB_MAX_SYMBOLES;      // Nombre de valeurs possibles pour un symbole, de 0 à (NB_MAX_SYMBOLES - 1)
    public static int NB_MAX_ESSAIS;       //Nombre d'essais max possibles pour trouver la combinaison
    public static boolean MODE_DEV;  // Mode développeur permettant d'afficher de code secret avant de démarrer la partie

    private static Logger logger = Logger.getLogger(Main.class);

    /**
     * Récupère les paramètres du jeu à partir d'un fichier de paramètres (config.properties)
     */
    public static void intialiserLesParametres(){
        try{
            // chargement des propriétés
            Properties prop = lireFichierParametres("C:\\Users\\S061980\\IdeaProjects\\mastermind\\src\\main\\resources\\config.properties");
            String longueurCodeSecret = prop.getProperty("longueurCodeSecret","4");
            String nbMaxSymboles = prop.getProperty("nbMaxSymboles","6");
            String nbMaxEssais = prop.getProperty("nbMaxEssais","12");
            String modedev = prop.getProperty("modedev","true");
            logger.debug("[ParametresuJeu] Paramètres récupérés du fichier=" + longueurCodeSecret + "," + nbMaxSymboles + "," + nbMaxEssais + "," + modedev);

            LONGUEUR_CODE_SECRET = longueurCodeSecretValide(longueurCodeSecret);
            NB_MAX_SYMBOLES = nbMaxSymbolesValide(nbMaxSymboles);
            NB_MAX_ESSAIS = nbMaxEssaisValide(nbMaxEssais);
            if (MODE_DEV != true){  // S'il est à true, c'est qu'il a été initialisé par l'argument à l'appel du Main
                MODE_DEV = modeDevValide(modedev);
            }
            logger.debug("Paramètres validés=" + LONGUEUR_CODE_SECRET + "," + NB_MAX_SYMBOLES + "," + NB_MAX_ESSAIS + "," + MODE_DEV);
        }
        catch(Exception e){
            System.out.println("Exception: " + e);
        }
    }

    private static Properties lireFichierParametres(String filename) throws IOException, FileNotFoundException{
        Properties properties = new Properties();
        FileInputStream input = new FileInputStream(filename);
        try {
            properties.load(input);
            return properties;
        }
        finally{
            input.close();
        }
    }

    /**
     * Validation de la longueur du code secret issu du fichier paramètre
     * @param longueurCodeSecret la longueur du code secret (chaine de caratère)
     * @return La longueur du code secret (numérique) validée
     */
    private static int longueurCodeSecretValide (String longueurCodeSecret) {
        Integer longueurCodeSecretInt = 0;
        try {
            longueurCodeSecretInt = new Integer(longueurCodeSecret);
            if (longueurCodeSecretInt > 6){
                System.out.println("La longueur du code secret en fichier paramètre '" + longueurCodeSecretInt + "' est très élevé.");
            }
        }catch (NumberFormatException e){
            System.out.println("exception : " + e);
            System.out.println("La valeur de la longueur du code secret en paramètre n'est pas numérique : " + longueurCodeSecret);
        }
        return longueurCodeSecretInt.intValue();
    }

    /**
     * Validation du nombre maximum d'essais possibles, issu du fichier de paramètres
     * @param nbMaxEssais le nombre maximum d'essais possibles (chaine de caractère)
     * @return le nombre maximum d'essais possibles (numérique)
     */
    private static int nbMaxEssaisValide(String nbMaxEssais) {
        Integer nbMaxEssaisInt = 0;
        try {
            nbMaxEssaisInt = new Integer(nbMaxEssais);
            if (nbMaxEssaisInt > 99){
                System.out.println("Le nombre maximum d'essais en fichier paramètre '" + nbMaxEssaisInt + "' est très élevé.");
            }
        }catch (NumberFormatException e){
            System.out.println("exception : " + e);
            System.out.println("Le nombre d'essais en paramètre n'est pas numérique : " + nbMaxEssais);
        }
        return nbMaxEssaisInt.intValue();
    }

    /**
     * Validation du mode dév (true ou false) issu du fichier paramètre
     * @param modeDev le mode dév (chaine de caratères)
     * @return Le mode dév (booléen)
     */
    private static boolean modeDevValide(String modeDev) {
        Boolean modedevBooleen = true;
        try {
            modedevBooleen = new Boolean(modeDev);
            System.out.println("Nombre d'essais maximum = " + modedevBooleen.toString());
        }catch (NumberFormatException e){
            System.out.println("exception : " + e);
            System.out.println("Le paramètre mode développeur est incorrect (true/false) : " + modeDev);
        }
        return modedevBooleen.booleanValue();
    }

    /**
     * Validation du nombre de symboles différents possibles, issu du fichier de paramètres
     * @param nbMaxSymboles le nombre de symboles possibles (chaine de caractère)
     * @return le nombre de symboles possibles (numérique)
     */
    private static int nbMaxSymbolesValide (String nbMaxSymboles) {
        Integer nbMaxSymbolesInt = 0;
        try {
            nbMaxSymbolesInt = new Integer(nbMaxSymboles);
            if (nbMaxSymbolesInt > 9){
                System.out.println("La valeur maximale des symboles en fichier paramètre '" + nbMaxSymbolesInt + "' est incorrecte (9 maximum)");
            }
        }catch (NumberFormatException e){
            System.out.println("exception : " + e);
            System.out.println("La valeur maximale des symboles en paramètre n'est pas nuémrique : " + nbMaxSymboles);
        }
        return nbMaxSymbolesInt.intValue();
    }

}
