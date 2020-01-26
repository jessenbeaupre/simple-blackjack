package simpleblackjack.ai;

import simpleblackjack.game.Deck;
import simpleblackjack.game.Hand;
import simpleblackjack.game.Player;
import simpleblackjack.game.TableView;

import java.util.ArrayList;
import java.util.List;

public class TrainingPlayer extends Player
{
    List<TrainingState> states = new ArrayList<>();

    @Override
    public void playTurn(TableView tableView, Deck deck)
    {
        int originalScore = Hand.computeScore(getHand().getCards());

        // Hit me!
        getHand().addCard(deck.deal());

        while (Hand.computeScore(getHand().getCards()) < 21)
        {
            states.add(new TrainingState(
                    tableView.visibleCardSum(),
                    tableView.visibleCardCount(),
                    originalScore,
                    1.0));


            originalScore = Hand.computeScore(getHand().getCards());

            // Hit me!
            getHand().addCard(deck.deal());
        }

        states.add(new TrainingState(
                tableView.visibleCardSum(),
                tableView.visibleCardCount(),
                originalScore,
                0.0));
    }

    public List<TrainingState> getStates()
    {
        return states;
    }
}
