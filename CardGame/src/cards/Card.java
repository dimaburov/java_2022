package cards;

import java.util.Objects;

public class Card {
    private final Suit suit;
    private final Value value;

    public Card(Suit suit, Value value) {
        this.suit = suit;
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Card card = (Card) o;
        return suit == card.suit && value == card.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(suit, value);
    }

    @Override
    public String toString() {
        return value + " " + suit;
    }

    public int getValue() {
        return value.getValue();
    }

    public static void main(String[] args) {
        Card c = new Card(Suit.SPADES, Value.QUEEN);
        System.out.println(c);
    }
}
