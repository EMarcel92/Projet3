package manu.tuto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DecodeurOrdiTest {

    @BeforeEach
    public void initialisation () {

    }

    @Test
    public void remplissageTableauSolutionTest () {
        ParametresDuJeu.NB_MAX_SYMBOLES=3;
        ParametresDuJeu.LONGUEUR_CODE_SECRET=4;
      //  ParametresDuJeu.intialiserLesParametres();
        DecodeurOrdi decodeurordi = new DecodeurOrdi();
        decodeurordi.GénérerSolutionsMastermind();
        assertEquals(81,decodeurordi.getTabSolution().size());
    }

    @Test
    public void completerZeroAGaucheTest (){
        DecodeurOrdi decodeurordi = new DecodeurOrdi();
        ParametresDuJeu.LONGUEUR_CODE_SECRET=5;
        String maString = "ab";
        assertEquals("000ab",decodeurordi.completerZeroAGauche(maString));
    }

    @Test
    public void initialiserSolutionsTest () {
        DecodeurOrdi decodeurordi = new DecodeurOrdi();
        ParametresDuJeu.NB_MAX_SYMBOLES=5;
        ParametresDuJeu.LONGUEUR_CODE_SECRET=5;
        decodeurordi.initialiserSolutions();
    }

    @Test
    public void propositionPlusMoinsTest () {
        DecodeurOrdi decodeurordi = new DecodeurOrdi();
        Codeur codeur = new CodeurOrdi();
        codeur.setCodeSecret("0144");
        ParametresDuJeu.NB_MAX_SYMBOLES=5;
        ParametresDuJeu.LONGUEUR_CODE_SECRET=4;
        decodeurordi.initialiserSolutions();
        assertEquals("0044",decodeurordi.proposition("--+=","1234"));

    }
}