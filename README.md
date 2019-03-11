# Projet 3 - Jeux de logique en JAVA

## Contenu
Cette application résulte de la mise en application des premiers cours OpenClassrooms du langage objet JAVA.

Elle permet de jouer à 2 jeux :
- Recherche d'un code (Plus-Moins)
- Mastermind

## Compilation
Prérequis :
 - un **IDE** (IntelliJ) 
 - **Maven**
 - **GIT**
 - java.exe et mvn.exe sont déclarés dans le PATH du système

Pour compiler ce code :
- Cloner le contenu du repository Github avec la commande **GIT CLONE https://github.com/EMarcel92/Projet3**
- Utiliser la commande Maven **MVN CLEAN COMPILE PACKAGE** depuis le répertoire principal du projet.


## Configuration
Un fichier de paramètres **config.properties** permet de gérer la configuration du jeu, avec les clés :

 - **longueurCodeSecret** définit la logueur du code à découvrir
 - **nbMaxSymboles** donne le nombre maximum de valeurs possibles pour un caratère du code à découvrir *(de 4 à 10)*
 - **nbMaxEssais** définit le nombre maximum d'essais autorisés pour trouver le code secret
 - **modedev** active le mode développeur et affiche le code secret à trouver sur la valeur est à *true*
 

## Lancement
Pour lancer l'application, se placer dans le répertoire target et exécuter la commande :

    java -jar mastermind-1.0-SNAPSHOT-jar-with-dependencies.jar
    
Ajouter le motclé modedev pour activer le mode développeur.
