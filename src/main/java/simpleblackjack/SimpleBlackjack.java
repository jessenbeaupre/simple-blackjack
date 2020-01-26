package simpleblackjack;

import org.neuroph.core.NeuralNetwork;
import org.neuroph.nnet.MultiLayerPerceptron;
import org.neuroph.util.TransferFunctionType;
import simpleblackjack.ai.AiPlayer;
import simpleblackjack.ai.BlackjackNeuralNetwork;
import simpleblackjack.ai.TrainingSet;
import simpleblackjack.game.BasicStrategyPlayer;
import simpleblackjack.game.Blackjack;
import simpleblackjack.game.Player;

import java.util.ArrayList;
import java.util.List;

public class SimpleBlackjack {

    public static void main(String args[])
    {
        runBasicGames();
        runAiGames();
    }

    private static void runBasicGames()
    {
        List<Player> playerList = new ArrayList<>();

        playerList.add(new BasicStrategyPlayer());
        playerList.add(new BasicStrategyPlayer());
        playerList.add(new BasicStrategyPlayer());
        playerList.add(new BasicStrategyPlayer());
        playerList.add(new BasicStrategyPlayer());

        Blackjack game = new Blackjack(playerList, false);

        for (int i = 0; i < 1000; i++)
        {
            game.playHand();
        }

        double winPercent = game.getWinRatio() * 100;
        System.out.println("Basic strategy players won " + winPercent + "%");
    }

    private static void runAiGames()
    {
        NeuralNetwork bJNN = new MultiLayerPerceptron(TransferFunctionType.TANH, 3, 3, 1);
        BlackjackNeuralNetwork.trainTest(bJNN);

        List<Player> playerList = new ArrayList<>();

        playerList.add(new AiPlayer(bJNN));
        playerList.add(new AiPlayer(bJNN));
        playerList.add(new AiPlayer(bJNN));
        playerList.add(new AiPlayer(bJNN));
        playerList.add(new AiPlayer(bJNN));

        Blackjack aiGame = new Blackjack(playerList, false);

        for (int i = 0; i < 1000; i++)
        {
            aiGame.playHand();
        }

        double winPercent = aiGame.getWinRatio() * 100;
        System.out.println("AI players won " + winPercent + "%");
    }
}
