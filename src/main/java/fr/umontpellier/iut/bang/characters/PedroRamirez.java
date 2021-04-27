package fr.umontpellier.iut.bang.characters;

import fr.umontpellier.iut.bang.Game;
import fr.umontpellier.iut.bang.Player;
import fr.umontpellier.iut.bang.cards.Card;
import fr.umontpellier.iut.bang.cards.CardSuit;

import java.util.ArrayList;
import java.util.List;

public class PedroRamirez extends BangCharacter {

    public PedroRamirez() {
        super("Pedro Ramirez", 4);
    }

    public void onStartTurn(Player player) {
        Game game = player.getGame();

        if (game.getDiscardPile().isEmpty()){ //si la defausse est vide (ex début de partie)
            player.drawToHand();
            player.drawToHand();
        }else {
            List<Card> topOfDiscardPile = new ArrayList<>();
            topOfDiscardPile.add(game.getTopOfDiscardPile());

            Card choix = player.chooseCard("Voulez-vous choisir la première carte de la pile défausse", topOfDiscardPile,true,  true);

            if(choix==game.getTopOfDiscardPile()){
                player.getHand().add(choix);
                player.removeFromDiscard(choix);
            }
            else{
                player.drawToHand();
            }
            player.drawToHand();
        }

    }
}
