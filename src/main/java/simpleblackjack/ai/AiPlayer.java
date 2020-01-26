package simpleblackjack.ai;

import org.neuroph.core.NeuralNetwork;
import simpleblackjack.game.*;

public class AiPlayer extends Player
{

    NeuralNetwork ai;

    public AiPlayer(NeuralNetwork ai)
    {
        this.ai = ai;
    }

    @Override
    public void playTurn(TableView tableView, Deck deck)
    {
        boolean stand = false;
        while (!stand)
        {
            ai.setInput(
                    (double)Hand.computeScore(getHand().getCards()) / 31.0,
                    (double)tableView.visibleCardCount() / 52.0,
                    (double)tableView.visibleCardSum() / 156.0 );

            ai.calculate();

            if (ai.getOutput()[0] > 0.5 && Hand.computeScore(getHand().getCards()) < 21)
            {
                Card hitCard = deck.deal();
                getHand().addCard(hitCard);
            } else
            {
                stand = true;
            }

        }
    }
}
