/**
 * This is a class that is used to create playing card objects
 *
 * @author Tyler McCarthy
 */
public class Card implements Comparable<Card> {
    
    /** A char value that represents the suit "Clubs." */
    public static final char CLUBS = 'c';
    /** A char value that represents the suit "Diamonds." */
    public static final char DIAMONDS = 'd';
    /** A char value that represents the suit "Spades." */
    public static final char SPADES = 's';
    /** A char value that represents the suit "Hearts." */
    public static final char HEARTS = 'h';
    /** The lowest numeric value for a card. */
    public static final int LOWEST_VALUE = 2;
    /** The highest numeric value for a card. */
    public static final int HIGHEST_VALUE = 14;
    /** An integer that represents the value shown on the card */
    private int value;
    /** A char that represents the suit shown on the card,
        based on the values seen in the class constants */
    private char suit;
    
    /**
     * Class constructor
     * 
     * @param value integer value of the card
     * @param suit suit of the card
     * @throw IllegalArgumentException if either parameter is invalid
     */
    public Card(int value, char suit) {
        //Test if value is invalid
        if ((value < LOWEST_VALUE) || (value > HIGHEST_VALUE)) {
            throw new IllegalArgumentException("Invalid value");
        //Test if suit is valid
        } else if ((suit == DIAMONDS) || (suit == CLUBS) || (suit == SPADES) || (suit == HEARTS)) {
            //Initializes fields
            this.value = value;
            this.suit = suit;
            
        } else {
            throw new IllegalArgumentException("Invalid suit");
        }
    }
    
    /**
     * Gets the value of the card
     *
     * @return value of the card
     */
    public int getValue() {
        return value;
    }
    
    /**
     * Gets the suit of the card
     *
     * @return suit of the card
     */
    public char getSuit() {
        return suit;
    }
    
    /**
     * Compares two Card objects
     * 
     * @param o Object for comparison
     * @return boolean that signifies whether the two Objects are equal
     */
    public boolean equals(Object o) {
        if (o instanceof Card) {
            Card c = (Card) o;
            return ((c.getValue() == value) && (c.getSuit() == suit));
        } else {
            return false;
        }
    }
    
    /**
     * Returns the Card object as a String
     *
     * @return String of the card object
     */
    public String toString() {
        return Character.toString(suit) + value;
    }
    
    /**
     * This method is used for sorting the cards in a player's hand in a game of
     * Poker. Cards are sorted first by value, then by suit.
     * 
     * @param other
     *            The Card object to which this Card is being compared.
     * @return negative value if this Card should be before the other Card,
     *         positive value if this Card should be after the other Card.
     */
    public int compareTo(Card other) {
        if (this.value != other.value) {
            return this.value - other.value;
        } else {
            return this.suit - other.suit;
        }
    }
}