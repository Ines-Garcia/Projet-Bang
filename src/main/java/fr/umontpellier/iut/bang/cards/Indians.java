package fr.umontpellier.iut.bang.cards;

import fr.umontpellier.iut.bang.Player;

import java.util.ArrayList;
import java.util.List;

public class Indians extends OrangeCard {

    public Indians(int value, CardSuit suit) {
        super("Indians!", value, suit);
    }

    @Override
    public void playedBy(Player player) {
        super.playedBy(player);
        List<Player> joueurs = player.getOtherPlayers();
        List<String> choice = new ArrayList<>();
        choice.add("Bang!");
        choice.add("");

        for (Player p : joueurs){
            if (p.getHand().contains(p.getCardInHand("Bang!"))) {
                if (p.choose("Voulez vous défausser un Bang ? ", choice, false, true).equals("")) { //demande si le joueur veux perdre un point de vie
                    p.decrementHealth(1, player);
                } else {
                    p.discardFromHand(p.getCardInHand("Bang!")); //enleve le bang de la main du joueur
                }
            }
            else {
                p.decrementHealth(1,player);
            }
        }
    }

    public boolean canPlayFromHand(Player player) {
        return super.canPlayFromHand(player);
    }

}
