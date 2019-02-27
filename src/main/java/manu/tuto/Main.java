package manu.tuto;

import org.apache.log4j.Logger;

/**
 * Projet 3 Formation Java OpenClassroom : Jeu du plus-Moins/Mastermind
 * @author Emmanuel MARCEL
 * @version 0.1
 */
public class Main {

    private static Logger logger = Logger.getLogger(Main.class);

    public static void main(String[] args) {

        logger.debug("msg de debogage");
        logger.info("msg d'information");
        logger.warn("msg d'avertissement");
        logger.error("msg d'erreur");
        logger.fatal("msg d'erreur fatale");

        logger.debug("[Main] Démarrage de Main");

        for( String param : args ) {
            logger.debug("[Main] Paramètre Args=" + param + '.');
            if (param == "modedev"){
                ParametresDuJeu.MODE_DEV = true ;

                logger.debug("Main : MODEDEV est à true");
            }
        }
        Jeu jeu = new Jeu();
        jeu.lancerLeJeu();
    }








}
