package manu.tuto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.fail;

class CodeSecretTest {
    //    ArrayList <String> codeSecret = new ArrayList<>();
    CodeSecret codeSecret;
    StringBuilder stb;
    Jeu jeu;

    @BeforeEach
    public void initilisation(){
        codeSecret = new CodeSecret();
        stb = new StringBuilder();
        jeu =new Jeu();
    }

    @Test
    public void testCodeSansDoublon() {     // test de l'absence de doublon dans les codes générés aléatoirement
        for (int i = 0; i < 5; i++) {   //test de 5 codes successivement
            codeSecret.genererCodeSecret();
            ArrayList<Character> myCypher = codeSecret.getCodeTableau();
            for (int j = 0; j < myCypher.size() - 1; j++) {   // comparaison du j ème caractère avec les k suivants
                for (int k = j + 1; k < myCypher.size(); k++) {
                    if (myCypher.get(j).equals(myCypher.get(k))) {
                        stb.append("codeSecret " + i + '\n');
                        for (Character mychar : codeSecret.getCodeTableau()) {
                            stb.append(mychar);
                        }
                        stb.append('\n');
                        System.out.println(stb.toString());
                        fail("!!!!!!!!!!!!!!!! Il y a un doublon !!!!!!!!!!!!");
                    }
                }
            }
        }
    }

}