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
        List<String> choice = new ArrayList<>();
        choice.add("");


        Player playerCible = player.choosePlayer("Sélectionne ta cible", playerRestant, false); //choisis la cible
        List<BlueCard> carteEnJeuCible = playerCible.getInPlay();
        for (BlueCard c : carteEnJeuCible){
            choice.add(c.getName());
        }
        String choix = player.choose("Voulez vous prendre une carte dans la main du joueur, ou dans le inPlay ?",choice,true,false);
        if (choix.equals("")){
           playerCible.discard(playerCible.removeRandomCardFromHand());
        }else{
            BlueCard carteAenlever = playerCible.getCardInPlay(choix);
            playerCible.removeFromInPlay(playerCible.getCardInPlay(choix));
            playerCible.discard(carteAenlever);
        }
    }
}
