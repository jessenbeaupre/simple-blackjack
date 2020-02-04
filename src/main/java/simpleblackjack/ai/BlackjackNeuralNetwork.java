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
        int inputSize = 4;
        int outputSize = 1;
        DataSet blackJackDataSet = new DataSet(inputSize, outputSize);

        TrainingSet trainingSet = new TrainingSet(20000);
        List<TrainingState> setStates = trainingSet.getSets();



        for (TrainingState state : setStates)
        {
            blackJackDataSet.addRow(new double[]
                    {
                            (double)state.getPlayerScore() / 31.0,
                            (double)state.getVisibleCardCount() / 52.0,
                            (double)state.getVisibleCardSum() / 156.0,
                            (double)state.getDealerCardValue()
                    }, new double[] {state.getCorrectValue()});
        }


        //creates back propagation with the data set we created and iterates 2000 times on the neural network
        //BackPropagation backPropagation = new BackPropagation();
        BackPropagation learning = new BackPropagation();
        learning.setMaxIterations(20000);
        learning.setLearningRate(0.1);
        NeuralNetwork.learn(blackJackDataSet, learning);
        System.out.println("Learning error: " + learning.getTotalNetworkError());
    }

    public static double getNormalizeValue(){return normalizeValue;}
}