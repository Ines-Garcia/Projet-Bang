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
        super.playedBy(player);//
        List<Player> playerRestant = player.getOtherPlayers();
        List<String> cardName = new ArrayList<>();

        Player playerCible = player.choosePlayer("Séléctionne ta cible", playerRestant, false); //choisis la cible
        Card cardChoisis = player.chooseCard("Choisis une carte du joueur cible", playerCible.getAllCards(), true, false); //recup les cartes en main du joueur cible

    }
}
