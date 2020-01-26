package simpleblackjack;

import simpleblackjack.game.Blackjack;

public class SimpleBlackjack {

    public static void main(String args[])
    {
        System.out.println("Hello World");

        Blackjack bj = new Blackjack(1);

        for (int i = 0; i < 10; i++)
        {
            bj.startRound();
        }
    }
}
