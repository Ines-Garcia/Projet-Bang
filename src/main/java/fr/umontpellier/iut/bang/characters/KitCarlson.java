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

        Card premiereCarte=player.drawCard();
        Card deuxiemeCarte=player.drawCard();
        Card troisiemeCarte=player.drawCard();

        ArrayList<Card> choiceCard1 = new ArrayList<>();
        choiceCard1.add(premiereCarte);
        choiceCard1.add(deuxiemeCarte);
        choiceCard1.add(troisiemeCarte);

        Card carteChoisie1 = player.chooseCard("Quelle est la carte que vous ne voulez pas prendre ?",choiceCard1,true,true);
        choiceCard1.remove(carteChoisie1);
        player.getGame().getDrawPile().addFirst(carteChoisie1);

        for (Card c : choiceCard1){
            player.addToHand(c);
        }
    }
}
