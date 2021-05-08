package fr.umontpellier.iut.bang.cards;

import fr.umontpellier.iut.bang.Player;

public class Mustang extends BlueCard {

    public Mustang(int value, CardSuit suit) {
        super("Mustang", value, suit);
    }

    public void playedBy(Player player){
        super.playedBy(player);
    }

    public boolean canPlayFromHand(Player player) {
        return super.canPlayFromHand(player);
    }


}
