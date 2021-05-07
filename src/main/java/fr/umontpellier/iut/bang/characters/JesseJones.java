package fr.umontpellier.iut.bang.characters;

import fr.umontpellier.iut.bang.Player;
import fr.umontpellier.iut.bang.cards.Card;

import java.util.*;

import java.util.ArrayList;

public class JesseJones extends BangCharacter {

    public JesseJones() {
        super("Jesse Jones", 4);
    }

    @Override
    public void onStartTurn(Player player) {
        List<Player> joueurs = player.getOtherPlayers();

        for (int i=0; i< joueurs.size();i++){
            if (joueurs.get(i).getHand().isEmpty()) {
                joueurs.remove(joueurs.get(i));
            }
        }

        Player joueurChoisi = player.choosePlayer("Choisisez le joueur a qui vous voulez prendre une carte, appuyez sur enter sinon",joueurs,true);
        if (joueurChoisi==null){ //si il ne pioche pas dans la main d'un joueur
            player.drawToHand();
        }else { //si il pioche dans la main d'un joueur
            player.addToHand(joueurChoisi.removeRandomCardFromHand());
        }
        player.drawToHand();
    }
}
