package simpleblackjack.game;

import java.util.List;

public class Blackjack implements TableView
{
    private List<Player> players;

    Player dealer;

    private Deck deck = new Deck();

    boolean verbose = false;

    double handsPlayed = 0.0;
    double handsWon = 0.0;

    public Blackjack(List<Player> players, boolean verbose)
    {
        this.players = players;
        this.verbose = verbose;

        dealer = new BasicStrategyPlayer();
        players.add(dealer);
    }

    public void playHand()
    {
        dealHand();
        playTurns();
        scoreRound();
    }

    private void dealHand()
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

    private void playTurns()
    {
        for (Player player : players)
        {
            player.playTurn(this, deck);
        }
    }

    private void scoreRound()
    {
        int dealerScore = Hand.computeScore(dealer.getHand().getCards());
        for (Player player : players)
        {
            if (player != dealer)
            {
                handsPlayed += 1.0;
                int playerScore = Hand.computeScore(player.getHand().getCards());
                String gameState = "loses";
                if ((playerScore > dealerScore || dealerScore > 21) && playerScore <= 21)
                {
                    player.scoreHand(1);
                    deck.returnHand(player.hand);
                    gameState = "wins";
                    handsWon += 1.0;
                } else
                {
                    player.scoreHand(0);
                    deck.returnHand(player.hand);
                }

                if (verbose)
                {
                    System.out.println("Player " + gameState + " \t " + playerScore + "-" + dealerScore);
                }
            }
        }
    }

    public double getWinRatio()
    {
        return handsWon / handsPlayed;
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
