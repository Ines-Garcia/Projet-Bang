package fr.umontpellier.iut.bang.cards;

import fr.umontpellier.iut.bang.Player;

import java.util.List;

public class Beer extends OrangeCard {

    public Beer(int value, CardSuit suit) {
        super("Beer", value, suit);
    }

    public void playedBy(Player player){
        super.playedBy(player);
        List<Player> joueurs = player.getOtherPlayers();
        if (joueurs.size()!=2) {   // les bières n'ont pas d'effet si il ne reste que 2 joueurs
            player.incrementHealth(1); //incrementHealth vérifie si pv>max
        }
    }

    public boolean canPlayFromHand(Player player) {
        if (player.getGame().getCurrentPlayer()==player){ //si c'est le tour du joueur passé en parametre
            if (player.getHand().contains(this)){ //si le joueur en parametre a la carte en main //pas besoin car playFromHand test deja si carte presente dans la main
                if (player.getOtherPlayers().size()<2){ //si ya plus de 2 joueurs
                    return true;
                }
                return false;
            }
            return false;
        }
        return false;
    }

}
