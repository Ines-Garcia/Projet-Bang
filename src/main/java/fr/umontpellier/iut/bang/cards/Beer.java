package fr.umontpellier.iut.bang.cards;

import fr.umontpellier.iut.bang.Player;

public class Beer extends OrangeCard {

    public Beer(int value, CardSuit suit) {
        super("Beer", value, suit);
    }

    public void ajouterPDV(Player p1){
        if (!p1.isDead()){ //vérif si pdv > 0 (vivant) et pdv < maxpdv
            p1.incrementHealth(1);
        }
    }
}
