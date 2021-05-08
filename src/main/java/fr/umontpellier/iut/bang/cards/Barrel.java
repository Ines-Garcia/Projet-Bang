package fr.umontpellier.iut.bang.cards;

import fr.umontpellier.iut.bang.Player;

public class Barrel extends BlueCard {
//planque
    public Barrel(int value, CardSuit suit) {
        super("Barrel", value, suit);
    }

    public void playedBy(Player player){
        super.playedBy(player);
    }

    public boolean canPlayFromHand(Player player) {
        return super.canPlayFromHand(player);
    }

}
