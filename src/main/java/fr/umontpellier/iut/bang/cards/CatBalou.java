package fr.umontpellier.iut.bang.cards;

import fr.umontpellier.iut.bang.Player;

import java.util.ArrayList;
import java.util.List;

public class CatBalou extends OrangeCard {
//coup de foudre
    public CatBalou(int value, CardSuit suit) {
        super("Cat Balou", value, suit);
    }

    public void playedBy(Player player) {
        super.playedBy(player);
        List<Player> playerRestant = player.getOtherPlayers();
        Player playerCible = player.choosePlayer("Séléctionne ta cible", playerRestant, false); //choisis la cible
        Card cardPicked = playerCible.chooseCard("Séléctionne la carte", playerCible.getAllCards(), true, false);
        playerCible.discardFromHand(cardPicked); //defausse la carte de la main du joueur

        if (playerCible.getInPlay().contains(cardPicked)){
           //playerCible.add;
        }
    }
}
