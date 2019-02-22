package manu.tuto;

public class ParametresDuJeu {
    public static int LONGUEUR_CODE_SECRET;   //Nombre de symboles du code secret
    public static int NB_MAX_SYMBOLES;      // Nombre de valeurs possibles pour un symbole, de 0 à (NB_MAX_SYMBOLES - 1)
    public static int NB_MAX_ESSAIS;       //Nombre d'essais max possibles pour trouver la combinaison
    public static boolean MODE_DEV;  // Mode développeur permettant d'afficher de code secret avant de démarrer la partie


    public static void intialiserLesParametres(){
        //TODO lire le fichier de config

        LONGUEUR_CODE_SECRET = 3;
        NB_MAX_SYMBOLES = 3;
        NB_MAX_ESSAIS = 3;
        MODE_DEV = true;
    }


//TODO controler longueur max



    //    public static void main( String [] args ) {
//
//        int accumulator = 0;
//
//        for( String param : args ) {
//            accumulator += Integer.parseInt( param );
//        }
//
//        System.out.println( accumulator );
//
//    }



    /**
     * @author Crunchify.com
     *
     */
/*

    public class CrunchifyGetPropertyValues {
        String result = "";
        InputStream inputStream;

        public String getPropValues() throws IOException {

            try {
                Properties prop = new Properties();
                String propFileName = "config.properties";

                inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);

                if (inputStream != null) {
                    prop.load(inputStream);
                } else {
                    throw new FileNotFoundException("property file '" + propFileName + "' not found in the classpath");
                }

                Date time = new Date(System.currentTimeMillis());

                // get the property value and print it out
                String user = prop.getProperty("user");
                String company1 = prop.getProperty("company1");
                String company2 = prop.getProperty("company2");
                String company3 = prop.getProperty("company3");

                result = "Company List = " + company1 + ", " + company2 + ", " + company3;
                System.out.println(result + "\nProgram Ran on " + time + " by user=" + user);
            } catch (Exception e) {
                System.out.println("Exception: " + e);
            } finally {
                inputStream.close();
            }
            return result;
        }
    }
*/



//Contenu fichie properties

/*

#Crunchify Properties
    user=Crunchify
    company1=Google
    company2=eBay
    company3=Yahoo
*/

}
