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
        /*player.drawToHand();
        List<String> choix = new ArrayList<>();
        choix.add("p2");
        choix.add("");
        if (player.choose("voulez vous tirer dans la main d'un joueur", choix, false, false).equals("")) {
            player.drawToHand();
        } else {
            List<Player> joueurs = player.getOtherPlayers();
            Player cible = player.choosePlayer("A qui voulez vous prendre une carte", joueurs, false);
            Card h = cible.removeRandomCardFromHand();  //enlève la carte de la main de la cible
            player.addToHand(h); // l'ajoute à la main du player
        }*/
    }
}
