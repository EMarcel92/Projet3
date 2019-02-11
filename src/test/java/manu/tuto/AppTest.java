package manu.tuto;


import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Unit test for simple App.
 */
public class AppTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    @BeforeEach
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
    }

    @AfterEach
    public void restoreStreams() {
        System.setOut(System.out);
    }

    Affichage affichage = new Affichage();

    @Test
    public void Given_playingMastermind_When_StartingProgram_Then_DisplayWelcome() {
        affichage.bienvenue();
        assertEquals("Bienvenue dans Plus-Moins / Mastermind\n", outContent.toString().replace("\r\n", "\n"));
    }


}
