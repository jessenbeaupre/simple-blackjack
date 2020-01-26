package simpleblackjack.game;

public abstract class Player
{
    int score = 0;
    int handValue = 0;
    Hand hand;

    public Player()
    {
        hand = new Hand();
    }

    public void setHand(Hand hand)
    {
        this.hand = hand;
    }

    public Hand getHand()
    {
        return hand;
    }

    public int getHandValue() {return handValue;}

    public void setHandValue(int handValue) {this.handValue = handValue;}

    public void scoreHand(int score) {
        score += score;
    }

    public abstract void playTurn(TableView tableView, Deck deck);

}
