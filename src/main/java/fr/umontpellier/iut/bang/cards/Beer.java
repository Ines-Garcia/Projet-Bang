package fr.umontpellier.iut.bang.cards;

import fr.umontpellier.iut.bang.Player;

public class Beer extends OrangeCard {

    public Beer(int value, CardSuit suit) {
        super("Beer", value, suit);
    }

    public void ajouterPDV(Player player){
        super.playedBy(player);
        if (!player.isDead()){ //vérif si pdv > 0 (vivant)
            player.incrementHealth(1); //incrementHealth vérifie si pv>max
        }
    }
}
