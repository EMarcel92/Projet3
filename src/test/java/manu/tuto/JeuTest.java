package manu.tuto;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class JeuTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    @BeforeEach
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
    }

    @AfterEach
    public void restoreStreams() {
        System.setOut(System.out);
    }

    @Test
    public void bienvenueTest() {
        Jeu jeu = new Jeu();
        jeu.bienvenue();
        assertEquals("******************************************\n" +
                "* Bienvenue dans Plus-Moins / Mastermind *\n" +
                "******************************************\n\n", outContent.toString().replace("\r\n", "\n"));
    }

}