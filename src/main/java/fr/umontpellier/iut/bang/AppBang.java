package fr.umontpellier.iut.bang;

import fr.umontpellier.iut.bang.cards.*;
import org.junit.jupiter.api.Test;

import java.util.List;

public class AppBang {
    public static void main(String[] args) {

        // Instancie et exécute une partie
        List<Player> players = Game.makePlayers(new String[]{"John", "Paul", "Ringo", "George"});
        Game g = new Game(players);
        g.run();


    }
}
