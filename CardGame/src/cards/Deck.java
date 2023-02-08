package cards;

import java.util.Random;

public class Deck {
    public static final int MAX_CARDS = 36;
    public static final Deck DECK = new Deck();

    private static final Random rnd = new Random();

    private final Card[] cards = new Card[MAX_CARDS];
    private int count;

    private Deck() {
        count = 0;
        for (Suit s : Suit.values()) {
            for (Value v : Value.values()) {
                cards[count++] = new Card(s,v);
            }
        }
    }

    public Card getCard() {
        int index = rnd.nextInt(count);
        Card c = cards[index];
        count --;
        cards[index] = cards[count];
        cards[count] = c;
        return c;
    }

    public void shuffle() {
        count = MAX_CARDS;
    }
}
