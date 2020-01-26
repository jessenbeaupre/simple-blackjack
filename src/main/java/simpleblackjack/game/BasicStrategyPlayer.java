package simpleblackjack.game;

public class BasicStrategyPlayer extends Player
{
    @Override
    public void playTurn(TableView tableView, Deck deck)
    {

        for (Card card : this.hand.getCards())
        {
            handValue += card.getValue();
        }

        Boolean stay = false;
        do
        {
            if (handValue < 17)
            {
                Card hitCard = deck.deal();
                handValue += hitCard.getValue();
                hand.addCard(hitCard);
            } else
            {
                stay = true;
            }
        }while (!stay);

    }
}
