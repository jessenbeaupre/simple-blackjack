package simpleblackjack.game;

import java.util.ArrayList;
import java.util.List;

public class Blackjack implements TableView
{
    private List<Player> players = new ArrayList<>();
    private int currentPlayer = 0;

    Player dealer;
    private Deck deck = new Deck();

    public Blackjack(int numPlayers)
    {
        for (int i = 0; i < numPlayers; i++)
        {
            players.add(new BasicStrategyPlayer());
        }

        dealer = new BasicStrategyPlayer();
        players.add(dealer);
    }

    public void startRound()
    {
        // Return the players' hands to the deck.
        for (Player player : players)
        {
            deck.returnHand(player.getHand());
        }

        // Shuffle the deck.
        deck.shuffle();

        // Deal out the hands.
        for (Player player : players)
        {
            player.setHand(deck.deal(2));
        }
    }

    public void playRound()
    {

    }

    public void scoreRound()
    {

    }

    @Override
    public int visibleDealerScore()
    {
        return 0;
    }

    @Override
    public int visibleCardSum()
    {
        return 0;
    }

    @Override
    public int visibleCardCount()
    {
        return 0;
    }
}
