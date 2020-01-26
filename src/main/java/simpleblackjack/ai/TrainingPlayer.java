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
        while (Hand.computeScore(getHand().getCards()) < 21)
        {
            states.add(new TrainingState(
                    tableView.visibleCardSum(),
                    tableView.visibleCardCount(),
                    Hand.computeScore(getHand().getCards()),
                    1.0));

            getHand().addCard(deck.deal());
        }

        states.add(new TrainingState(
                tableView.visibleCardSum(),
                tableView.visibleCardCount(),
                Hand.computeScore(getHand().getCards()),
                0.0));
    }
}
