package simpleblackjack.game;

import java.util.ArrayList;
import java.util.List;

public class Hand
{
    List<Card> cards;

    public Hand(List<Card> cards)
    {
        this.cards = cards;
    }

    public Hand()
    {
        cards = new ArrayList<>();
    }

    public List<Card> getCards()
    {
        return cards;
    }

    public int size()
    {
        return cards.size();
    }

    public void clearHand()
    {
        cards.clear();
    }

    public void addCard(Card card)
    {
        cards.add(card);
    }

    public static int computeScore( List<Card> cards )
    {
        int sum = 0;

        // Sum all of the cards base values.
        for (Card card : cards)
        {
            sum += card.getValue();
        }

        for (Card card : cards)
        {
            // Add 10 for aces if there's space for them.
            if (sum + 10 <= 21 && card.getValue() == 1)
            {
                sum += 10;
            }
        }

        return sum;
    }
}
