package manu.tuto;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class JoueurHumainTest {
//    Joueur joueur = new JoueurOrdi();
    JoueurHumain joueur2 = new JoueurHumain();
//    PlusMoins plusMoins = new PlusMoins(joueur,joueur2);
//    Mastermind mastermind = new Mastermind(joueur,joueur2);
//    StringBuilder stb = new StringBuilder();

    @Test
    void genererCodeSecret() {
    }

    @Test
    void checkSaisieOK() {

    }

    @Test
    void evaluerProposition() {

    }

    @Test
    void checkEvaluationOK() {
//        ParametresDuJeu.NB_MAX_SYMBOLES=4;
//        ParametresDuJeu.LONGUEUR_CODE_SECRET=4;
        //   JoueurHumain joueur2 = new JoueurHumain();
        //mastermind.setCodeSecret("0123");
        assertTrue(joueur2.checkEvaluationOK("40"));
    }

    @Test
    void checkEvaluationOKFalse() {
        assertFalse(joueur2.checkEvaluationOK ("A"));
        assertFalse(joueur2.checkEvaluationOK ("100"));
        assertFalse(joueur2.checkEvaluationOK ("1"));
    }
}