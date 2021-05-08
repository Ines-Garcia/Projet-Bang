package fr.umontpellier.iut.bang.cards;

import fr.umontpellier.iut.bang.Player;

public class Barrel extends BlueCard {
//planque
    public Barrel(int value, CardSuit suit) {
        super("Barrel", value, suit);
    }

    public void playedBy(Player player){
        super.playedBy(player);
    }

    public boolean canPlayFromHand(Player player) {
        super.canPlayFromHand(player);
        if (player.getGame().getCurrentPlayer()==player){ //si c'est le tour du joueur passé en parametre
            if (player.getHand().contains(this)){ //si le joueur en parametre a la carte en main //pas besoin car playFromHand test deja si carte presente dans la main
                if (player.getInPlay().contains(this)){
                    return false;
                }
                return true;
            }
            return false;
        }
        return false;
    }

}
