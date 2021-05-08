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
        player.removeFromInPlay(this);   // pourquoi on enlève la Prison du InPlay de celui qui la joue ?
        List<Player> playerRestant = new ArrayList<>();
        playerRestant.addAll(player.getOtherPlayers());

        for (int i=0;i<playerRestant.size();i++){
            if (playerRestant.get(i).getRole()== Role.SHERIFF){
                playerRestant.remove(playerRestant.get(i));
            }
        }

        Player playerCible = player.choosePlayer("Séléctionne ta cible", playerRestant, false); //choisis la cible
        playerCible.addToInPlay(this);
    }

    public boolean canPlayFromHand(Player player) {
        if (super.canPlayFromHand(player)){
            if (player.getOtherPlayers().size()==1 && player.getOtherPlayers().get(0).getRole() == Role.SHERIFF){
                return false;
            }
            return true;
        }
        return false;
    }

}
