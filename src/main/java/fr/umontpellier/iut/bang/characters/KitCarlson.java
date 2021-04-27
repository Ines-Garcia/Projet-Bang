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

        Card carteChoisie1 = player.chooseCard("Quelle est la première carte que vous voulez prendre ?",choiceCard1,true,true);
        if (carteChoisie1!=null){ //si il choisis une carte
            player.addToHand(carteChoisie1);
            choiceCard1.remove(carteChoisie1);
            Card carteChoisie2 = player.chooseCard("Quelle est la deuxieme carte que vous voulez prendre ?",choiceCard1,true,true);
            if (carteChoisie2!=null){ //si il choisis une carte
                player.addToHand(carteChoisie2);
                choiceCard1.remove(carteChoisie2);
            }else { //il ne choisis qu'une carte, il remet les deux autres dans la draw pile
                player.getGame().getDrawPile().addFirst(deuxiemeCarte);
                player.getGame().getDrawPile().addFirst(troisiemeCarte);
            }
        }else { //il ne choisis aucune carte, il remet tout dans la draw pile
            player.getGame().getDrawPile().addFirst(premiereCarte);
            player.getGame().getDrawPile().addFirst(deuxiemeCarte);
            player.getGame().getDrawPile().addFirst(troisiemeCarte);
        }
    }
}
