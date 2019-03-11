package manu.tuto;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ParametresDuJeuTest {

    @Test
    void intialiserLesParametresTest() {
        ParametresDuJeu parametresDuJeu = new ParametresDuJeu();
        parametresDuJeu.intialiserLesParametres();
        assertEquals(4,ParametresDuJeu.LONGUEUR_CODE_SECRET);
        assertEquals(12,ParametresDuJeu.NB_MAX_ESSAIS);
        assertEquals(6,ParametresDuJeu.NB_MAX_SYMBOLES);
        assertFalse(ParametresDuJeu.MODE_DEV);

    }
}