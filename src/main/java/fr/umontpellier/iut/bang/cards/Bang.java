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

        if (player.getInPlay().contains(player.getCardInPlay("Volcanic"))){ //si le joueur a une volcanic
            List<String> choiceVolcanic = new ArrayList<>();
            choiceVolcanic.add("Bang!");//
            choiceVolcanic.add("");
            while (player.getHand().contains(player.getCardInHand("Bang!"))) { //tant qu'il a des bang en main
                List<Player> PlayerAPorte = player.getPlayersInRange(player.getWeaponRange()); //recup les joueurs a porte
                Player playerCible = player.choosePlayer("Séléctionne ta cible", PlayerAPorte, false); //choisis la cible
                String choixFaits = playerCible.choose("Voulez vous jouer un Bang", choiceVolcanic, true, true);
                if (choixFaits.equals("Bang!")) {
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
                    player.setBangDejaJoue(true); //Sert au Volcanic et a Willy The Kid
                }
                else { //si le choix fait = "", le joueur ne tire pas, il ne se passe rien
                    break;
                }
            }
        }else {
            List<Player> PlayerAPorte = player.getPlayersInRange(player.getWeaponRange()); //recup les joueurs a porte
            Player playerCible = player.choosePlayer("Séléctionne ta cible", PlayerAPorte, false); //choisis la cible
            if (playerCible.getHand().contains(playerCible.getCardInHand("Missed!"))){ //si la cible a un missed en main
                List<String> choice = new ArrayList<>();
                choice.add("Missed!");
                choice.add("");
                if (playerCible.choose("Voulez vous jouer un Missed", choice, true, true).equals("")) { //demande au joueur cible si il veut jouer sa carte miss
                    playerCible.decrementHealth(1, player); // si il ne veut pas utiliser un missed
                }else { //si il utilise un missed
                    playerCible.discardFromHand(playerCible.getCardInHand("Missed!"));
                }
            }else if(playerCible.getInPlay().contains(playerCible.getCardInPlay("Barrel"))){ //si la cible a un barrel sur le terrain
                Card degainer = playerCible.randomDraw(); //dégaine une carte
                if (degainer.getSuit()!=CardSuit.HEART){ //si la carte degainer n'est pas un coeur
                    playerCible.decrementHealth(1,player); //met a jours les pv
                }
                playerCible.removeFromInPlay(playerCible.getCardInPlay("Barrel"));
            }else { //si la cible n'as ni de barrel ni de missed
                playerCible.decrementHealth(1,player); //met a jours les pv
            }
            player.setBangDejaJoue(true); //Sert au Volcanic et a Willy The Kid
        }
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
