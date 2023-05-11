import java.util.*;

public class Blackjack
{
    public static ArrayList<Card> makeDeck()
    {
        ArrayList<Card> cards = new ArrayList<Card>();
        for (int rank = 1; rank <= 13; rank = rank + 1)
        {
            for (int suit = 0; suit <= 3; suit = suit + 1)
            {
                Card card = new Card(rank, suit);
                cards.add(card);
            }
        }

        return cards;
    }

    public static void swap(ArrayList<Card> cards, int i, int j)
    {
    	Card temp = cards.get(i);
        cards.set(i, cards.get(j));
        cards.set(j, temp);
    }

    public static void dealOneCard(ArrayList<Card> deck, ArrayList<Card> hand)
    {
    	int randomIndex = (int) Math.floor(Math.random() * deck.size());
        Card randomCard = deck.get(randomIndex);
        deck.remove(randomIndex);
        hand.add(randomCard);
    }

    public static int getScore(ArrayList<Card> hand)
    {
    	int score = 0;
        for (int i = 0; i < hand.size(); i++){
            if (hand.get(i).getRank() == 11 || hand.get(i).getRank() == 12 || hand.get(i).getRank() == 13){
                score += 10;
            }
            else{
                score += hand.get(i).getRank();
            }
        }
    	
        return score;
    }
}