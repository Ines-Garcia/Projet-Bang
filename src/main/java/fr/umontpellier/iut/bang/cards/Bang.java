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

        List<Player> PlayerAPorte = player.getPlayersInRange(player.getWeaponRange()); //recup les joueurs a porte
        Player playerCible = player.choosePlayer("Séléctionne ta cible", PlayerAPorte, false); //choisis la cible
        boolean esquiveJourdonnais = false; //initialisation du boolen d'esquive a false

        if (playerCible.getBangCharacter().getName().equals("Jourdonnais")) { //si la cible est "Jourdonnais"
            Card degainerJourdonnais = playerCible.randomDraw(); //dégaine une carte
            if (degainerJourdonnais.getSuit() == CardSuit.HEART) { //si la carte degaine est un coeur
                esquiveJourdonnais = true; //il esquive
            }
        }
        if (!esquiveJourdonnais) { //si il n'esquive pas
            if (player.getBangCharacter().getName().equals("Slab the Killer")) {
                int compteurDeMissed = 0;
                for (Card c : playerCible.getHand()) {
                    if (c.getName().equals("Missed!")||c.getName().equals("Barrel")) {
                        compteurDeMissed++;
                    }
                }
                if (compteurDeMissed >= 2) { //si la cible a 2 missed en main ou plus
                    int nbDeMissedUtilise = 0;
                    boolean missedNonUtilise = false;

                    List<String> choice = new ArrayList<>();
                    choice.add("Missed!");
                    choice.add("Barrel");
                    choice.add("");

                    while (nbDeMissedUtilise != 2 && !missedNonUtilise) {
                        String choix = playerCible.choose("Voulez vous jouer un Missed", choice, true, true);
                        if (choix.equals("")) { //si il veux rien utilisé
                            playerCible.decrementHealth(1, player);
                            missedNonUtilise = true;
                        } else { //si il utilise un missed OU un Barrel
                            if (choix.equals("Missed!")) {
                                playerCible.discardFromHand(playerCible.getCardInHand("Missed!"));
                                nbDeMissedUtilise++;
                            }
                            if(choix.equals("Barrel")) {
                                Card degainer = playerCible.randomDraw(); //dégaine une carte
                                if (degainer.getSuit() != CardSuit.HEART) { //si la carte degainer n'est pas un coeur
                                    playerCible.decrementHealth(1, player); //met a jours les pv
                                    missedNonUtilise=true;
                                }
                                playerCible.discardFromHand(playerCible.getCardInHand("Barrel"));
                                nbDeMissedUtilise++;
                            }
                        }
                    }
                }
                else {
                    playerCible.decrementHealth(1, player); //met a jours les pv
                }

            } else if (!player.getBangCharacter().getName().equals("Slab the Killer")) {
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
        }
        player.setBangDejaJoue(true); //Sert au Volcanic et a Willy The Kid
    }


    public boolean canPlayFromHand(Player player) {
        if (player.getBangCharacter().getName().equals("Willy the Kid") || player.getWeaponName().equals("Volcanic")){ // si c'est willy the kid ou si il a une volcanic alors il peut jouer autant de bang qu'il veut
            return true;
        }
        else { //si il n'a pas de volcanic et que ce n'est pas Willy the Kid
            if (player.isBangDejaJoue()){ //si il a deja jouer un bang cf ligne 49
                return false;
            }else { //si il n'a pas jouer de bang ce tour
                return true;
            }
        }
    }

}
