package fr.umontpellier.iut.bang.cards;

import fr.umontpellier.iut.bang.Player;

import java.util.ArrayList;
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
            p.reactToBang(player);
        }
    }

    // Si les autre joueur ont un rater ils ne perde pas de point de vie

    public boolean canPlayFromHand(Player player) {
        return super.canPlayFromHand(player);
    }

}
