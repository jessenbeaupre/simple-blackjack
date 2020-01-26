package simpleblackjack.ai;

import simpleblackjack.game.BasicStrategyPlayer;
import simpleblackjack.game.Blackjack;
import simpleblackjack.game.Player;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TrainingSet
{
    List<TrainingPlayer> trainingPlayers = new ArrayList<>();

    public TrainingSet(int numGames)
    {
        for (int i = 0; i < numGames; i++)
        {
        }
    }

    private void simulateGame()
    {
        List<Player> players = new ArrayList<>();

        int otherPlayers = (int) Math.round(Math.random() * 4);

        for (int i = 0; i < otherPlayers; i++)
        {
            players.add(new BasicStrategyPlayer());
        }

        TrainingPlayer training = new TrainingPlayer();
        trainingPlayers.add(training);

        players.add(training);

        // Place the trainer somewhere in the list.
        Collections.shuffle(players);

        Blackjack game = new Blackjack(players);
    }
}
