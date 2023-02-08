package game;

import cards.Deck;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Game {
    private static final Scanner scanner = new Scanner(System.in);

    private static void game() throws Exception {
        Deck.DECK.shuffle();
        System.out.print("Введите кол-во игроков (2 - 5): ");
        int countGamer  = scanner.nextInt();
        List<Player> players = new ArrayList<>();

        if (countGamer < 2 || countGamer > 5) throw new Exception("Введено недопустимое кол-во игроков");

        //Выдача карт и запись игроков
        for (int i =0;i<countGamer;i++){
            players.add(new Player("Player "+(i+1)));
            players.get(i).getSixCard();
            System.out.println(players.get(i).toString());
        }

        //Подсчёт очков
        int maxValue = Integer.MIN_VALUE;
        int playerPoint = 0;
        for (Player player:players) {
            playerPoint = player.points();
            if (maxValue < playerPoint) maxValue = playerPoint;
        }
        //Вывод результатов
        System.out.print("\n Максимальная разность = " + maxValue + " у игроков ");
        for (Player player:players){
            if (player.points() == maxValue) System.out.print(player.getName());
        }
    }

    public static void main(String[] args) {
        try {
            game();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
