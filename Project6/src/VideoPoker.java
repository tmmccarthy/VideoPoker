/**
 * This is a class that handles logic behind a game of Video Poker
 *
 * @author Tyler McCarthy
 */
public class VideoPoker {
    
    /** Indicates that a random game should be played */
    public static final int RANDOM_GAME = -1;
    /** The number of cards in a hand */
    public static final int CARDS_IN_HAND = 5;
    /** The number of points that the player has when the game begins */
    public static final int STARTING_POINTS = 100;
    /** The number of points needed to play a new game */
    public static final int POINTS_FOR_NEW_GAME = 10;
    /** The number of points awarded for a Royal Flush */
    public static final int ROYAL_FLUSH = 100;
    /** The number of points awarded for a Straight Flush */
    public static final int STRAIGHT_FLUSH = 60;
    /** The number of points awarded for Four of a Kind */
    public static final int FOUR_OF_A_KIND = 50;
    /** The number of points awarded for a Full House */
    public static final int FULL_HOUSE  = 40;
    /** The number of points awarded for a Flush */
    public static final int FLUSH  = 30;
    /** The number of points awarded for a Straight */
    public static final int STRAIGHT = 25;
    /** The number of points awarded for Three of a Kind */
    public static final int THREE_OF_A_KIND = 15;
    /** The number of points awarded for Two Pairs*/
    public static final int TWO_PAIRS = 10;
    /** The number of points awarded for One Pair */
    public static final int ONE_PAIR = 7;
    
    /** The Deck of cards that will be used to play the game */
    private Deck deck;
    /** The Hand of Cards in the game */
    private Hand hand;
    /** The current number of points */
    private int points;
    
    /**
     * VideoPoker constructor
     *
     * @param seed seed used to generate a specific deck
     */
    public VideoPoker(int seed) {
        deck = new Deck(seed);
        points = STARTING_POINTS;
    }
    
    /**
     * Returns the number of points
     * 
     * @return integer number of points
     */
    public int getPoints() {
        return points;
    }
    
    /** 
     * Returns the name of the image file for the Card at he given index in the hand
     *
     * @param index index of Card
     * @return name of file that represents the cards
     */
    public String getCardFileName(int index) {
        return "cards/" + hand.getCard(index).toString() + ".gif";
    }
    
    /**
     * Returns the card at the given index in the hand 
     *
     * @param index index of Card
     * @return Card at the given index
     */
    public Card getCard(int index) {
        return hand.getCard(index);
    }
    
    /**
     * Subtracts POINTS_FOR_NEW_GAME from points and shuffles the deck.
     */
    public void newGame() {
        points -= POINTS_FOR_NEW_GAME;
        deck.shuffle();
        Card[] newHand = new Card[CARDS_IN_HAND];
        for (int i = 0; i < CARDS_IN_HAND; i++) {
            newHand[i] = deck.nextCard();
        }
        hand = new Hand(newHand);
    }
    
    /**
     * Gets the next Card from the deck 
     * and requests the hand to replace the card at the given index with the new Card
     *
     * @param index index of Card to replace
     */
    public void replaceCard(int index) {
        hand.replace(index, deck.nextCard());
    }
    
    /**
     * Returns whatever the hand was
     *
     * @return name of the hand
     */
    public String scoreHand() {
        if (hand.isRoyalFlush()) {
            points += ROYAL_FLUSH;
            return "Royal Flush";
        } else if (hand.isStraightFlush()) {
            points += STRAIGHT_FLUSH;
            return "Straight Flush";
        } else if (hand.hasFourOfAKind()) {
            points += FOUR_OF_A_KIND;
            return "Four of a Kind";
        } else if (hand.isFullHouse()) {
            points += FULL_HOUSE;
            return "Full House";
        } else if (hand.isFlush()) {
            points += FLUSH;
            return "Flush";
        } else if (hand.isStraight()) {
            points += STRAIGHT;
            return "Straight";
        } else if (hand.hasThreeOfAKind()) {
            points += THREE_OF_A_KIND;
            return "Three of a Kind";
        } else if (hand.hasTwoPairs()) {
            points += TWO_PAIRS;
            return "Two Pairs";
        } else if (hand.hasOnePair()) {
            points += ONE_PAIR;
            return "One Pair";
        } else {
            return "No Pair";
        }
    }
}