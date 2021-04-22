package fr.umontpellier.iut.bang.cards;

import fr.umontpellier.iut.bang.Player;

public class Volcanic extends WeaponCard {
    public Volcanic(int value, CardSuit suit) {
        super("Volcanic", value, suit);
    }

    public int getRange() {
        return 1;
    }

    public void playedBy(Player player) {
        super.playedBy(player);
        player.setWeapon(this);
    }
}
