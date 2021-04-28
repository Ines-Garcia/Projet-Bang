package fr.umontpellier.iut.bang.cards;

import fr.umontpellier.iut.bang.Player;

import java.util.ArrayList;
import java.util.List;

public class Duel extends OrangeCard {

    public Duel(int value, CardSuit suit) {
        super("Duel", value, suit);
    }

    public void playedBy(Player player) {
        super.playedBy(player);
        List<Player> joueurs = player.getOtherPlayers();

        joueurs.remove(player);

        Player playerCible = player.choosePlayer("Séléctionne ta cible", joueurs, false); //choisis la cible

        List<String> choice = new ArrayList<>();
        choice.add("Bang!");
        choice.add("");

        if (playerCible.getHand().contains(playerCible.getCardInHand("Bang!"))) { //si la cible a un bang
            while (true){
                if (playerCible.getHand().contains(playerCible.getCardInHand("Bang!")) ) {
                    if (playerCible.choose("Voulez vous utiliser un Bang ? ", choice, false, true).equals("")) { //demande au joueur d'utiliser un bang
                        playerCible.decrementHealth(1, player); //il ne veut pas
                        break;
                    } else {
                        playerCible.discardFromHand(playerCible.getCardInHand("Bang!")); //enleve le bang de la main du joueur
                    }
                }else {
                        playerCible.decrementHealth(1, player);
                        break;
                }
                if (player.getHand().contains(player.getCardInHand("Bang!"))){
                    if (player.choose("Voulez vous utiliser un Bang ? ", choice, false, true).equals("")) { //demande utiliser un bang
                        player.decrementHealth(1, playerCible); //il ne veut pas
                        break;
                    }else {
                        player.discardFromHand(player.getCardInHand("Bang!")); //enleve le bang de la main du joueur
                    }
                }else {
                    player.decrementHealth(1, player);
                    break;
                }
            }
        }else {
            playerCible.decrementHealth(1, player);
        }

    }

}
