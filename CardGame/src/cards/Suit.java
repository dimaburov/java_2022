package cards;

public enum Suit {
    SPADES("пики"), CLUBS("трефы"), DIAMONDS("бубны"), HEARTS("червы");

    private final String name;

    Suit(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
