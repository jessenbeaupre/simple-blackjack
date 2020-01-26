package simpleblackjack.ai;

public class TrainingState
{
    int visibleCardSum = 0;
    int visibleCardCount = 0;
    int playerScore = 0;

    double correctValue = 0.0;

    public TrainingState(int visibleCardSum, int visibleCardCount, int playerScore, double correctValue)
    {
        this.visibleCardSum = visibleCardSum;
        this.visibleCardCount = visibleCardCount;
        this.playerScore = playerScore;
        this.correctValue = correctValue;
    }

    public int getVisibleCardSum()
    {
        return visibleCardSum;
    }

    public int getVisibleCardCount()
    {
        return visibleCardCount;
    }

    public int getPlayerScore()
    {
        return playerScore;
    }

    public double getCorrectValue()
    {
        return correctValue;
    }
}
