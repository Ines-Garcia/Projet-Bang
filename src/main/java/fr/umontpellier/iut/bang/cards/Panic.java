package fr.umontpellier.iut.bang.cards;

import fr.umontpellier.iut.bang.Player;

import java.util.ArrayList;
import java.util.List;

public class Panic extends OrangeCard {

    public Panic(int value, CardSuit suit) {
        super("Panic!", value, suit);
    }

    public void playedBy(Player player) {
        super.playedBy(player);
        List<Player> joueurs = player.getPlayersInRange(1);
        List<String> choice = new ArrayList<>();
        choice.add("");

        Player playerCible = player.choosePlayer("Séléctionne ta cible", joueurs, false); //choisis la cible


        if (player.choose("Voulez vous prendre une carte en jeu", choice, false, false).equals("")) {  //si il prend une carte en main
            Card carteHasard = playerCible.removeRandomCardFromHand(); //enleve une carte au hasard de la main de la cible et la stock
            player.addToHand(carteHasard); //l'ajoute à la main du joueur courant
        }else { //si il prend une carte en jeu
            Card bleuCardPicked = playerCible.chooseCard("Séléctionne une carte en jeu carte", playerCible.getAllCards(), false, false);
            playerCible.removeFromInPlay(playerCible.getCardInPlay(bleuCardPicked.getName())); //enleve la carte du terrain
            player.addToHand(bleuCardPicked); //ajoute la carte dans la main
            /* BlueCard bleuCardPicked = playerCible.chooseBlueCard("Séléctionne une carte en jeu carte", playerCible.getInPlay(), false, false);
            playerCible.removeFromInPlay(bleuCardPicked); //enleve la carte du terrain
            player.addToHand(bleuCardPicked); //ajoute la carte dans la main */
        }

    }

}
