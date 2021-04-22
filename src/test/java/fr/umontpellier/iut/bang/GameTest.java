package fr.umontpellier.iut.bang;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.io.OutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GameTest {

    private Game minimalGame;
    private Player p1, p2,p3,p4,p5;

    @BeforeEach
    void disableConsole() {
        System.setOut(new PrintStream(new OutputStream() {
            @Override
            public void write(int arg0) {

            }
        }));

    }

    @BeforeEach
    void setUp() {
        List<Player> players = Game.makePlayers(new String[]{"Toto", "Titi", "Tutu", "Tata", "Tato"});
        minimalGame = new Game(players);

        p1 = minimalGame.getPlayers().get(0);
        p2 = minimalGame.getPlayers().get(1);
        p3 = minimalGame.getPlayers().get(2);
        p4 = minimalGame.getPlayers().get(3);
        p5 = minimalGame.getPlayers().get(4);

    }


    @Test
    void testGetPlayerDistance() {
        assertEquals(1, minimalGame.getPlayerDistance(p1, p5));
        assertEquals(2, minimalGame.getPlayerDistance(p1, p4));
        assertEquals(2, minimalGame.getPlayerDistance(p1, p3));
    }

    @Test
    void testgetOtherPlayer() {
        List<Player> listPlayerAttendu = new ArrayList<>();
        listPlayerAttendu.add(p1);
        listPlayerAttendu.add(p2);
        listPlayerAttendu.add(p3);
        listPlayerAttendu.add(p4);
        listPlayerAttendu.add(p5);
        assertEquals(listPlayerAttendu, p1.getOtherPlayers());

    }
}