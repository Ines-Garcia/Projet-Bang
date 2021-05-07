package fr.umontpellier.iut.bang.cards;

import fr.umontpellier.iut.bang.Player;

import java.util.List;

public class Saloon extends OrangeCard {

    public Saloon(int value, CardSuit suit) {
        super("Saloon", value, suit);
    }


    public void playedBy(Player player) {
        super.playedBy(player);
        List<Player> joueurs = player.getOtherPlayers();
        joueurs.add(player);
        for (Player i : joueurs){
            i.incrementHealth(1);
        }
    }
}
