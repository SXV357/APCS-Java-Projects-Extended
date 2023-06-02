import java.awt.Color;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.ArrayList;

public class StarChart {

  private ArrayList<Star> stars;
  private HashMap<Integer, Point> identityToLocation;
  private HashMap<ArrayList<String>, Integer> namesToIdentity;
  static final int SIZE = 512;

  public StarChart(String fileName) throws FileNotFoundException {
    StdDraw.setCanvasSize(SIZE, SIZE);
    StdDraw.setXscale(0, SIZE);
    StdDraw.setYscale(0, SIZE);
    this.stars = new ArrayList<Star>();
    this.identityToLocation = new HashMap<Integer, Point>();
    this.namesToIdentity = new HashMap<ArrayList<String>, Integer>();
    Scanner file = new Scanner(new File(fileName));
    while (file.hasNextLine()) {
      String[] arr = file.nextLine().split(" ");
      double xCord = Double.parseDouble(arr[0]);
      double yCord = Double.parseDouble(arr[1]);
      int henryDraper = Integer.parseInt(arr[3]);
      double magnitude = Double.parseDouble(arr[4]);
      ArrayList<String> starNames = new ArrayList<String>();
      if (arr.length >= 7) {
        String[] modifiedArr = Arrays.copyOfRange(arr, 6, arr.length);
        for (int i = 0; i < modifiedArr.length; i++) {
          modifiedArr[i] = modifiedArr[i].replace(";", "");
        }
        for (int j = 0; j < modifiedArr.length; j++) {
          starNames.add(modifiedArr[j]);
        }
      }
      this.stars.add(new Star(xCord, yCord, henryDraper, magnitude, starNames));
    }
    populateMaps();
  }

  void populateMaps() {
    for (int i = 0; i < this.stars.size(); i++) {
      this.identityToLocation.put(
          this.stars.get(i).getHenryDraper(),
          new Point(this.stars.get(i).getXCord(), this.stars.get(i).getYCord())
        );
      this.namesToIdentity.put(
          this.stars.get(i).getNames(),
          this.stars.get(i).getHenryDraper()
        );
    }
  }

  public Point coordsToPixel(double origX, double origY, int size) {
    double newX = (origX + 1.0) * (size / 2.0);
    double newY = (origY + 1.0) * (size / 2.0);
    return new Point(newX, newY);
  }

  void drawStars() {
    StdDraw.setPenColor(Color.BLACK);
    StdDraw.filledSquare(SIZE / 2, SIZE / 2, SIZE / 2);
    StdDraw.setPenColor(Color.WHITE);
    for (int i = 0; i < this.stars.size(); i++) {
      double radius = Math.round(10.0 / (this.stars.get(i).getMagnitude() + 2));
      double newX =
        this.coordsToPixel(
            this.stars.get(i).getXCord(),
            this.stars.get(i).getYCord(),
            SIZE
          )
          .getX();
      double newY =
        this.coordsToPixel(
            this.stars.get(i).getXCord(),
            this.stars.get(i).getYCord(),
            SIZE
          )
          .getY();
      StdDraw.filledCircle(newX, newY, radius);
    }
  }

  void drawConstellations() throws FileNotFoundException {
    StdDraw.setPenColor(Color.YELLOW);
    File[] files = {
      new File(
        "C:/Users/14058/OneDrive/Desktop/APCS-Projects-Extended/Constellations/Constellation Files/BigDipper.txt"
      ),
      new File(
        "C:/Users/14058/OneDrive/Desktop/APCS-Projects-Extended/Constellations/Constellation Files/Bootes.txt"
      ),
      new File(
        "C:/Users/14058/OneDrive/Desktop/APCS-Projects-Extended/Constellations/Constellation Files/Cassiopeia.txt"
      ),
      new File(
        "C:/Users/14058/OneDrive/Desktop/APCS-Projects-Extended/Constellations/Constellation Files/Cygnet.txt"
      ),
      new File(
        "C:/Users/14058/OneDrive/Desktop/APCS-Projects-Extended/Constellations/Constellation Files/Gemini.txt"
      ),
      new File(
        "C:/Users/14058/OneDrive/Desktop/APCS-Projects-Extended/Constellations/Constellation Files/Hydra.txt"
      ),
      new File(
        "C:/Users/14058/OneDrive/Desktop/APCS-Projects-Extended/Constellations/Constellation Files/UrsaMajor.txt"
      ),
      new File(
        "C:/Users/14058/OneDrive/Desktop/APCS-Projects-Extended/Constellations/Constellation Files/UrsaMinor.txt"
      ),
    };
    for (File f : files) {
      Scanner file = new Scanner(f);
      double sourceX = 0.0;
      double sourceY = 0.0;
      double destinationX = 0.0;
      double destinationY = 0.0;
      while (file.hasNextLine()) {
        String[] curr = file.nextLine().split(",");
        String starOne = curr[0];
        String starTwo = curr[1];
        for (int i = 0; i < curr.length; i++) {
          for (int j = 0; j < stars.size(); j++) {
            if (stars.get(j).getNames().size() >= 1) {
              ArrayList<String> names = stars.get(j).getNames();
              for (int k = 0; k < names.size(); k++) {
                if (names.get(k).equals(starOne)) {
                  sourceX =
                    identityToLocation.get(namesToIdentity.get(names)).getX();
                  sourceY =
                    identityToLocation.get(namesToIdentity.get(names)).getY();
                } else if (names.get(k).equals(starTwo)) {
                  destinationX =
                    identityToLocation.get(namesToIdentity.get(names)).getX();
                  destinationY =
                    identityToLocation.get(namesToIdentity.get(names)).getY();
                }
              }
            }
          }
        }
        StdDraw.line(sourceX, sourceY, destinationX, destinationY);
      }
    }
  }
}
