package simpleblackjack;

import org.neuroph.core.NeuralNetwork;
import org.neuroph.nnet.MultiLayerPerceptron;
import org.neuroph.util.TransferFunctionType;
import simpleblackjack.ai.AiPlayer;
import simpleblackjack.ai.BlackjackNeuralNetwork;
import simpleblackjack.ai.TrainingSet;
import simpleblackjack.game.Blackjack;
import simpleblackjack.game.Player;

import java.util.ArrayList;
import java.util.List;

public class SimpleBlackjack {

    public static void main(String args[])
    {
        NeuralNetwork bJNN = new MultiLayerPerceptron(TransferFunctionType.TANH, 3, 4, 1);
        BlackjackNeuralNetwork.trainTest(bJNN);

        AiPlayer ai1 = new AiPlayer(bJNN);
        AiPlayer ai2 = new AiPlayer(bJNN);


        List<Player> playerList = new ArrayList<>();

        playerList.add(ai1);
        playerList.add(ai2);

        Blackjack aiGame = new Blackjack(playerList);

        System.out.println("STARING AI:");

        for (int i = 0; i < 10; i++)
        {
            aiGame.playHand();
        }
    }
}
