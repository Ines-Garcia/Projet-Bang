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
            List<Player> PlayerRestant = player.getOtherPlayers();
            Player playerCible = player.choosePlayer("Séléctionne ta cible", PlayerRestant, false); //choisis la cible
            Card cardChoisis = player.chooseCard("Choisis une carte du joueur cible", playerCible.getAllCards(), true, true); //recup les cartes en main du joueur cible
            playerCible.discardFromHand(cardChoisis); //retire la carte de la main du joueur
            //if(cardChoisis==null){
            //}
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
