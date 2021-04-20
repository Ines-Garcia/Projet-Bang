package fr.umontpellier.iut.bang.cards;

import fr.umontpellier.iut.bang.Player;

public class Scope extends BlueCard {
//lunette
    public Scope(int value, CardSuit suit) {
        super("Scope", value, suit);
    }

    public void playedBy(Player player){
        super.playedBy(player);
    }

}
