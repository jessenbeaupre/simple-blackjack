package simpleblackjack.ai;

import simpleblackjack.game.BasicStrategyPlayer;
import simpleblackjack.game.Blackjack;
import simpleblackjack.game.Player;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TrainingSet
{
    //List<TrainingPlayer> trainingPlayers = new ArrayList<>();
    TrainingPlayer training = new TrainingPlayer();

    public TrainingSet(int numGames)
    {
        for (int i = 0; i < numGames; i++)
        {
            simulateGame();
        }
    }

    private void simulateGame()
    {
        List<Player> players = new ArrayList<>();

        for (int i = 0; i < 4; i++)
        {
            players.add(new BasicStrategyPlayer());
        }

        //trainingPlayers.add(training);

        players.add(training);

        // Place the trainer somewhere in the list.
        Collections.shuffle(players);

        Blackjack game = new Blackjack(players, false);
        game.playHand();

    }

    public List<TrainingState> getSets(){return training.getStates();}
}
