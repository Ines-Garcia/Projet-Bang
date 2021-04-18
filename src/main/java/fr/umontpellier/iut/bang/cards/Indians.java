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
        choice.add("Oui");
        choice.add("Non");

        for (Player p : joueurs){
            if (p.getHand().contains("Bang")) {
                if (p.choose("Voulez vous jouer un Bang", choice, true, false) == "") {
                    p.decrementHealth(1, player);
                } else {
                    //retirer la carte bang de la main du joueur
                }
            }
        }
    }

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
