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
            ai.setInput(Hand.computeScore(getHand().getCards()) / BlackjackNeuralNetwork.getNormalizeValue(),tableView.visibleCardCount() / BlackjackNeuralNetwork.getNormalizeValue(),
                    tableView.visibleCardSum() / BlackjackNeuralNetwork.getNormalizeValue());

            ai.calculate();

            if (ai.getOutput()[0] > 0.5)
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
