package simpleblackjack.game;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Deck
{
    List<Card> cards = new ArrayList<>();

    public Deck()
    {
        for (Card.Suit suit : Card.Suit.values())
        {
            for (int i = 1; i <= 13; i++)
            {
                cards.add(new Card(i, suit));
            }
        }
    }

    public void shuffle()
    {
        Collections.shuffle(cards);
    }

    public Hand deal(int handSize)
    {
        List<Card> cards = new ArrayList<>();

        for (int i = 0; i < handSize; i++)
        {
           cards.add(deal());
        }

        return new Hand(cards);
    }

    public Card deal()
    {
        return cards.remove(0);
    }

    public void returnHand(Hand hand)
    {
        for ( Card card : hand.getCards()) {
            cards.add(card);
        }

        hand.clearHand();
    }
}
