import java.util.List;
import java.util.Arrays;

/**
 * The ElevensBoard class represents the board in a game of Elevens.
 */
public class ElevensBoard extends Board {

	/**
	 * The size (number of cards) on the board.
	 */
	private static final int BOARD_SIZE = 9;

	/**
	 * The values of the cards for this game to be sent to the deck.
	 */
	private static final int[] POINT_VALUES =
		{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 0, 0, 0};


	/**
	 * Creates a new <code>ElevensBoard</code> instance.
	 */
	public ElevensBoard() {
		super(BOARD_SIZE, POINT_VALUES);
	}

	/**
	 * Determines if the selected cards form a valid group for removal.
	 * In Elevens, the legal groups are (1) a pair of non-face cards
	 * whose values add to 11, and (2) a group of three cards consisting of
	 * a jack, a queen, and a king in some order.
	 * @param selectedCards the list of the indices of the selected cards.
	 * @return true if the selected cards form a valid group for removal;
	 *         false otherwise.
	 */
	public boolean isLegal(List<Integer> selectedCards) {
		if ((selectedCards.size() == 2 && containsPairSum(selectedCards)) || (selectedCards.size() == 3 && super.containsJQK(selectedCards))){
			return true;
		}
		return false;
	}

	/**
	 * Determine if there are any legal plays left on the board.
	 * In Elevens, there is a legal play if the board contains
	 * (1) a pair of non-face cards whose values add to 11, or (2) a group
	 * of three cards consisting of a jack, a queen, and a king in some order.
	 * @return true if there is a legal play left on the board;
	 *         false otherwise.
	 */
	public boolean anotherPlayIsPossible() {
		if ((super.getCards().length == 2 && super.getCards()[0].pointValue() + super.getCards()[1].pointValue() == 11) || (super.getCards().length== 3 && Arrays.asList(super.getCards()).containsAll(Arrays.asList("jack", "queen", "king")))){
			return true;
		}
		return false;
	}

	/**
	 * Check for an 11-pair in the selected cards.
	 * @param selectedCards selects a subset of this board.  It is list
	 *                      of indexes into this board that are searched
	 *                      to find an 11-pair.
	 * @return true if the board entries in selectedCards
	 *              contain an 11-pair; false otherwise.
	 */
	public boolean containsPairSum(List<Integer> selectedCards) {
		for (int i = 0; i < selectedCards.size(); i++){
			for (int j = i + 1; j <= selectedCards.size(); j++){
				if (super.cardAt(j).pointValue() + super.cardAt(i).pointValue() == 11){
					return true;
				}
			}
		}
		return false;
	}
}
