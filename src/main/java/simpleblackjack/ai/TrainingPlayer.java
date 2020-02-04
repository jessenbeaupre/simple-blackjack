package simpleblackjack.ai;

import simpleblackjack.game.*;

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
        //getHand().addCard(deck.deal());

        while (Hand.computeScore(getHand().getCards()) < 21)
        {
            originalScore = Hand.computeScore(getHand().getCards());

            // Hit me!
            getHand().addCard(deck.deal());

            double correctValue;

            if (Hand.computeScore(this.getHand().getCards()) > 21)
            {
                //we busted
                correctValue = 0.0;
            }
            else if (    //dealer didn't bust
                    Hand.computeScore(tableView.getDealer().getHand().getCards()) <= 21 &&
                    //out hand is less than or equal to dealer hand
                    Hand.computeScore(this.getHand().getCards()) <= Hand.computeScore(tableView.getDealer().getHand().getCards()))
            {
                //might not have won but was still a good hit
                correctValue = 0.75;
            }
            else if (       //dealer didn't bust
                            Hand.computeScore(tableView.getDealer().getHand().getCards()) <= 21 &&
                            //our hand is more than dealer hand
                            (Hand.computeScore(this.getHand().getCards()) < Hand.computeScore(tableView.getDealer().getHand().getCards())))
            {
                correctValue = 1.0;
            }
            else if (    //dealer did bust
                         Hand.computeScore(tableView.getDealer().getHand().getCards()) > 21 &&
                         //out hand is less than or equal to 21
                         Hand.computeScore(tableView.getDealer().getHand().getCards()) <= 21)
            {
                //weight less so we don't stay too much on a premonition
                correctValue = 0.75;
            }
            else   //how knows what happened don't reinforce either way
            {
                correctValue = 0.5;
            }
            states.add(new TrainingState(
                    tableView.visibleCardSum(),
                    tableView.visibleCardCount(),
                    originalScore,
                    tableView.getDealer().getDealerShowing().getValue(),
                    correctValue));

        }
    }


    public List<TrainingState> getStates()
    {
        return states;
    }
}
