package fr.umontpellier.iut.bang.cards;

public class Remington extends WeaponCard {
    public Remington(int value, CardSuit suit) {
        super("Remington", value, suit);
    }
//Remington : distance 3
    @Override
    public int getRange() {
        return 3;
    }

}
