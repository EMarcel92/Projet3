package manu.tuto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CodeurOrdiTest {
    CodeurOrdi codeurOrdi = new CodeurOrdi();
    StringBuilder stb = new StringBuilder();

    @BeforeEach
    public void initilisation(){
    }

    @Test
    void genererCodeSecretTest() {
        ParametresDuJeu.NB_MAX_SYMBOLES=4;
        ParametresDuJeu.LONGUEUR_CODE_SECRET=4;
        codeurOrdi.genererCodeSecret();
        String  monCode = codeurOrdi.getCodeSecret();
        assertEquals(monCode.length(),ParametresDuJeu.LONGUEUR_CODE_SECRET);
    }

    @Test
    void evaluerTest() {
        ParametresDuJeu.NB_MAX_SYMBOLES=4;
        ParametresDuJeu.LONGUEUR_CODE_SECRET=4;
        codeurOrdi.setCodeSecret("0123");
        assertEquals("--=+",codeurOrdi.evaluerProposition("2222"));
    }

    @Test
    void evaluerPropositionTest (){
        ParametresDuJeu.NB_MAX_SYMBOLES=5;
        ParametresDuJeu.LONGUEUR_CODE_SECRET=4;
        assertEquals(11,codeurOrdi.evaluerProposition("2212","1234"));
    }

    @Test
    void evaluerPropositionTest2 (){
        ParametresDuJeu.NB_MAX_SYMBOLES=5;
        ParametresDuJeu.LONGUEUR_CODE_SECRET=4;
        assertEquals(40,codeurOrdi.evaluerProposition("1234","1234"));
    }

    @Test
    void evaluerPropositionTest3 (){
        ParametresDuJeu.NB_MAX_SYMBOLES=5;
        ParametresDuJeu.LONGUEUR_CODE_SECRET=4;
        assertEquals(4,codeurOrdi.evaluerProposition("1122","2211"));
    }

}