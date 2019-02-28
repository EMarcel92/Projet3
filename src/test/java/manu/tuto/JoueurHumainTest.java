package manu.tuto;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests des méthodes de la classe JoueurHumain
 */
class JoueurHumainTest {
//    Joueur joueur = new JoueurOrdi();
    JoueurHumain joueur = new JoueurHumain();
//    PlusMoins plusMoins = new PlusMoins(joueur,joueur2);
//    Mastermind mastermind = new Mastermind(joueur,joueur2);
//    StringBuilder stb = new StringBuilder();

    @Test
    void genererCodeSecret() {
    }

    @Test
    void checkSaisieOK() {
        ParametresDuJeu.NB_MAX_SYMBOLES=6;
        ParametresDuJeu.LONGUEUR_CODE_SECRET=4;
        assertTrue(joueur.checkSaisieOK("1234"));
    }

    @Test
    void checkSaisieKO() {
        ParametresDuJeu.NB_MAX_SYMBOLES=6;
        ParametresDuJeu.LONGUEUR_CODE_SECRET=4;
        assertFalse(joueur.checkSaisieOK("123"));
        assertFalse(joueur.checkSaisieOK("abcd"));
        assertFalse(joueur.checkSaisieOK(""));
        assertFalse(joueur.checkSaisieOK("1236"));
    }

    @Test
    void checkEvaluationOK() {
        assertTrue(joueur.checkEvaluationOK("40"));
    }

    @Test
    void checkEvaluationOKFalse() {
        assertFalse(joueur.checkEvaluationOK ("A"));
        assertFalse(joueur.checkEvaluationOK ("100"));
        assertFalse(joueur.checkEvaluationOK ("1"));
    }
}