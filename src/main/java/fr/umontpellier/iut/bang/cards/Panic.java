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

        Player playerCible = player.choosePlayer("Séléctionne ta cible", joueurs, false); //choisis la cible
        BlueCard chosie = player.chooseBlueCard("Séléctionnez une carte en jeu sinon appuyez sur entrer:",playerCible.getInPlay(),true,true);

        if (chosie==null) {  //si il prend une carte en main
            Card carteHasard = playerCible.removeRandomCardFromHand(); //enleve une carte au hasard de la main de la cible et la stock
            player.addToHand(carteHasard); //l'ajoute à la main du joueur courant
        }else { //si il prend une carte en jeu
            playerCible.removeFromInPlay(playerCible.getCardInPlay(chosie.getName())); //enleve la carte du terrain
            player.addToHand(chosie); //ajoute la carte dans la main
        }

    }

    public boolean canPlayFromHand(Player player) {
        List<Player> joueurs = player.getPlayersInRange(1);
        if (joueurs.isEmpty()){
            return false;
        }
        return true;
    }

}
