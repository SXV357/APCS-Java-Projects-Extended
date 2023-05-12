import java.util.ArrayList;
import java.util.List;

public abstract class Board {

    private static final String[] RANKS =
		{"ace", "2", "3", "4", "5", "6", "7", "8", "9", "10", "jack", "queen", "king"};
    
    private static final String[] SUITS = {"spades", "hearts", "diamonds", "clubs"};

    private Card[] cards;

    private Deck deck;

    private static final boolean I_AM_DEBUGGING = false;

    public Board(int boardSize, int[] points){
        cards = new Card[boardSize];
        deck = new Deck(RANKS, SUITS, points);
        if (I_AM_DEBUGGING) {
			System.out.println(deck);
			System.out.println("----------");
		}
		dealMyCards();
    }

    public abstract boolean containsPairSum(List<Integer> selectedCards);
    public abstract boolean isLegal(List<Integer> selectedCards);
    public abstract boolean anotherPlayIsPossible();

    /**
	 * Check for a JQK in the selected cards.
	 * @param selectedCards selects a subset of this board.  It is list
	 *                      of indexes into this board that are searched
	 *                      to find a JQK group.
	 * @return true if the board entries in selectedCards
	 *              include a jack, a queen, and a king; false otherwise.
	 */
    public boolean containsJQK(List<Integer> selectedCards) {
		for (int i = 0; i < selectedCards.size() - 2; i++){
			if (cardAt(i).rank().equals("jack")){
				if (cardAt(i+1).rank().equals("queen") && cardAt(i+2).rank().equals("king")){
					return true;
				}
			}
		}
		return false;
	}

    public String[] getRanks(){
        return RANKS;
    }

    public Card[] getCards(){
        return cards;
    }

    public Deck getDeck(){
        return deck;
    }

    public String[] getSuits(){
        return SUITS;
    }
    
    private void dealMyCards() {
		for (int k = 0; k < cards.length; k++) {
			cards[k] = deck.deal();
		}
	}

    // /**
	//  * Start a new game by shuffling the deck and
	//  * dealing some cards to this board.
	//  */
    public void newGame() {
		deck.shuffle();
		dealMyCards();
	}

    // /**
	//  * Accesses the deck's size.
	//  * @return the number of undealt cards left in the deck.
	//  */
    public int deckSize() {
		return deck.size();
	}

    // /**
	//  * Accesses the size of the board.
	//  * Note that this is not the number of cards it contains,
	//  * which will be smaller near the end of a winning game.
	//  * @return the size of the board
	//  */
    public int size() {
		return cards.length;
    }

    // /**
	//  * Accesses a card on the board.
	//  * @return the card at position k on the board.
	//  * @param k is the board position of the card to return.
	//  */
    public Card cardAt(int k) {
		return cards[k];
	}

    // /**
	//  * Replaces selected cards on the board by dealing new cards.
	//  * @param selectedCards is a list of the indices of the
	//  *        cards to be replaced.
	//  */
    public void replaceSelectedCards(List<Integer> selectedCards) {
		for (Integer k : selectedCards) {
			deal(k.intValue());
		}
	}

    /**
	 * Determine whether or not the game has been won,
	 * i.e. neither the board nor the deck has any more cards.
	 * @return true when the current game has been won;
	 *         false otherwise.
	 */
    public boolean gameIsWon() {
		if (deck.isEmpty()) {
			for (Card c : cards) {
				if (c != null) {
					return false;
				}
			}
			return true;
		}
		return false;
	}

    /**
	 * Gets the indexes of the actual (non-null) cards on the board.
	 *
	 * @return a List that contains the locations (indexes)
	 *         of the non-null entries on the board.
	 */
    public List<Integer> cardIndexes() { 
		List<Integer> cardIndexes = new ArrayList<>();
		for (int i = 0; i < this.cards.length; i++){
			if (cards[i] != null){
				cardIndexes.add(i);
			}
		}
		return cardIndexes;
	}

    // /**
	//  * Deal a card to the kth position in this board.
	//  * If the deck is empty, the kth card is set to null.
	//  * @param k the index of the card to be dealt.
	//  */
    public void deal(int k) {
		cards[k] = deck.deal();
	}

    // /**
	//  * Determines if the board is empty (has no cards).
	//  * @return true if this board is empty; false otherwise.
	//  */
    public boolean isEmpty() {
		for (int k = 0; k < cards.length; k++) {
			if (cards[k] != null) {
				return false;
			}
		}
		return true;
	}

    /**
	 * Generates and returns a string representation of this board.
	 * @return the string version of this board.
	 */
    public String toString() {
		String s = "";
		for (int k = 0; k < cards.length; k++) {
			s = s + k + ": " + cards[k] + "\n";
		}
		return s;
	}
}
