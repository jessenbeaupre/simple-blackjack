package simpleblackjack.ai;

public class TrainingState
{
    int visibleCardSum = 0;
    int visibleCardCount = 0;
    int playerScore = 0;
    int dealerCardValue;

    double correctValue = 0.0;

    public TrainingState(int visibleCardSum, int visibleCardCount, int playerScore, int dealerCardValue, double correctValue)
    {
        this.visibleCardSum = visibleCardSum;
        this.visibleCardCount = visibleCardCount;
        this.playerScore = playerScore;
        this.dealerCardValue = dealerCardValue;
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

    public int getDealerCardValue(){return dealerCardValue;}

    public double getCorrectValue()
    {
        return correctValue;
    }
}
