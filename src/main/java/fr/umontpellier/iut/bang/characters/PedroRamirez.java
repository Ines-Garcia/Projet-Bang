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

        List<String> topOfDiscardPile = new ArrayList<>();
        topOfDiscardPile.add(game.getTopOfDiscardPile().getName());

        String choix = player.choose("Voulez-vous choisir la première carte de la pile défausse", topOfDiscardPile,true,  true);

        if(choix.equals("")){
            player.drawCard();
        }
        else{
            player.getHand().add(game.getTopOfDiscardPile());
        }
            player.drawToHand();
    }
}
