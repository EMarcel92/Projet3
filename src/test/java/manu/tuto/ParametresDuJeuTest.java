package manu.tuto;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ParametresDuJeuTest {

    @Test
    void intialiserLesParametresTest() {
        ParametresDuJeu.intialiserLesParametres();
        assertEquals(4,ParametresDuJeu.LONGUEUR_CODE_SECRET);
        assertEquals(3,ParametresDuJeu.NB_MAX_ESSAIS);
        assertEquals(3,ParametresDuJeu.NB_MAX_SYMBOLES);
        assertTrue(ParametresDuJeu.MODE_DEV);

    }
}