import java.awt.Color;

public class Game {

  static final int STARTING_SPEED = 50;

  private GridDisplay display; //GUI class
  private Block block; //current block on screen, controlled by player
  private int speed; //sleep duration, for controlling game speed
  private int score;

  /** make a new game starting at the default speed */
  public Game() {
    this(STARTING_SPEED);
    this.score = 0;
  }

  public Game(int speed) {
	this.speed = speed;
    this.display = new GridDisplay(20, 10);
    this.block = new Block(display);
    this.display.setTitle("Tetris - Score: " + this.score);
    this.display.setLineColor(Color.BLACK);
  }

  public void play() {
    while (true) {
      for (int i = 0; i < 10; i++) { //loop to process 10 key presses in 10*speed (0.5 seconds at default speed)
        display.pause(speed);

        int key = display.checkLastKeyPressed();

        if (key == 32) { // space bar
          while (this.block.shift(1, 0)) {
            display.pause(10);
          }
        }

        if (key == 37) { // left arrow
          this.block.shift(0, -1);
        } else if (key == 38) { // up arrow
          this.block.rotate();
        } else if (key == 39) { // right arrow
          this.block.shift(0, 1);
        } else if (key == 40) { // down arrow
          this.block.shift(1, 0);
        }
      }
      if (!checkGameOver()) {
        boolean res = this.block.shift(1, 0);
        if (!res) {
          removeCompletedRows();
          this.block = new Block(this.display);
        }
      } else {
        this.display.showMessageDialog(
            "Game over. You scored " + this.score + " points"
          );
        break;
      }
    }
  }

  public boolean checkGameOver() {
	for (int i = 0; i < this.display.getNumCols(); i++){
		int count = 0;
		for (int j = 0; j < this.display.getNumRows(); j++){
			if (!(this.display.getColor(new Location(j, i)).equals(Color.BLACK))){
				count++;
			}
		}
		if (!(this.block.areValidAndEmpty(this.block.getLocs())) && count == this.display.getNumRows()){
			return true;
		}
	}
	return false;
  }

  public boolean isCompletedRow(int row) {
    for (int i = 0; i < this.display.getNumCols(); i++) {
      if (this.display.getColor(new Location(row, i)).equals(Color.BLACK)) {
        return false;
      }
    }
    return true;
  }

  public void removeSquare(Location loc) {
    for (int i = loc.getRow(); i > 0; i--) {
      this.display.setColor(
          new Location(i, loc.getCol()),
          this.display.getColor(new Location(i - 1, loc.getCol()))
        );
    }
    this.display.setColor(new Location(0, loc.getCol()), Color.BLACK);
  }

  public void removeRow(int row) {
    for (int j = 0; j < this.display.getNumCols(); j++) {
      removeSquare(new Location(row, j));
    }
  }

  public void removeCompletedRows() {
    for (int k = 0; k < this.display.getNumRows(); k++) {
      if (isCompletedRow(k)) {
        this.score++;
        removeRow(k);
      }
    }
  }
}
