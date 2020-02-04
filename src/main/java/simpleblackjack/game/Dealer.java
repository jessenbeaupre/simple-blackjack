package simpleblackjack.game;

public class Dealer extends Player
{
    @Override
    public void playTurn(TableView tableView, Deck deck)
    {
        Boolean stay = false;
        do
        {
            if (Hand.computeScore(getHand().getCards()) < 17)
            {
                Card hitCard = deck.deal();
                hand.addCard(hitCard);
            } else
            {
                stay = true;
            }
        } while (!stay);

    }

    public Card getDealerShowing(){
        Card dealersCard = hand.getCards().get(0);
        return dealersCard;
    }
}
