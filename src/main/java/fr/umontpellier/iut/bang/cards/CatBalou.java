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
        List<String> mainOuInPlay = new ArrayList<>();
        mainOuInPlay.add("");
        mainOuInPlay.add("InPlay");

        Player playerCible = player.choosePlayer("Sélectionne ta cible", playerRestant, false); //choisis la cible
        String choix = player.choose("Voulez vous prendre une carte dans la main du joueur, ou dans le inPlay ?",mainOuInPlay,true,false);
        if (choix.equals("")){
           player.discard(playerCible.removeRandomCardFromHand());
        }else{
            List<BlueCard> cartesEnJeu = player.getInPlay();
            Card cardPicked = playerCible.chooseBlueCard("Séléctionne la carte",cartesEnJeu, true, false);
            playerCible.discardFromHand(cardPicked); //defausse la carte de la main du joueur
        }
    }
}
