package fr.umontpellier.iut.bang.characters;

import fr.umontpellier.iut.bang.Player;
import fr.umontpellier.iut.bang.cards.Card;

import java.util.ArrayList;
import java.util.Arrays;

public class KitCarlson extends BangCharacter {

    public KitCarlson() {
        super("Kit Carlson", 4);
    }

    @Override
    public void onStartTurn(Player player){
        /*
        ArrayList<Card> cartesPiochees = new ArrayList<>();
        for (int i = 0 ; i < 3 ; i++){
            cartesPiochees.add(player.drawCard());
        }
        Card carte1 = player.chooseCard("Quelle est la première carte que vous voulez prendre ?",cartesPiochees,true,true);
        cartesPiochees.remove(carte1);
        player.addToHand(carte1);

        Card carte2 = player.chooseCard("Quelle est la deuxième carte que vous voulez prendre ?",cartesPiochees,true,true);
        cartesPiochees.remove(carte2);
        player.addToHand(carte2);

        Card derniereCarte = cartesPiochees.get(0);

        player.getGame().getDrawPile().addFirst(derniereCarte); // quel add choisir ?

         */
    }
}
