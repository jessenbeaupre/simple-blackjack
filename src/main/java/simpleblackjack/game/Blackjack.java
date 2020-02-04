package simpleblackjack.game;

import java.util.List;

public class Blackjack implements TableView
{
    private List<Player> players;

    Dealer dealer;

    private Deck deck = new Deck();

    boolean verbose = false;

    double handsPlayed = 0.0;
    double handsWon = 0.0;
    double handsTied = 0.0;

    public Blackjack(List<Player> players, boolean verbose)
    {
        dealer = new Dealer();
        players.add(0, dealer);

        this.players = players;
        this.verbose = verbose;
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
                }
                else if (playerScore <= 21 && playerScore == dealerScore)
                {
                    //might need to change to a .5 later
                    player.scoreHand(1);
                    deck.returnHand(player.hand);
                    gameState = "ties";
                    handsTied += 1.0;
                }
                else
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

    public double getTieRatio()
    {
        return handsTied / handsPlayed;
    }

    @Override
    public int visibleDealerScore()
    {
        return Hand.computeScore(dealer.getHand().getCards());
    }

    @Override
    public int visibleCardSum()
    {
        int cardSum = 0;

        for (Player player: players)
        {
            cardSum += Hand.computeScore(player.getHand().getCards());
        }
        return cardSum;
    }

    @Override
    public int visibleCardCount()
    {
        int cardsTotal = 0;

        for (Player player: players)
        {
            cardsTotal += player.getHand().size();
        }
        return cardsTotal;
    }

    public Dealer getDealer(){
        return dealer;
    }
}
