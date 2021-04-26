package fr.umontpellier.iut.bang.cards;

import fr.umontpellier.iut.bang.Player;

import java.util.List;

public class Remington extends WeaponCard {
    public Remington(int value, CardSuit suit) {
        super("Remington", value, suit);
    }
//Remington : distance 3
    @Override
    public int getRange() {
        return 3;
    }

    @Override
    public void playedBy(Player player) {
        super.playedBy(player);
    }
}
