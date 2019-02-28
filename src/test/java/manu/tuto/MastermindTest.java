package manu.tuto;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MastermindTest {
    Joueur joueur = new JoueurOrdi();
    JoueurHumain joueur2 = new JoueurHumain();
    Mastermind mastermind = new Mastermind(joueur,joueur2);

    @Test
    public void cEstGagneTest (){
        ParametresDuJeu.LONGUEUR_CODE_SECRET=4;
        assertTrue(mastermind.cEstGagne(40));
    }

   @Test
    public void cEstGagneTestKO (){
        ParametresDuJeu.LONGUEUR_CODE_SECRET=4;
        assertFalse(mastermind.cEstGagne(4));
    }

}