package fr.umontpellier.iut.bang.cards;

import fr.umontpellier.iut.bang.Player;

import java.util.List;

public class CatBalou extends OrangeCard {
//coup de foudre
    public CatBalou(int value, CardSuit suit) {
        super("Cat Balou", value, suit);
    }

    public void playedBy(Player player) {
        super.playedBy(player);
        List<Player> playerRestant = player.getOtherPlayers();
        Player playerCible = player.choosePlayer("Séléctionne ta cible", playerRestant, false); //choisis la cible
        Card cardChoisis = player.chooseCard("Choisis une carte du joueur cible", playerCible.getAllCards(), true, true); //recup les cartes en main du joueur cible
        playerCible.discardFromHand(cardChoisis); //defausse la carte de la main du joueur
        if (playerCible.getInPlay().contains(playerCible.getCardInPlay(cardChoisis.getName()))){ //si le joueur cible a la carte choisie sur le terrain
            playerCible.removeFromInPlay(playerCible.getCardInPlay(cardChoisis.getName())); //defausse la carte du plateau du joueur
        }
        player.discardFromHand(this); //defausse la carte du joueur courant
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
