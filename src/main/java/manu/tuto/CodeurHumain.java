package manu.tuto;

import java.util.Scanner;

/**
 * Le décodeur (celui qui cherche le code secret) est un humain
 */
public class CodeurHumain extends Codeur {

    /**
     * Demande au joueur de saisir un code secret (en tant que codeur)
     */
    @Override
    public void genererCodeSecret() {
//        ArrayList<Character > codeSecret = new ArrayList<>();
        String codeSecret = new String();
        DecodeurHumain decodeur = new DecodeurHumain();
        Scanner sc = new Scanner(System.in);
        do {        // Boucle pour obtenir la saisie d'une proposition valide
            System.out.print("Entrez votre code secret :");
            codeSecret = sc.next();
            if (!decodeur.checkSaisieOK(codeSecret)) {
                System.out.println("   Saisie incorrecte");
            }
        } while (decodeur.checkSaisieOK(codeSecret)==false);
        this.setCodeSecret(codeSecret);
    }

}
