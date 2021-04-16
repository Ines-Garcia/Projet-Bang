package fr.umontpellier.iut.bang.cards;

public class Winchester extends WeaponCard {
    public Winchester(int value, CardSuit suit) {
        super("Winchester", value, suit);
    }
// winchester : distance 5
    @Override
    public int getRange() {
        return 5;
    }
}
