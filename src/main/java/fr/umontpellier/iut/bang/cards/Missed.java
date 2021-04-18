package fr.umontpellier.iut.bang.cards;

import fr.umontpellier.iut.bang.Player;

import java.util.List;

public class Missed extends OrangeCard {

    public Missed(int value, CardSuit suit) {
        super("Missed!", value, suit);
    }

    public void playedBy(Player player) {
        super.playedBy(player);
        player.discardFromHand(this); //retire la carte de la main du joueur
    }

}
