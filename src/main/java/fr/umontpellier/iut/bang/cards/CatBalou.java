package fr.umontpellier.iut.bang.cards;

import fr.umontpellier.iut.bang.Player;

import java.util.ArrayList;
import java.util.List;

public class CatBalou extends OrangeCard {
//coup de foudre
    public CatBalou(int value, CardSuit suit) {
        super("Cat Balou", value, suit);
    }

    public void playedBy(Player player) {
        super.playedBy(player);

        /*List<Player> playerRestant = player.getOtherPlayers(); //V1
        List<String> choice = new ArrayList<>();
        choice.add("");


        Player playerCible = player.choosePlayer("Sélectionne ta cible", playerRestant, false); //choisis la cible
        List<BlueCard> carteEnJeuCible = playerCible.getInPlay();
        for (BlueCard c : carteEnJeuCible){
            choice.add(c.getName());
        }
        String choix = player.choose("Séléctionnez une carte, entrez \"\"  pour piocher dans la main:",choice,true,false);
        if (choix.equals("")){
           playerCible.discard(playerCible.removeRandomCardFromHand());
        }else{
            BlueCard carteAenlever = playerCible.getCardInPlay(choix);
            playerCible.removeFromInPlay(playerCible.getCardInPlay(choix));
            playerCible.discard(carteAenlever);
        }*/

        List<Player> playerRestant = player.getOtherPlayerQuiOntDesCarte(); //V2

        Player playerCible = player.choosePlayer("Sélectionne ta cible", playerRestant, false); //choisis la cible
        BlueCard chosie = player.chooseBlueCard("Séléctionnez une carte en jeu sinon appuyez sur entrer:",playerCible.getInPlay(),true,true);

        if (chosie==null){ //si carte en main
            playerCible.discard(playerCible.removeRandomCardFromHand());
        }else { //si carte en jeu
            playerCible.removeFromInPlay(playerCible.getCardInPlay(chosie.getName()));
            playerCible.discard(chosie);
        }
    }

    public boolean canPlayFromHand(Player player) {
        List<Player> playerRestant = player.getOtherPlayerQuiOntDesCarte();
        if(playerRestant.isEmpty()){
            return false;
        }
        return true;
    }
}
