package codinginterview.online;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class ComparatorTest {


    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();

        Player[] player = new Player[n];
        Checker checker = new Checker();

        for(int i = 0; i < n; i++){
            player[i] = new Player(scan.next(), scan.nextInt());
        }
        scan.close();

        Arrays.sort(player, checker);
        for(int i = 0; i < player.length; i++){
            System.out.printf("%s %s\n", player[i].name, player[i].score);
        }
    }

    static class Checker implements Comparator<Player> {
        public int compare(Player first, Player second) {
            int comparison = first.score > second.score ? -1 : first.score < second.score ? 1 : 0;
            if (comparison == 0) {
                comparison = first.name.compareTo(second.name);
            }
            return comparison;
        }
    }

    static class Player{
        String name;
        int score;

        Player(String name, int score){
            this.name = name;
            this.score = score;
        }
    }
}
