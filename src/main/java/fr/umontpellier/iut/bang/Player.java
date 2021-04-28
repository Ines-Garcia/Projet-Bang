package fr.umontpellier.iut.bang;

import fr.umontpellier.iut.bang.cards.BlueCard;
import fr.umontpellier.iut.bang.cards.*;
import fr.umontpellier.iut.bang.cards.WeaponCard;
import fr.umontpellier.iut.bang.characters.BangCharacter;
import fr.umontpellier.iut.bang.Game;
import java.util.*;
import java.util.spi.AbstractResourceBundleProvider;

public class Player {
    /**
     * Nom du joueur
     */
    private String name;
    /**
     * Points de vie courants
     */
    private int healthPoints;
    /**
     * Rôle dans la partie (Shériff, adjoint, hors-la-loi ou renégat)
     */
    private Role role;
    /**
     * Personnage
     */
    private BangCharacter bangCharacter;
    /**
     * Partie à laquelle le joueur appartient
     */
    private Game game;
    /**
     * Cartes en main
     */
    private List<Card> hand;
    /**
     * Cartes bleues actuellement posées devant le joueur (hors arme)
     */
    private List<BlueCard> inPlay;
    /**
     * Arme posée devant le joueur
     */
    private WeaponCard weapon;

    private boolean bangDejaJoue = false;

    public Player(String name, BangCharacter bangCharacter, Role role) {
        this.name = name;
        this.role = role;
        this.bangCharacter = bangCharacter;
        healthPoints = getHealthPointsMax();
        inPlay = new ArrayList<>();
        hand = new ArrayList<>();
    }

    public boolean isBangDejaJoue() {
        return bangDejaJoue;
    }

    public void setBangDejaJoue(boolean bangDejaJoue) {
        this.bangDejaJoue = bangDejaJoue;
    }

    public String getName() {
        return name;
    }

    public int getHealthPoints() {
        return healthPoints;
    }

    public Role getRole() {
        return role;
    }

    public Game getGame() {
        return game;
    }

    public BangCharacter getBangCharacter() {
        return bangCharacter;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public List<Card> getHand() {
        return hand;
    }

    public List<BlueCard> getInPlay() {
        return inPlay;
    }

    public WeaponCard getWeapon() {
        return weapon;
    }

    public String getWeaponName(){
        if (getWeapon()==null){
            return ""; // en gros il a un colt 45 (arme de base)
        }else {
            return weapon.getName();
        }
    }

    public List<Card> getAllCards(){ //RETOURNE UNE LIST DE TOUTES LES CARTES DU JOUEUR
        List<Card> playerAllCards = new ArrayList<>(); //crée une liste de toutes les cartes
        playerAllCards.addAll(this.getInPlay()); //ajoute les cartes en jeu
        playerAllCards.addAll(this.getHand()); //ajoute les cartes en main
        return playerAllCards; //retourne la liste
    }



    /**
     * @return la portée de l'arme équipée (1 si aucune arme équipée)
     */
    public int getWeaponRange() {
        if (weapon == null) { //si il a pas d'arme il a un colt 45
            return 1;
        }
        return weapon.getRange();
    }

    /**
     * @return le nombre maximum de points de vie que le joueur peut avoir. Dépend des points de vie de son personnage
     * et de son rôle (le Shériff a un point de vie max de plus que les autres)
     */
    public int getHealthPointsMax() {
        int hp = bangCharacter.getHealthPoints();
        if (role == Role.SHERIFF)
            return hp + 1;
        return hp;
    }

    /**
     * @return la liste des autres joueurs encore en jeu, dans l'ordre de jeu (le premier est le joueur
     * immédiatement après le joueur courant)
     */
    public List<Player> getOtherPlayers() {
        List<Player> player = game.getPlayers();
        List<Player> playerordre = new ArrayList<>();
        if(player.indexOf(this)==0) {
            playerordre=player;
        }
        else {
            for (int i=player.indexOf(this);i< player.size();i++){
                playerordre.add(player.get(i));
            }
            for (int j=0;j<player.indexOf(this);j++){
                playerordre.add(player.get(j));
            }

        }
        return playerordre;
    }

    /**
     * @param range portée considérée
     * @return la liste des autres joueurs encore en jeu que le joueur courant voit à une distance inférieure ou égale
     * à {@code range}.
     */
    public List<Player> getPlayersInRange(int range) {
        ArrayList<Player> PlayersInRange = new ArrayList<>(); //ArrayList de retour
             for (int nb = 0; nb < getOtherPlayers().size() ; nb++) { //Parcours de la list de joueurs
                 if (range >= distanceTo(getOtherPlayers().get(nb))){ //si la range est >= à la distance entre le joueur courant et le joueur a l'indice nb.
                     PlayersInRange.add(getOtherPlayers().get(nb)); //le joueur est donc a porté je l'ajoute donc.
                 }
             }
        return PlayersInRange;
    }

    /**
     * @return true si le joueur est mort
     */
    public boolean isDead() {
        return healthPoints <= 0;
    }

    /**
     * Change l'arme du joueur
     * Si le joueur a déjà une arme, celle-ci est défaussée
     * <p>
     * Remarque : pour retirer l'arme du joueur, il faut appeler {@code p.setWeapon(null);}
     *
     * @param weapon nouvelle arme à équiper
     */
    public void setWeapon(WeaponCard weapon) {
        if (this.getWeapon()!=null){ // si j'ai une arme
            this.discard(this.weapon); //l'arme courante en main est jeté dans la fausse
            this.removeFromInPlay(this.weapon); //enleve l'arme du inplay
            this.weapon=weapon; //set la nouvelle arme en tant que courante
            addToInPlay(weapon);
        }else { //si j'ai pas d'arme
            this.weapon=weapon;
            addToInPlay(weapon);
        }
    }

    /**
     * @param cardName nom de la carte à renvoyer
     * @return la première carte parmi les cartes en jeu du joueur ayant le nom passé en argument, ou {@code null}
     * si aucune carte ne correspond
     */
    public BlueCard getCardInPlay(String cardName) {
        for (BlueCard c : inPlay) {
            if (c.getName().equals(cardName)) {
                return c;
            }
        }
        return null;
    }

    public Card getCardInHand(String cardName) {
        for (Card c : hand) {
            if (c.getName().equals(cardName)) {
                return c;
            }
        }
        return null;
    }

    /**
     * Augmente le nombre de points de vie du joueur de la valeur indiquée, sans toutefois dépasser la valeur maximale
     *
     * @param n nombre de points de vie à ajouter (doit être positif)
     */
    public void incrementHealth(int n) {
        healthPoints += n;
        if (healthPoints > getHealthPointsMax()) {
            healthPoints = getHealthPointsMax();
        }
    }

    /**
     * Décrémente le nombre de points de vie du joueur de la valeur indiquée. Si les points de vie du joueur passent à
     * 0 ou moins mais que le joueur a des Bières en main et qu'il peut les utiliser, elles sont automatiquement
     * utilisées pour le maintenir en vie.
     * <p>
     * Si les points de vie du joueur sont à 0 ou moins, le joueur est mort et retiré du jeu.
     *
     * @param n        nombre de points de vie à retirer (doit être positif)
     * @param attacker joueur qui est responsable des dégâts subis ou {@code null} si aucun joueur n'est directement
     *                 responsable (p.ex. Dynamite)
     */
    public void decrementHealth(int n, Player attacker) {
        if (this.getBangCharacter().getName().equals("Bart Cassidy")){
            this.drawToHand();
        }
        if (this.getBangCharacter().getName().equals("El Gringo")){
            this.addToHand(attacker.removeRandomCardFromHand());
        }
        if(this.getHealthPoints()-n>0){ //si vivant apres degats
            this.healthPoints-=n;
        }
        else if(this.getHealthPoints()-n<=0){ //si mort apres degats //modif inferieur ou EGAL a 0
            this.healthPoints-=n;
            while(this.getHand().contains(this.getCardInHand("Beer")) && isDead()) {
                incrementHealth(1);
                discardFromHand(this.getCardInHand("Beer"));
            }
            boolean mort = false;
            if(isDead()){
                mort = true;
            }
            if (mort) {
                healthPoints = 0;
                if (game.vultureSamLaEtVivant() && !this.getBangCharacter().getName().equals("Vulture Sam")) { //si Vulture Sam est vivant et qu'il est dans la partie ET que le joueur mort n'est pas Vulture Sam
                    for (Card carte : this.getHand()) { //pour toute les cartes de la main du joueur mort
                        this.getGame().getVultureSam().addToHand(carte); //je les ajoutes dans la main de Sam
                    }
                    for (Card carte2 : this.getInPlay()) { //pour toute les cartes en jeu du joueur mort
                        this.getGame().getVultureSam().addToHand(carte2); //je les ajoutes dans la main de Sam
                    }
                }
                game.removePlayer(this);
            }
        }
    }



    /**
     * @param player autre joueur
     * @return distance à laquelle le joueur courant voit le joueur passé en paramètre
     */
    public int distanceTo(Player player) {
        int distanceBase = game.getPlayerDistance(this, player);

        if(this.getInPlay().contains(this.getCardInPlay("Scope"))){ //si j'ai un scope
            distanceBase-=1; //je le vois 1 plus pres //POURQUOI AVEC 2 ça passe ? => voir getPlayerDistance
        }else if(player.getInPlay().contains(player.getCardInPlay("Mustang"))){ //si il a un mustang
            distanceBase+=1; //je le vois 1 plus loin
        }else if(player.getBangCharacter().getName().equals("Paul Regret")){ // Paul Regret voit 1 plus loin
            distanceBase+=1;
        }else if(this.getBangCharacter().getName().equals("Rose Doolan")){ //Rose Doolan voit 1 plus pres
            distanceBase-=1; //je le vois 1 plus loin
        }
        return distanceBase;
    }

    /**
     * Pioche une carte
     *
     * @return la carte qui a été piochée
     */
    public Card drawCard() {
        return game.drawCard();
    }

    /**
     * Défausse une carte
     *
     * @param card carte à défausser
     */
    public void discard(Card card) {
        game.addToDiscard(card);
    }

    /**
     * Ajoute une carte à la main du joueur
     * @param card carte à ajouter
     */
    public void addToHand(Card card) {
        hand.add(card);
    }

    /**
     * Pioche une carte, qui est mise dans la main du joueur
     *
     * @return la carte qui a été piochée
     */
    public Card drawToHand() {
        Card card = game.drawCard();
        hand.add(card);
        return card;
    }

    /**
     * Retire une carte de la main du joueur
     *
     * @param card carte à retirer
     * @return true si la carte a bien été retirée, false sinon (la carte n'était pas dans la main du joueur)
     */
    public boolean removeFromHand(Card card) {
        if (hand.contains(card)){ //si la main contient, je retire et retourne vrai
            hand.remove(card);
            return true;
        }
        return false;
    }

    public void removeFromDiscard(Card card){
        game.removeFromDiscrad(card);
    }


    /**
     * Défausse une carte de la main du joueur
     * <p>
     * La fonction ne fait rien si la carte n'est pas dans la main du joueur
     *
     * @param c carte à défausser
     */
    public void discardFromHand(Card c) {
        if (removeFromHand(c)) {
            game.addToDiscard(c);
        }
    }

    public void discardFromInPlay(BlueCard c) {
            game.addToDiscard(c);
    }

    /**
     * Retire une carte aléatoire de la main du joueur
     *
     * @return la carte qui a été retirée (ou null si le joueur n'a pas de carte en main)
     */
    public Card removeRandomCardFromHand() {
        if (hand.size() > 0) {
            Random random = new Random();
            Card card = hand.get(random.nextInt(hand.size()));
            removeFromHand(card);
            return card;
        }
        return null;
    }

    /**
     * Renvoie une carte retournée sur la pioche pour un tirage aléatoire (action "dégainer").
     * Cette action peut être modifiée par le personnage du joueur.
     *
     * @return la carte qui a été dégainée
     */
    public Card randomDraw() {
        if (this.getBangCharacter().getName().equals("Lucky Duke")){ //V1 Lucky Duke
           Card premiereCarte=this.drawCard();
           Card deuxiemeCarte=this.drawCard();

           ArrayList<String> choiceCard = new ArrayList<>();

            choiceCard.add(premiereCarte.getPokerString());

            choiceCard.add(deuxiemeCarte.getPokerString());

            String choisie = this.choose("Entrez la valeur de la carte que vous voulez:",choiceCard,true,false);

            discard(premiereCarte);
            discard(deuxiemeCarte);

            if (premiereCarte.getPokerString().equals(choisie)){
                return premiereCarte;
            }else {
                return deuxiemeCarte;
            }
        }
        return bangCharacter.randomDraw(this);
    }

    /**
     * Attend une entrée de la part du joueur (au clavier) et renvoie le choix du joueur.
     * Cette méthode lit l'entrée clavier jusqu'à ce qu'un choix valide (un élément de {@code choices}
     * ou éventuellement la chaîne vide si l'utilisateur est autorisé à passer) soit
     * entré par l'utilisateur. Lorsqu'un choix valide est obtenu, il est renvoyé par la fonction.
     * <p>
     * Si l'ensemble {@code choices} ne comporte qu'un seul élément et que {@code canPass} est faux,
     * l'unique choix valide est automatiquement renvoyé sans lire l'entrée de l'utilisateur.
     * <p>
     * Si l'ensemble des choix est vide, la chaîne vide ("") est automatiquement renvoyée par la méthode
     * (indépendamment de la valeur de {@code canPass}).
     * <p>
     * Exemple d'utilisation pour demander à un joueur de répondre à une question par "oui" ou "non" (en affichant
     * des boutons pour les deux options) :
     * <p>
     * {@code
     * List<String> choices = Arrays.asList("oui", "non");
     * String input = p.("Voulez vous faire ceci ?", choices, true, false);
     * }
     *
     * @param instruction message à afficher à l'écran pour indiquer au joueur
     *                    la nature du choix qui est attendu
     * @param choices     une liste de chaînes de caractères correspondant aux
     *                    choix valides attendus du joueur (la liste sera convertie en ensemble
     *                    par la fonction pour éliminer les doublons, ce qui permet de compter
     *                    correctement le nombre d'options disponibles)
     * @param showButtons indique s'il faut afficher des boutons pour permettre au joueur de choisir une des options.
     *                    Si c'est le cas, un bouton sera affiché pour chaque valeur dans `choices` (en mode console
     *                    les noms des boutons sont affichés après l'instruction séparés par "/" pour indiquer au
     *                    joueur les choix valides)
     * @param canPass     booléen indiquant si le joueur a le droit de passer sans
     *                    faire de choix. S'il est autorisé à passer, c'est la chaîne de
     *                    caractères vide ("") qui signifie qu'il désire passer.
     * @return le choix de l'utilisateur.
     */
    public String choose(String instruction, List<String> choices, boolean showButtons, boolean canPass) {
        // on retire les doublons de la liste des choix
        HashSet<String> distinctChoices = new HashSet<>(choices);
        // Aucun choix disponible
        if (distinctChoices.isEmpty())
            return "";
        else // Un seul choix possible (renvoyer cet unique élément)
            if (distinctChoices.size() == 1 && !canPass)
                return distinctChoices.iterator().next();
            else {
                String input;
                List<String> buttons = showButtons ? choices : new ArrayList<>();
                // Lit l'entrée de l'utilisateur jusqu'à obtenir un choix valide
                while (true) {
                    game.prompt(this, instruction, buttons, canPass);
                    input = game.readLine();
                    // si une réponse valide est obtenue, elle est renvoyée
                    if (distinctChoices.contains(input) || (canPass && input.equals("")))
                        return input;
                }
            }
    }

    /**
     * Attend une entrée de la part du joueur et renvoie le choix du joueur.
     * Dans cette méthode, la liste des choix est donnée sous la forme d'une liste de cartes et le joueur doit
     * choisir le nom d'une de ces cartes.
     *
     * @param instruction message à afficher à l'écran pour indiquer au joueur la nature du choix qui est attendu
     * @param choices     liste de cartes parmi lesquelles l'utilisateur doit choisir
     * @param showButtons indique s'il faut afficher des boutons pour permettre au joueur de choisir une des options.
     *                    Si c'est le cas, un bouton sera affiché pour chaque valeur dans `choices` (en mode console
     *                    les noms des boutons sont affichés après l'instruction séparés par "/" pour indiquer au
     *                    joueur les choix valides)
     * @param canPass     booléen indiquant si le joueur a le droit de passer sans faire de choix.
     *                    S'il est autorisé à passer, c'est la chaîne de caractères vide ("") qui signifie
     *                    qu'il désire passer (dans ce cas la fonction renvoie {@code null})
     * @return une carte de la liste {@code choices} dont le nom correspond au nom entré par le joueur (ou {@code null}
     * si le joueur passe ou si aucun choix n'est donné).
     */
    public Card chooseCard(String instruction, List<Card> choices, boolean showButtons, boolean canPass) {
        List<String> cardNames = new ArrayList<>();
        for (Card card : choices) {
            cardNames.add(card.getName());
        }
        String cardName = choose(
                instruction,
                cardNames,
                showButtons,
                canPass);
        for (Card card : choices) {
            if (card.getName().equals(cardName)) {
                return card;
            }
        }
        return null;
    }

    /**
     * Attend une entrée de la part du joueur et renvoie le choix du joueur.
     * Dans cette méthode, la liste des choix est donnée sous la forme d'une liste de joueurs et le joueur doit
     * choisir le nom d'un de ces joueurs.
     *
     * @param instruction message à afficher à l'écran pour indiquer au joueur la nature du choix qui est attendu
     * @param choices     liste de joueurs parmi lesquels l'utilisateur doit choisir
     * @param canPass     booléen indiquant si le joueur a le droit de passer sans faire de choix.
     *                    S'il est autorisé à passer, c'est la chaîne de caractères vide ("") qui signifie
     *                    qu'il désire passer (dans ce cas la fonction renvoie {@code null})
     * @return un joueur de la liste {@code choices} dont le nom correspond au nom entré par le joueur (ou {@code null}
     * si le joueur passe ou si aucun choix n'est donné).
     */
    public Player choosePlayer(String instruction, List<Player> choices, boolean canPass) {
        List<String> playerNames = new ArrayList<>();
        for (Player player : choices) {
            playerNames.add(player.getName());
        }
        String playerName = choose(
                instruction,
                playerNames,
                false,
                canPass);
        for (Player player : choices) {
            if (player.getName().equals(playerName)) {
                return player;
            }
        }
        return null;
    }

    /**
     * Renvoie une représentation de l'état du joueur sous forme d'une chaîne de caractères.
     * <p>
     * Cette représentation comporte
     * <ul>
     *   <li> le nom du joueur et le nom de son personnage
     *   <li> son rôle et son nombre de points de vie
     *   <li> la liste des cartes qu'il a en jeu
     *   <li> la liste des cartes dans sa main
     * </ul>
     * <p>
     * On pourrait par exemple avoir l'affichage suivant:
     * <pre>
     *   - John (Paul Regret)
     *   Rôle: SHERIFF      HP: ♥♥♥♥
     *   Arme: Remington (3)     En Jeu: Barrel (K♠)
     *   Main: Bang! (A♦), Bang! (6♣), Duel (8♣), Panic! (A♥)
     * </pre>
     *
     * @return une chaîne de caractères décrivant le joueur
     */
    @Override
    public String toString() {
        char[] hpChars = new char[healthPoints];
        Arrays.fill(hpChars, '♥');
        char[] missingHpChars = new char[getHealthPointsMax() - healthPoints];
        Arrays.fill(missingHpChars, '.');
        String weaponString = weapon == null ? "--" : weapon.getName();

        StringJoiner inPlayJoiner = new StringJoiner(", ");
        for (Card card : inPlay) {
            inPlayJoiner.add(card.toString());
        }
        StringJoiner handJoiner = new StringJoiner(", ");
        for (Card card : hand) {
            handJoiner.add(card.toString());
        }
        return String.format("  - %s (%s)\n", name, bangCharacter.getName())
                + String.format("  Rôle: %s      HP: %s%s\n", role, new String(hpChars), new String(missingHpChars))
                + String.format("  Arme: %s (%d)     En Jeu: %s\n", weaponString, getWeaponRange(), inPlayJoiner.toString())
                + String.format("  Main: %s\n", handJoiner.toString());
    }

    /**
     * @return une chaîne de caractères représentant les informations du joueur au format JSON
     * (utilisée par l'interface graphique)
     */
    public String toJSON() {
        StringJoiner joiner = new StringJoiner(", ");
        joiner.add(String.format("\"name\": \"%s\"", name));
        joiner.add(String.format("\"hp\": %d", healthPoints));
        joiner.add(String.format("\"hp_max\": %d", getHealthPointsMax()));
        joiner.add(String.format("\"character\": \"%s\"", bangCharacter.getName()));
        joiner.add(String.format("\"role\": \"%s\"", role));
        if (weapon != null) {
            joiner.add(String.format("\"weapon\": %s", weapon.toJSON()));
        }

        StringJoiner cardsJoiner;
        // main du joueur
        cardsJoiner = new StringJoiner(", ");
        for (Card c : hand) {
            cardsJoiner.add(c.toJSON());
        }
        joiner.add(String.format("\"hand\": [%s]", cardsJoiner.toString()));

        // cartes en jeu
        cardsJoiner = new StringJoiner(", ");
        for (Card c : inPlay) {
            cardsJoiner.add(c.toJSON());
        }
        joiner.add(String.format("\"in_play\": [%s]", cardsJoiner.toString()));

        return "{" + joiner.toString() + "}";
    }

    /**
     * Joue une carte de la main du joueur (ne fait rien si la carte n'est pas dans la main du joueur)
     *
     * @param card la carte à jouer
     */
    public void playFromHand(Card card) {
        if (hand.remove(card)) { //si carte presente dans la main alors l'enleve
            card.playedBy(this);
        }
    }

    /**
     * Ajoute une carte à la liste des cartes que le joueur a devant lui ("en jeu")
     *
     * @param card carte à ajouter à la liste
     */
    public void addToInPlay(BlueCard card) {
        inPlay.add(card);
    }

    /**
     * Retire une carte de la liste des cartes que le joueur a devant lui ("en jeu")
     *
     * @param card carte à retirer de la liste
     */
    public void removeFromInPlay(BlueCard card) {
        if (card==weapon){
            this.weapon=null;
        }
        inPlay.remove(card);
    }

    /**
     * Exécute un tour complet du joueur. Le tour comprend plusieurs phases :
     * <ul>
     *     <li> Résolution des effets préliminaires (Dynamite et Jail)
     *     <li> Piocher des cartes en main (en général 2 cartes, mais peut être modifié par le personnage)
     *     <li> Jouer des cartes que le joueur a en main
     *     <li> Défausser des cartes si le joueur a plus de cartes qu'il a de points de vie restants
     * </ul>
     */
    public void playTurn() {
        // phase 0: setup et résolution des effets préliminaires (dynamite, prison, etc...)
        if (getInPlay().contains(getCardInPlay("Dynamite"))) {
            Card cartedaigner = randomDraw();
            if (cartedaigner.getSuit() == CardSuit.SPADE) { //si la carte degainer est un pique
                if (cartedaigner.getValue() >= 2 && cartedaigner.getValue() <= 9) {
                    this.decrementHealth(3, null); //la dynamite explose
                    this.discard(this.getCardInPlay("Dynamite"));

                    this.removeFromInPlay(this.getCardInPlay("Dynamite"));
                } else {
                    getOtherPlayers().get(1).addToInPlay(this.getCardInPlay("Dynamite")); //la dynamite passe au joueur d'apres

                    this.removeFromInPlay(this.getCardInPlay("Dynamite"));

                }
            } else {
                this.getOtherPlayers().get(1).addToInPlay(getCardInPlay("Dynamite")); //la dynamite passe au joueur d'apres
                this.removeFromInPlay(this.getCardInPlay("Dynamite"));
            }
        }

        boolean prison=false;
        if (getInPlay().contains(getCardInPlay("Jail"))) {
            Card cartedaigner = randomDraw();
            if (cartedaigner.getSuit() != CardSuit.HEART) { //si la carte degainer n'est pas un coeur
                discardFromInPlay(getCardInPlay("Jail")); //je defausse la carte
                removeFromInPlay(getCardInPlay("Jail")); // je l'enlève du InPlay
                prison = true; //je suis en prison donc je passe mon tour
            } else {
                discardFromInPlay(getCardInPlay("Jail")); //je defausse la prison mais je peux jouer mon tour
                removeFromInPlay(getCardInPlay("Jail")); // je l'enlève du InPlay
            }
        }


        if (!prison) {
            // phase 1: piocher des cartes
            bangCharacter.onStartTurn(this);

            // phase 2: jouer des cartes
            while (true) {
                List<Card> possibleCards = new ArrayList<>();
                for (Card c : hand) {
                    if (c.canPlayFromHand(this)) { //appel du canPlayFromHand de chaque carte pour savoir si elle peut etre joué
                        possibleCards.add(c);
                    }
                }
                Card card = chooseCard("Choisissez une carte à jouer", possibleCards, false, true);
                if (card == null) break;
                playFromHand(card);
            }

            // phase 3: défausser les cartes en trop
            while (hand.size() > healthPoints) {
                Card card = chooseCard(String.format("Défaussez pour n'avoir que %d carte(s) en main", healthPoints), hand, false, false);
                if (card != null) {
                    discardFromHand(card);
                }
            }

            // phase 4: remettre boolean à bang deja jouer a faux
            bangDejaJoue = false;
        }
    }


    public BlueCard chooseBlueCard(String instruction, List<BlueCard> choices, boolean showButtons, boolean canPass) {
        List<String> cardNames = new ArrayList<>();
        for (BlueCard card : choices) {
            cardNames.add(card.getName());
        }
        String cardName = choose(
                instruction,
                cardNames,
                showButtons,
                canPass);
        for (BlueCard card : choices) {
            if (card.getName().equals(cardName)) {
                return card;
            }
        }
        return null;
    }

}
