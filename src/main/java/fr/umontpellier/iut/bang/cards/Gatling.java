package fr.umontpellier.iut.bang.cards;

import fr.umontpellier.iut.bang.Player;

import java.util.List;

public class Gatling extends OrangeCard {

    public Gatling(int value, CardSuit suit) {
        super("Gatling", value, suit);
    }

    @Override
    public void playedBy(Player player) {
        super.playedBy(player);
        List<Player> joueurs = player.getOtherPlayers();
        for (Player p : joueurs){
            while (p!=player){
                p.decrementHealth(1,player);
            }
        }
    }
}
