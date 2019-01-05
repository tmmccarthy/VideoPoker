import java.util.Random;
import java.util.Arrays;

/**
 * This is a class that is used to create objects that represent a deck of playing cards
 *
 * @author Tyler McCarthy
 */
public class Deck {
    
    /** An integer value that represents the number of cards in a deck */
    public static final int CARDS_IN_DECK = 52;
    /** Number of suits in a deck */
    public static final int NUM_SUITS = 4;
    
    /** An array of Card objects that manages the cards in the deck */
    private Card[] deckOfCards;
    /** An integer that holds the index of the next card to be dealt from deckOfCards */
    private int cardIndex;
    /** An integer variable that maintains the random seed provided for testing */
    private int seed;
    
    /**
     * Deck constructor
     *
     * @param seed the random seed needed for testing
     */
    public Deck(int seed) {
        deckOfCards = new Card[CARDS_IN_DECK];
        Card singleCard = null;
        char[] suitsInDeck = {Card.CLUBS, Card.DIAMONDS, Card.HEARTS, Card.SPADES};
        for (int i = 0; i < NUM_SUITS; i++) {
            for (int j = Card.LOWEST_VALUE; j <= Card.HIGHEST_VALUE; j++) {
                singleCard = new Card(j, suitsInDeck[i]);
                deckOfCards[j - Card.LOWEST_VALUE + i * (Card.HIGHEST_VALUE - 1)]
                        = singleCard;
            }
        }
        this.seed = seed;
        cardIndex = 0;
    }
    
    /**
     * Resets the deck for a new hand to be played
     */
    public void shuffle() {
        Random r = null;
        if (seed == -1) {
            r = new Random();
        } else {
            r = new Random(seed);
        }
        Card temp = null;
        for (int i = CARDS_IN_DECK - 1; i > 0; i--) {
            int rand = r.nextInt(i);
            temp = deckOfCards[i];
            deckOfCards[i] = deckOfCards[rand];
            deckOfCards[rand] = temp;
        }
        cardIndex = 0;
    }
    
    /**
     * Returns the next card in the deck
     *
     * @return the next card in the deck
     */
    public Card nextCard() {
        cardIndex += 1;
        return deckOfCards[cardIndex - 1];
    }
    
    /**
     * Compares the value of two Deck Objects
     *
     * @param o Object to compare to Deck
     * @return boolean that signifies whether the two Objects are equal
     */
    public boolean equals(Object o) {
        if (o instanceof Deck) {
            Deck d = (Deck) o;
            return Arrays.equals(deckOfCards, d.deckOfCards)
                    && cardIndex == d.cardIndex && seed == d.seed;
        } else {
            return false;
        }
    }
    
    /**
     * Returns a String representation of the Deck
     *
     * @return String representation of the Deck
     */
    public String toString() {
        String deckList = "";
        for (int i = 0; i < CARDS_IN_DECK; i++) {
            deckList += "card " + i + ": " + deckOfCards[i].toString() + "\n";
        }
        return deckList;
    }
}