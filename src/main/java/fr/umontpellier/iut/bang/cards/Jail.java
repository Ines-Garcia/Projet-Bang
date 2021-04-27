package fr.umontpellier.iut.bang.cards;

import fr.umontpellier.iut.bang.Player;
import fr.umontpellier.iut.bang.Role;

import java.util.ArrayList;
import java.util.List;

public class Jail extends BlueCard {
    public Jail(int value, CardSuit suit) {
        super("Jail", value, suit);
    }

    public void playedBy(Player player) {
        super.playedBy(player);
        player.removeFromInPlay(this);
        List<Player> playerRestant = player.getOtherPlayers();

        for (int i=0;i<playerRestant.size();i++){
            if (playerRestant.get(i).getRole()== Role.SHERIFF){
                playerRestant.remove(playerRestant.get(i));
            }
        }

        Player playerCible = player.choosePlayer("Séléctionne ta cible", playerRestant, false); //choisis la cible
        playerCible.addToInPlay(this);
    }
}
