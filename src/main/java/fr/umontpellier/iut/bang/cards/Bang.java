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

        List<Player> PlayerAPorte = player.getPlayersInRange(player.getWeaponRange()); //recup les joueurs a porte
        //PlayerAPorte.remove(player);
        Player playerCible = player.choosePlayer("Séléctionne ta cible", PlayerAPorte, false); //choisis la cible
        playerCible.reactToBang(player);
        player.setBangDejaJoue(true); //Sert au Volcanic et a Willy The Kid
    }



    public boolean canPlayFromHand(Player player) {
        List<Player> joueurs = player.getPlayersInRange(player.getWeaponRange());
        //joueurs.remove(player);
        if (!joueurs.isEmpty()) {
            if (player.getBangCharacter().getName().equals("Willy the Kid") || player.getWeaponName().equals("Volcanic")) { // si c'est willy the kid ou si il a une volcanic alors il peut jouer autant de bang qu'il veut
                return true;
            } else { //si il n'a pas de volcanic et que ce n'est pas Willy the Kid
                if (player.isBangDejaJoue()) { //si il a deja jouer un bang cf ligne 49
                    return false;
                } else { //si il n'a pas jouer de bang ce tour
                    return true;
                }
            }
        }
        return false;
    }

}
