import java.awt.Color;
import java.util.*;

public class Block {

  private GridDisplay display;
  private Color color;
  private Location[] locs;

  public Block(GridDisplay disp) {
    this.display = disp;
    Random rand = new Random();
    int rnd = rand.nextInt(3);
    switch (rnd) {
      case 0:
        locs =
          new Location[] {
            new Location(0, 4),
            new Location(0, 3),
            new Location(0, 5),
          };
        this.color = Color.MAGENTA;
        break;
      case 1:
        locs =
          new Location[] {
            new Location(0, 4),
            new Location(0, 5),
            new Location(1, 4),
            new Location(1, 5),
          };
        this.color = Color.YELLOW;
        break;
      case 2:
        locs =
          new Location[] {
            new Location(0, 4),
            new Location(0, 3),
            new Location(0, 5),
            new Location(1, 4),
          };
        this.color = Color.CYAN;
        break;
    }
    drawSelf(this.color);
  }

  public Location[] getLocs(){return this.locs;}

  public void drawSelf(Color col) {
    for (Location loc : this.locs) {
      this.display.setColor(loc, col);
    }
  }

  /** check potential new locations to see if this block has a valid move there */
  public boolean areValidAndEmpty(Location[] newLocs) {
    for (Location loc : newLocs) {
      if (
        !this.display.isValid(loc) ||
        !this.display.getColor(loc).equals(Color.BLACK)
      ) {
        return false;
      }
    }
    return true;
  }

  public boolean shift(int deltaRow, int deltaCol) {
    drawSelf(Color.BLACK);
    Location[] newLocs = new Location[this.locs.length];
    for (int i = 0; i < newLocs.length; i++) {
      int modifiedRow = this.locs[i].getRow() + deltaRow;
      int modifiedCol = this.locs[i].getCol() + deltaCol;
      newLocs[i] = new Location(modifiedRow, modifiedCol);
    }
    if (areValidAndEmpty(newLocs)) {
      this.locs = newLocs;
      drawSelf(this.color);
      return true;
    }
    drawSelf(this.color);
    return false;
  }

  public void rotate() {
    drawSelf(Color.BLACK);
    Location[] newRotatedLocs = new Location[this.locs.length];
    newRotatedLocs[0] = this.locs[0];
    int rowZero = this.locs[0].getRow();
    int colZero = this.locs[0].getCol();
    for (int j = 1; j < newRotatedLocs.length; j++) {
      int newRow = rowZero + locs[j].getCol() - colZero;
      int newCol = colZero + rowZero - locs[j].getRow();
      newRotatedLocs[j] = new Location(newRow, newCol);
    }
    if (areValidAndEmpty(newRotatedLocs)) {
      this.locs = newRotatedLocs;
      drawSelf(this.color);
    }
    drawSelf(this.color);
  }
}
