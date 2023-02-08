package game;

import cards.Card;
import cards.Deck;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Player {
    private final String name;
    private final List<Card> cards = new ArrayList<>();

    public Player(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void getSixCard(){
        for (int i=0;i<6;i++){
            cards.add(Deck.DECK.getCard());
        }
    }



    public int points() {
        //Сортировка
        Collections.sort(cards, new Comparator<Card>() {
            @Override
            public int compare(Card o1, Card o2) {
                return o1.getValue() - o2.getValue();
            }
        });

        return cards.get(cards.size()-1).getValue() -  cards.get(0).getValue();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(name).append('\n').append("-----------\n");
        for (Card card : cards) {
            sb.append(card).append('\n');
        }
        return sb.toString();
    }
}
