import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class ThirteensBoard extends Board {

  private static final int BOARD_SIZE = 10;
  private static final int[] POINT_VALUES =
		{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13};

  public ThirteensBoard() {
    super(BOARD_SIZE, POINT_VALUES);
  }

  /**
	 * Check for a 13-pair in the selected cards.
	 * @param selectedCards selects a subset of this board.  It is list
	 *                      of indexes into this board that are searched
	 *                      to find a 13-pair.
	 * @return true if the board entries in selectedCards
	 *              contain a 13-pair; false otherwise.
	 */
  @Override
  public boolean containsPairSum(List<Integer> selectedCards) {
    for (int i = 0; i < selectedCards.size(); i++) {
      for (int j = i + 1; j <= selectedCards.size(); j++) {
        if (super.cardAt(j).pointValue() + super.cardAt(i).pointValue() == 13) {
          return true;
        }
      }
    }
    return false;
  }

  @Override
  public boolean isLegal(List<Integer> selectedCards) {
    int randomIndex = (int) (Math.random() * selectedCards.size());
    if (
      (super.cardAt(randomIndex).rank().equals("king")) ||
      (selectedCards.size() == 2 && containsPairSum(selectedCards)) ||
      (selectedCards.size() == 3 && super.containsJQK(selectedCards))
    ) {
      return true;
    }
    return false;
  }

  @Override
  public boolean anotherPlayIsPossible() {
    Collection<?> face = new ArrayList<String>(Arrays.asList("jack", "queen", "king"));
    if (
      (
        super.getCards().length == 1 &&
        super.getCards()[0].rank().equals("king")
      ) ||
      (
        super.getCards().length == 2 &&
        super.getCards()[0].pointValue() +
        super.getCards()[1].pointValue() ==
        13
      ) ||
      (
        super.getCards().length == 3 &&
        Arrays
          .asList(super.getCards())
          .containsAll(face)
      )
    ) {
      return true;
    }
    return false;
  }
}
