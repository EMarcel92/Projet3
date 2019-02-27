package manu.tuto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class JoueurOrdiTest {


    @BeforeEach
    public void initialisation () {

    }

    @Test
    public void completerZeroAGaucheTest (){
        JoueurOrdi joueurOrdi = new JoueurOrdi();
        ParametresDuJeu.LONGUEUR_CODE_SECRET=5;
        String maString = "ab";
        assertEquals("000ab",joueurOrdi.completerZeroAGauche(maString));
    }

    @Test
    public void propositionPlusMoinsTest () {
        JoueurOrdi joueurOrdi = new JoueurOrdi();
        JoueurHumain joueurHumain = new JoueurHumain();
        PlusMoins plusMoins = new PlusMoins(joueurOrdi,joueurHumain);
        joueurOrdi.setPropositionPrecedente("1254");
        ParametresDuJeu.NB_MAX_SYMBOLES=6;
        ParametresDuJeu.LONGUEUR_CODE_SECRET=4;
        joueurOrdi.initialiserSolutionsPlusMoins();
        assertEquals("0054",joueurOrdi.proposition("--+="));
    }

    @Test
    void initialiserSolutionsMastermind() {
        ParametresDuJeu.NB_MAX_SYMBOLES=3;
        ParametresDuJeu.LONGUEUR_CODE_SECRET=3;
        JoueurOrdi joueurOrdi = new JoueurOrdi();
        joueurOrdi.initialiserSolutionsMastermind();
        assertEquals(27,joueurOrdi.getTabSolution().size());
    }

    @Test
    void   evaluerPropositionMastermindOK() {
        ParametresDuJeu.NB_MAX_SYMBOLES=6;
        ParametresDuJeu.LONGUEUR_CODE_SECRET=4;
        JoueurOrdi joueurOrdi = new JoueurOrdi();
        assertEquals(40,joueurOrdi.evaluerProposition("1234","1234"));
    }

    @Test
    void  evaluerPropositionMastermind() {
        ParametresDuJeu.NB_MAX_SYMBOLES=6;
        ParametresDuJeu.LONGUEUR_CODE_SECRET=4;
        JoueurOrdi joueurOrdi = new JoueurOrdi();
        assertEquals(12,joueurOrdi.evaluerProposition("1234","0132"));
    }

    @Test
    void genererUneSuiteDeCodeSecret(){
        ParametresDuJeu.NB_MAX_SYMBOLES=6;
        ParametresDuJeu.LONGUEUR_CODE_SECRET=4;
        JoueurOrdi joueurOrdi = new JoueurOrdi();
        System.out.println("les codes suivants devraient être tous différents :");
        System.out.println(joueurOrdi.genererCodeSecret());
        System.out.println(joueurOrdi.genererCodeSecret());
        System.out.println(joueurOrdi.genererCodeSecret());
        System.out.println(joueurOrdi.genererCodeSecret());
        System.out.println(joueurOrdi.genererCodeSecret());
    }

    @Test
    void initialiserSolutionsPlusMoinsTest(){
        ParametresDuJeu.NB_MAX_SYMBOLES=6;
        ParametresDuJeu.LONGUEUR_CODE_SECRET=4;
        JoueurOrdi joueurOrdi = new JoueurOrdi();
        joueurOrdi.initialiserSolutionsPlusMoins();
        assertEquals("5555", joueurOrdi.getSolutionMax().toString());
        assertEquals("0000", joueurOrdi.getSolutionMin().toString());
    }
}