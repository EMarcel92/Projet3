package manu.tuto;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class ParametresDuJeu {
    public static int LONGUEUR_CODE_SECRET;   //Nombre de symboles du code secret
    public static int NB_MAX_SYMBOLES;      // Nombre de valeurs possibles pour un symbole, de 0 à (NB_MAX_SYMBOLES - 1)
    public static int NB_MAX_ESSAIS;       //Nombre d'essais max possibles pour trouver la combinaison
    public static boolean MODE_DEV;  // Mode développeur permettant d'afficher de code secret avant de démarrer la partie

    public static void intialiserLesParametres(){
        try{
            // chargement des propriétés
            Properties prop = lireFichierParametres("C:\\Users\\S061980\\IdeaProjects\\mastermind\\src\\main\\resources\\config.properties");
            String longueurCodeSecret = prop.getProperty("longueurCodeSecret","4");
            String nbMaxSymboles = prop.getProperty("nbMaxSymboles","6");
            String nbMaxMaxEssais = prop.getProperty("nbMaxMaxEssais","12");
            String modedev = prop.getProperty("modedev","true");
            System.out.println("Paramètres récupérés du fichier=" + longueurCodeSecret + "," + nbMaxSymboles + "," + nbMaxMaxEssais + "," + modedev);

            LONGUEUR_CODE_SECRET = longueurCodeSecretValide(longueurCodeSecret);
            System.out.println(LONGUEUR_CODE_SECRET);
            NB_MAX_SYMBOLES = nbMaxSymbolesValide(nbMaxSymboles);
            NB_MAX_ESSAIS = nbMaxMaxEssaisValide(nbMaxMaxEssais);
            MODE_DEV = modedevValide(modedev);
            System.out.println("Paramètres validés=" + LONGUEUR_CODE_SECRET + "," + NB_MAX_SYMBOLES + "," + NB_MAX_ESSAIS + "," + MODE_DEV);
        }
        catch(Exception e){
            System.out.println("Exception: " + e);
        }
    }

    private static int longueurCodeSecretValide (String longueurCodeSecret) {
        Integer longueurCodeSecretInt = 0;
        try {
            longueurCodeSecretInt = new Integer(longueurCodeSecret);
            System.out.println("Longueur max = " + longueurCodeSecretInt);
            if (longueurCodeSecretInt > 6){
                System.out.println("La longueur du code secret en fichier paramètre '" + longueurCodeSecretInt + "' est trop élevée pour un cerveau humain");
                //TODO comment arrêter le programe proprement : lancer une exception ?
            }
        }catch (NumberFormatException e){
            System.out.println("exception : " + e);
            System.out.println("La valeur de la longueur du code secret en paramètre n'est pas nuémrique : " + longueurCodeSecret);
        }
        return longueurCodeSecretInt.intValue();
    }

    private static int nbMaxMaxEssaisValide (String nbMaxMaxEssais) {
        Integer nbMaxMaxEssaisInt = 0;
        try {
            nbMaxMaxEssaisInt = new Integer(nbMaxMaxEssais);
            System.out.println("Nombre d'essais maximum = " + nbMaxMaxEssaisInt);
            if (nbMaxMaxEssaisInt > 9){
                System.out.println("Le nombre maximum d'essais en fichier paramètre '" + nbMaxMaxEssaisInt + "' est trop élevé (50 maximum)");
                //TODO comment arrêter le programe proprement : lancer une exception ?
            }
        }catch (NumberFormatException e){
            System.out.println("exception : " + e);
            System.out.println("La valeur maximale des symboles en paramètre n'est pas numérique : " + nbMaxMaxEssais);
        }
        return nbMaxMaxEssaisInt.intValue();
    }

    private static boolean modedevValide (String nbMaxMaxEssais) {
        Boolean modedevBooleen = true;
        try {
            modedevBooleen = new Boolean(nbMaxMaxEssais);
            System.out.println("Nombre d'essais maximum = " + modedevBooleen.toString());
        }catch (NumberFormatException e){
            System.out.println("exception : " + e);
            System.out.println("Le paramètre mode développeur est incorrect (true/false) : " + nbMaxMaxEssais);
        }
        return modedevBooleen.booleanValue();
    }

    private static int nbMaxSymbolesValide (String nbMaxSymboles) {
        Integer nbMaxSymbolesInt = 0;
        try {
            nbMaxSymbolesInt = new Integer(nbMaxSymboles);
            System.out.println("Symbole max = " + nbMaxSymbolesInt);
            if (nbMaxSymbolesInt > 9){
                System.out.println("La valeur maximale des symboles en fichier paramètre '" + nbMaxSymbolesInt + "' est incorrecte (9 maximum)");
                //TODO comment arrêter le programe propr ement : lancer une exception ?
            }
        }catch (NumberFormatException e){
            System.out.println("exception : " + e);
            System.out.println("La valeur maximale des symboles en paramètre n'est pas nuémrique : " + nbMaxSymboles);
        }
        return nbMaxSymbolesInt.intValue();
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

}
 /*
        private static void lireFichierParametres() throws IOException {
            InputStream inputStream = null;
            try {
                Properties prop = new Properties();
                String fichierProperties = "config.properties";
                 inputStream = getClass().getClassLoader().getResourceAsStream(fichierProperties);
                if (inputStream != null) {
                    prop.load(inputStream);
                } else {
                    throw new FileNotFoundException("fichier de paramètres '" + fichierProperties + "' introuvable");
                }
                // get the property value and print it out
                String longueurCodeSecret = prop.getProperty("longueurCodeSecret","4");
                String nbMaxSymboles = prop.getProperty("nbMaxSymboles","6");
                String nbMaxMaxEssais = prop.getProperty("nbMaxMaxEssais","12");
                String modedev = prop.getProperty("modedev","true");

                System.out.println("Paramètres=" + longueurCodeSecret + "," + nbMaxSymboles + ", " + nbMaxMaxEssais + ", " + modedev);
            } catch (Exception e) {
                System.out.println("Exception: " + e);
            } finally {
                inputStream.close();
            }
        }
*/



