package fr.umontpellier.iut.bang.cards;

import fr.umontpellier.iut.bang.Player;

public class Barrel extends BlueCard {
//planque
    public Barrel(int value, CardSuit suit) {
        super("Barrel", value, suit);
    }

    /*public void playedBy(Player player){
        super.playedBy(player);
        player.addToInPlay(this); //met sur le plateau
        Card degainer = player.randomDraw(); //dégaine une carte
        if (degainer.getSuit()==CardSuit.HEART){ //si la carte degainer est un coeur
            //appel carte Missed
            Card missed = new Missed(1,CardSuit.HEART); //creation carte missed
            player.addToHand(missed); //ajout dans la main pour la jouer
            player.playFromHand(missed); //joue la carte (ne la met pas sur le plateau)
            player.removeFromHand(missed); //remove et pas discard car sinon met la carte dans la defausse
            //quel condition pour enlever une carte bleu de devant le joueur
            /*
            while(player.getInPlay.contain(this){
                if(je prend prend des degats){
                    appel carte missed
                    enleve barrel du plateau
                }
            }

        }
        // carte degainer n'est pas un coeur donc rien ne se passe
    }*/

    /*public void playedBy(Player player){
        super.playedBy(player);
        player.addToInPlay(this); //met sur le plateau
        Card degainer = player.randomDraw(); //dégaine une carte
        while (player.getInPlay().contains(this)){
            if (degainer.getSuit()==CardSuit.HEART){ //si la carte degainer est un coeur
                Card missed = new Missed(1,CardSuit.HEART); //creation carte missed
                player.addToHand(missed); //ajout dans la main pour la jouer
                player.playFromHand(missed); //joue la carte (ne la met pas sur le plateau)
                player.removeFromHand(missed); //remove et pas discard car sinon met la carte dans la defausse
            }
        }
        player.removeFromInPlay(this);
        // carte degainer n'est pas un coeur donc rien ne se passe
    }*/

    public boolean canPlayFromHand(Player player) {
        if (player.getGame().getCurrentPlayer()==player){ //si c'est le tour du joueur passé en parametre
            if (player.getHand().contains(this)){ //si le joueur en parametre a la carte en main //pas besoin car playFromHand test deja si carte presente dans la main
                return true;
            }
            return false;
        }
        return false;
    }

}
