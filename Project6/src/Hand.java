import java.util.*;

/** 
 * Represents hand of cards
 * @author Dan Longo
 * @author Suzanne Balik
 * @author Tyler McCarthy
 */
public class Hand {

    /** Represents the number of cards a Hand holds at a time */
    public static final int CARDS_IN_HAND = 5;
    /** Represents the value of the first card in a royal flush */
    public static final int FIRST_VALUE_ROYAL = 10;
    /** Represents the number of values that must be equal for a hand to have four of a kind */
    public static final int FOUR_OF_A_KIND = 4;
    /** Represents the number of values that must be equal for a hand to have three of a kind */
    public static final int THREE_OF_A_KIND = 3;
    /** Represents the number of values that must be equal for a hand to have a pair */
    public static final int PAIR = 2;
    /** Represents the number of pairs in hand that has two pairs */
    public static final int TWO_PAIRS = 2;
    
    /** Contains cards in hand */
    private Card[] hand;

    /**
     * Constructs a Hand Object
     *
     * @param hand array of the cards in a hand
     */
    public Hand(Card[] hand) {
        if (Arrays.equals(hand, null)) {
            throw new NullPointerException("Null array");
        }
        if (hand.length != CARDS_IN_HAND) {
            throw new IllegalArgumentException("Invalid array length");
        }
        for (int i = 0; i < hand.length; i++) {
            if (hand[i] == null) {
                throw new NullPointerException("Null element");
            }
        }
        this.hand = hand;
    }

    /**
     * Returns a specific card in a Hand
     *
     * @param index index of the card to return
     * @return the card at the specific index
     */
    public Card getCard(int index) {
        if ((index < 0) || (index >= CARDS_IN_HAND)) {
            throw new IllegalArgumentException("Invalid index");
        }
        return hand[index];
    }
    
    /**
     * Replaces a current Card in a Hand with a new Card
     *
     * @param index index of Card to be replaced
     * @param card new Card
     */
    public void replace(int index, Card card) {
        if ((index < 0) || (index >= CARDS_IN_HAND)) {
            throw new IllegalArgumentException("Invalid index");
        }
        if (card == null) {
            throw new NullPointerException("Null card");
        }
        hand[index] = card;
    }
    
    /**
     * Creates a String represenation of the Hand
     *
     * @return String represenation of the Hand
     */
    public String toString() {
        return Arrays.toString(hand);
    }
    
    /**
     * Compares the value of two Hand Objects
     *
     * @param o Object to compare to Hand
     * @return boolean that signifies the equality of two Hand Objects
     */
    public boolean equals(Object o) {
        if (o instanceof Hand) {
            Hand h = (Hand) o;
            return Arrays.equals(getSortedHand(), h.getSortedHand());
        } else {
            return false;
        }
    }
    
    /**
     * Determines if a Hand is a flush
     *
     * @return boolean that signifies if the Hand is a flush
     */
    public boolean isFlush() {
        for (int i = 1; i < CARDS_IN_HAND; i++) {
            if (hand[i].getSuit() != hand[i - 1].getSuit()) {
                return false;
            }
        }
        return true;
    }
    
    /**
     * Determines if a Hand is a straight
     *
     * @return boolean that signifies if the Hand is a straight
     */
    public boolean isStraight() {
        Card[] sortedHand = getSortedHand();
        for (int i = 1; i < CARDS_IN_HAND; i++) {
            if (sortedHand[i].getValue() != 1 + sortedHand[i - 1].getValue()) {
                return false;
            }
        }
        return true;
    }
    
    /**
     * Determines if a Hand is a straight flush
     *
     * @return boolean that signifies if the Hand is a straight flush
     */
    public boolean isStraightFlush() {
        return isFlush() && isStraight();
    }
    
    /**
     * Determines if a Hand is a royal flush
     *
     * @return boolean that signifies if the Hand is a royal flush
     */
    public boolean isRoyalFlush() {
        if (!isFlush()) {
            return false;
        }
        Card[] sortedHand = getSortedHand();
        for (int i = 0; i < CARDS_IN_HAND; i++) {
            if (sortedHand[i].getValue() != i + FIRST_VALUE_ROYAL) {
                return false;
            }
        }
        return true;
    }
    
    /**
     * Determines if a Hand has four cards with the same value
     *
     * @return boolean that signifies if the Hand is has four of a kind
     */
    public boolean hasFourOfAKind() {
        for (int i = 0; i < Card.HIGHEST_VALUE + 1; i++) {
            if (getCounts()[i] == FOUR_OF_A_KIND) {
                return true;
            }
        }
        return false;
    }
    
    /**
     * Determines if a Hand has four cards with the same value
     *
     * @return boolean that signifies if the Hand is has four of a kind
     */
    public boolean hasThreeOfAKind() {
        for (int i = 0; i < Card.HIGHEST_VALUE + 1; i++) {
            if (getCounts()[i] == THREE_OF_A_KIND) {
                return true;
            }
        }
        return false;
    }
    
    /**
     * Determines if a Hand has two different pairs
     *
     * @return boolean that signifies if the Hand has two pairs
     */
    public boolean hasTwoPairs() {
        int pairCount = 0;
        for (int i = 0; i < Card.HIGHEST_VALUE + 1; i++) {
            if (getCounts()[i] == PAIR) {
                pairCount += 1;
            }
        }
        if (pairCount == TWO_PAIRS) {
            return true;
        }
        return false;
    }
    
    /**
     * Determines if a Hand has one pair
     *
     * @return boolean that signifies if the Hand has one pair
     */
    public boolean hasOnePair() {
        int pairCount = 0;
        for (int i = 0; i < Card.HIGHEST_VALUE + 1; i++) {
            if (getCounts()[i] == PAIR) {
                pairCount += 1;
            }
        }
        if (pairCount == 1) {
            return true;
        }
        return false;
    }
    
    /** Determiens if Hand is a full house
     *
     * @return boolean tha signifies if the Hand is a full house
     */
    public boolean isFullHouse() {
        boolean threeOfAKind = false;
        boolean twoOfAKind = false;
        for (int i = 0; i < Card.HIGHEST_VALUE + 1; i++) {
            if (getCounts()[i] == THREE_OF_A_KIND) {
                threeOfAKind = true;
            }
            if (getCounts()[i] == PAIR) {
                twoOfAKind = true;
            }
        }
        return threeOfAKind && twoOfAKind;
    }
    
    /**
     * Counts the number of cards with each value in the hand
     * @return tally array containing number of cards of each value from 2 to 14.
     */
    public int[] getCounts() {
        int[] counts = new int[Card.HIGHEST_VALUE + 1];
        for (int i = 0; i < hand.length; i++) {
            counts[hand[i].getValue()]++;
        }
        return counts;
    }

    /**
     * Creates a copy of the hand sorted first by value, then by suit
     * @return copy of the hand sorted first by value, then by suit
     */
    public Card[] getSortedHand() {
        Card[] sortedHand = Arrays.copyOf(hand, hand.length);
        Arrays.sort(sortedHand);
        return sortedHand;
    }
}