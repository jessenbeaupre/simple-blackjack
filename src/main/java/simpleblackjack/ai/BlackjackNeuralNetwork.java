package simpleblackjack.ai;

import org.neuroph.core.NeuralNetwork;
import org.neuroph.core.data.DataSet;
import org.neuroph.core.data.DataSetRow;
import org.neuroph.nnet.learning.BackPropagation;

import java.util.List;

public class BlackjackNeuralNetwork
{
    static double normalizeValue = 384;

    public static void trainTest(NeuralNetwork NeuralNetwork)
    {

        //setup data set with input sizes
        int inputSize = 3;
        int outputSize = 1;
        DataSet blackJackDataSet = new DataSet(inputSize, outputSize);

        TrainingSet trainingSet = new TrainingSet(1000);
        List<TrainingState> setStates = trainingSet.getSets();



        for (TrainingState state : setStates)
        {
            blackJackDataSet.addRow(new double[] {state.getPlayerScore() / normalizeValue, state.getVisibleCardCount() / normalizeValue,
                    state.getVisibleCardSum() / normalizeValue}, new double[] {state.getCorrectValue() / normalizeValue});
        }


        //creates back propagation with the data set we created and iterates 2000 times on the neural network
        //BackPropagation backPropagation = new BackPropagation();
        BackPropagation learning = new BackPropagation();
        learning.setMaxIterations(5000000);
        learning.setLearningRate(0.05);
        NeuralNetwork.learn(blackJackDataSet, learning);
        System.out.println("Learning error: " + learning.getTotalNetworkError());
    }

    public static double getNormalizeValue(){return normalizeValue;}
}