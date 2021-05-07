package fr.umontpellier.iut.bang.cards;

import fr.umontpellier.iut.bang.Player;

import java.util.List;

public class Beer extends OrangeCard {

    public Beer(int value, CardSuit suit) {
        super("Beer", value, suit);
    }

    public void playedBy(Player player){
        super.playedBy(player);
        player.incrementHealth(1); //incrementHealth vérifie si pv>max
    }

    public boolean canPlayFromHand(Player player) {
        if (player.getOtherPlayers().size() > 1) { //si ya plus de 2 joueurs
            return true;
        } else {
            return false;
        }
    }

}
