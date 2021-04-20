package fr.umontpellier.iut.bang.cards;

import fr.umontpellier.iut.bang.Player;

import java.util.ArrayList;
import java.util.List;

public class Gatling extends OrangeCard {

    public Gatling(int value, CardSuit suit) {
        super("Gatling", value, suit);
    }

    @Override
    public void playedBy(Player player) {
        super.playedBy(player);
        List<Player> joueurs = player.getOtherPlayers();
        List<String> choice = new ArrayList<>();
        choice.add("Missed!");
        choice.add("");

        for (Player p : joueurs){
            if (p!=player){
                if (p.getHand().contains(p.getCardInHand("Missed!"))) {
                    if (p.choose("Voulez vous utiliser un Missed ? ", choice, false, true).equals("")) { //demande si le joueur veux perdre un point de vie
                        p.decrementHealth(1, player);
                    } else {
                        p.discardFromHand(p.getCardInHand("Missed!")); //enleve le bang de la main du joueur
                    }
                }
                p.decrementHealth(1, player);
            }
        }
    }

    // Si les autre joueur ont un rater ils ne perde pas de point de vie

    public boolean canPlayFromHand(Player player) {
        if (player.getGame().getCurrentPlayer()==player){ //si c'est le tour du joueur passé en parametre
            if (player.getHand().contains(this)){ //si le joueur en parametre a la carte en main //pas besoin car playFromHand test deja si carte presente dans la main
                return true;
            }
            return false;
        }
        return false;
    }
}
