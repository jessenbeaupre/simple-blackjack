package simpleblackjack.game;

public interface TableView
{
    int visibleDealerScore();
    int visibleCardSum();
    int visibleCardCount();
    Dealer getDealer();
}
