package fr.umontpellier.iut.bang.cards;

import fr.umontpellier.iut.bang.Game;
import fr.umontpellier.iut.bang.Player;

import java.util.ArrayList;
import java.util.List;

public class Bang extends OrangeCard {

    public Bang(int value, CardSuit suit) {
        super("Bang!", value, suit);
    }

    @Override
    public void playedBy(Player player) {
        super.playedBy(player);
        List<Player> PlayerAPorte = player.getPlayersInRange(1); //recup les joueurs a porte
        Player playerCible = player.choosePlayer("Séléctionne ta cible", PlayerAPorte, false); //choisis la cible
        playerCible.decrementHealth(1,player); //met a jours les pv
        player.discardFromHand(this); //retire la carte de la main du joueur

        //je choisis le joueur
        //si le joueur n'est pas deja mort
        //si j'ai pas deja tirer un Bang ET que j'ai pas l'arme Volcanic
        //si j'ai la range
        //si le joueur en face n'as pas une carte Missed (et la planque?)
        //donc renvoi boolean
    }

    /*public boolean canPlayFromHand(Player player) {
        if (player.getGame().getCurrentPlayer()==player){ //si c'est le tour du joueur passé en parametre
           if (player.getHand().contains(this)){ //si le joueur en parametre a la carte en main
               return true;
           }
           return false;
        }
        return false;
    }*/

}
