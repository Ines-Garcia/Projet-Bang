package fr.umontpellier.iut.bang.cards;

import fr.umontpellier.iut.bang.Game;
import fr.umontpellier.iut.bang.Player;

import java.util.ArrayList;
import java.util.List;

public class Bang extends OrangeCard {

    public Bang(int value, CardSuit suit) {
        super("Bang!", value, suit);
    }

    @Override
    public void playedBy(Player player) {
        super.playedBy(player);
        /*if (player.getInPlay().contains(player.getCardInPlay("Scope"))){ //si le joueur a un scope
            List<Player> PlayerAPorte = player.getPlayersInRange(player.getWeaponRange()+1); //recup les joueurs a porte +1
        }*/
        if(!player.getInPlay().contains(player.getCardInPlay("Volcanic"))) {//si j'ai pas de volcanic
            List<Player> PlayerAPorte = player.getPlayersInRange(player.getWeaponRange()); //recup les joueurs a porte
            Player playerCible = player.choosePlayer("Séléctionne ta cible", PlayerAPorte, false); //choisis la cible
            if (playerCible.getHand().contains(playerCible.getCardInHand("Missed!"))) { //si la cible a un missed en main
                List<String> choice = new ArrayList<>();
                choice.add("Missed!");
                choice.add("");
                if (playerCible.choose("Voulez vous jouer un Missed", choice, true, true).equals("")) { //demande au joueur cible si il veut jouer sa carte miss
                    playerCible.decrementHealth(1, player); // si il ne veut pas utiliser un missed
                } else { //si il utilise un missed
                    playerCible.discardFromHand(playerCible.getCardInHand("Missed!"));
                }
            } else if (playerCible.getInPlay().contains(playerCible.getCardInPlay("Barrel"))) { //si la cible a un barrel sur le terrain
                Card degainer = playerCible.randomDraw(); //dégaine une carte
                if (degainer.getSuit() != CardSuit.HEART) { //si la carte degainer n'est pas un coeur
                    playerCible.decrementHealth(1, player); //met a jours les pv
                }
                playerCible.removeFromInPlay(playerCible.getCardInPlay("Barrel"));
            } else { //si la cible n'as ni de barrel ni de missed
                playerCible.decrementHealth(1, player); //met a jours les pv
            }
        }
        else if(player.getInPlay().contains(player.getCardInPlay("Volcanic"))){//si j'ai un volcanic
            List<String> choiceVolcanic = new ArrayList<>(); //choix de Bang! ou non
            choiceVolcanic.add("Bang!");//
            choiceVolcanic.add("");
            while(player.getHand().contains(player.getCardInHand("Bang!"))){ //tant que j'ai encore des bang! en main
                String choixFait = player.choose("Voulez vous jouer un Bang", choiceVolcanic, true, true);//je demande si le joueur veux Bang ou passer son tour
                if(choixFait.equals("Bang!")){
                    List<Player> PlayerInRange = player.getPlayersInRange(player.getWeaponRange()); //recup les joueurs a porte
                    Player playerCibleDuVolcanic = player.choosePlayer("Séléctionne ta cible", PlayerInRange, false); //choisis la cible
                    if (playerCibleDuVolcanic.getHand().contains(playerCibleDuVolcanic.getCardInHand("Missed!"))){ //si la cible a un missed en main
                        List<String> choice = new ArrayList<>();
                        choice.add("Missed!");
                        choice.add("");
                        if (playerCibleDuVolcanic.choose("Voulez vous jouer un Missed", choice, true, true).equals("")) { //demande au joueur cible si il veut jouer sa carte miss
                            playerCibleDuVolcanic.decrementHealth(1, player); // si il ne veut pas utiliser un missed
                        }else { //si il utilise un missed
                            playerCibleDuVolcanic.discardFromHand(playerCibleDuVolcanic.getCardInHand("Missed!"));
                        }
                    }else if(playerCibleDuVolcanic.getInPlay().contains(playerCibleDuVolcanic.getCardInPlay("Barrel"))){ //si la cible a un barrel sur le terrain
                        Card degainer = playerCibleDuVolcanic.randomDraw(); //dégaine une carte
                        if (degainer.getSuit()!=CardSuit.HEART){ //si la carte degainer n'est pas un coeur
                            playerCibleDuVolcanic.decrementHealth(1,player); //met a jours les pv
                        }
                        playerCibleDuVolcanic.removeFromInPlay(playerCibleDuVolcanic.getCardInPlay("Barrel"));
                    }else { //si la cible n'as ni de barrel ni de missed
                        playerCibleDuVolcanic.decrementHealth(1,player); //met a jours les pv
                    }
                }
            }
        }
           player.setBangDejaJoue(true); //Sert au Volcanic et a Willy The Kid
    }


    public boolean canPlayFromHand(Player player) {
        if (player.getGame().getCurrentPlayer()==player){ //si c'est le tour du joueur passé en parametre
           if (player.getHand().contains(this)){ //si le joueur en parametre a la carte en main //pas besoin car playFromHand test deja si carte presente dans la main
               if (player.getInPlay().contains(player.getCardInPlay("Volcanic"))){
                   return true;
               }
               if (player.isBangDejaJoue()){
                   return false;
               }
               return true;
           }
           return false;
        }
        return false;
    }
}
