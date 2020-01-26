package simpleblackjack.game;

import java.util.ArrayList;
import java.util.List;

public class Card
{
    enum Suit
    {
        spades,
        hearts,
        diamonds,
        clubs
    }

    private int value;
    private Suit suit;

    public Card(int value, Suit suit)
    {
        this.value = value;
        this.suit = suit;
    }

    public int getValue()
    {
        return value;
    }

    public Suit getSuit()
    {
        return suit;
    }
}
