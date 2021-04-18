package fr.umontpellier.iut.bang.cards;

import fr.umontpellier.iut.bang.Game;
import fr.umontpellier.iut.bang.Player;

import java.util.ArrayList;
import java.util.List;

public class Bang extends OrangeCard {

    public Bang(int value, CardSuit suit) {
        super("Bang!", value, suit);
    }

    /*@Override
    public void playedBy(Player player) {
        super.playedBy(player);
        List<Player> PlayerAPorte = player.getPlayersInRange(player.getWeaponRange()); //recup les joueurs a porte
        Player playerCible = player.choosePlayer("Séléctionne ta cible", PlayerAPorte, false); //choisis la cible
        if (playerCible.getHand().contains("Missed")){ //si la cible a un missed en main
            //demande au joueur cible si il veut jouer sa carte miss
            if (){ //si il veut jouer sa carte missed
                //playerCible.removeFromHand(Missed);
            }else { //si l ne veut pas jouer sa carte missed
                playerCible.decrementHealth(1,player); //met a jours les pv
            }
        }else if(playerCible.getInPlay().contains("barrel")){ //si la cible a un barrel sur le terrain
            Card degainer = playerCible.randomDraw(); //dégaine une carte
            if (degainer.getSuit()!=CardSuit.HEART){ //si la carte degainer n'est pas un coeur
                playerCible.decrementHealth(1,player); //met a jours les pv
            }
            playerCible.removeFromInPlay("Barrel");
        }else { //si la cible n'as ni de barrel ni de missed
            playerCible.decrementHealth(1,player); //met a jours les pv
        }
        player.discardFromHand(this); //retire la carte de la main du joueur


        //je choisis le joueur
        //si le joueur n'est pas deja mort
        //si j'ai pas deja tirer un Bang ET que j'ai pas l'arme Volcanic
        //si j'ai la range
        //si le joueur en face n'as pas une carte Missed (et la planque?)
        //donc renvoi boolean
    }*/

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
