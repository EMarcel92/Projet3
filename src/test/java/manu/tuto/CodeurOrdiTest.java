package manu.tuto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class CodeurOrdiTest {
    CodeurOrdi codeurOrdi = new CodeurOrdi();
    StringBuilder stb = new StringBuilder();

    @BeforeEach
    public void initilisation(){

    }

    @Test
    void genererCodeSecret() {
        codeurOrdi.genererCodeSecret();
        ArrayList<Character> monCode = codeurOrdi.getCodeTableau();
        assertEquals(monCode.size(),ParametresDuJeu.LONGUEUR_CODE_SECRET);
    }
}