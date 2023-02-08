package cards;

public enum Value {
    SIX("Шестёрка", 6), SEVEN("Семёрка", 7), EIGHT("Восьмёрка", 8), NINE("Девятка", 9),
    TEN("Десятка", 10), JACK("Валет", 2), QUEEN("Дама", 3), KING("Король", 4), ACE("Туз", 11);

    private final String name;
    private final int value;

    Value(String name, int val) {
        this.name = name;
        this.value = val;
    }

    @Override
    public String toString() {
        return name;
    }

    public int getValue() {
        return value;
    }
}
