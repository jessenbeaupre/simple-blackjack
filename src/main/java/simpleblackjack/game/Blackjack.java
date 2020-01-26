package simpleblackjack.game;

import java.util.List;

public class Blackjack implements TableView
{
    private List<Player> players;

    Player dealer;
    private Deck deck = new Deck();

    public Blackjack(List<Player> players)
    {
        this.players = players;

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
                int playerScore = Hand.computeScore(player.getHand().getCards());
                if ( (playerScore > dealerScore || dealerScore > 21 ) && playerScore <= 21 )
                {
                    System.out.println("Player wins with " + playerScore + " over dealer's " + dealerScore);
                    player.scoreHand(1);
                    deck.returnHand(player.hand);
                }
                else
                {
                    System.out.println("Player loses with " + playerScore + " over dealer's " + dealerScore);

                    player.scoreHand(0);
                    deck.returnHand(player.hand);
                }
            }
            else
            {
                deck.returnHand(dealer.hand);
            }
        }

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
