import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.*;
import java.awt.Color;

public class StarChart {
    private ArrayList<Star> stars;
    private HashMap<Integer, Point> identityToLocation;
    private HashMap<ArrayList<String>, Integer> namesToIdentity;

    public StarChart(String fileName) throws FileNotFoundException{
        this.stars = new ArrayList<Star>();
        this.identityToLocation = new HashMap<Integer, Point>();
        this.namesToIdentity = new HashMap<ArrayList<String>, Integer>();
        Scanner file = new Scanner(new File(fileName));
        while (file.hasNextLine()){
            String[] arr = file.nextLine().split(" ");
            double xCord = Double.parseDouble(arr[0]);
            double yCord = Double.parseDouble(arr[1]);
            int henryDraper = Integer.parseInt(arr[3]);
            double magnitude = Double.parseDouble(arr[4]);
            ArrayList<String> starNames = new ArrayList<String>();
            if (arr.length >= 7){
                String[] modifiedArr = Arrays.copyOfRange(arr, 6, arr.length);
                for (int i = 0; i < modifiedArr.length; i++){
                    modifiedArr[i] = modifiedArr[i].replace(";", "");
                }
                for (int j = 0; j < modifiedArr.length; j++){
                    starNames.add(modifiedArr[j]);
                }
            }
            this.stars.add(new Star(xCord, yCord, henryDraper, magnitude, starNames));
        }
        // populateMaps();
    }

    void populateMaps(){
        for (int i = 0; i < this.stars.size(); i++){
            this.identityToLocation.put(this.stars.get(i).getHenryDraper(), new Point(this.stars.get(i).getXCord(), this.stars.get(i).getYCord()));
            this.namesToIdentity.put(this.stars.get(i).getNames(), this.stars.get(i).getHenryDraper());
        }
        System.out.println(namesToIdentity);
    }

    public Point coordsToPixel(double origX, double origY, int size){
       double newX = (origX + 1.0) * (size / 2.0);
       double newY = (origY + 1.0) * (size / 2.0);
       return new Point(newX, newY);
    }

    void drawStars(){
        StdDraw.setPenColor(Color.BLACK);
        StdDraw.filledSquare(256, 256, 256);
        StdDraw.setPenColor(Color.WHITE);
        for (int i = 0; i < this.stars.size(); i++){
            double radius = Math.round(10.0 / (this.stars.get(i).getMagnitude() + 2));
            double newX = this.coordsToPixel(this.stars.get(i).getXCord(), this.stars.get(i).getYCord(), 512).getX();
            double newY = this.coordsToPixel(this.stars.get(i).getXCord(), this.stars.get(i).getYCord(), 512).getY();
            StdDraw.filledCircle(newX, newY, radius);
        }
    }

    void drawConstellation(String fileName) throws FileNotFoundException{
        StdDraw.setPenColor(Color.YELLOW);
        Scanner file = new Scanner(new File(fileName));
        while (file.hasNextLine()){
            String[] curr = file.nextLine().split(",");
            // get location of 1st star and draw a line from it to 2nd star
            // double firstX = 0.0; double firstY = 0.0; double secondX = 0.0; double secondY = 0.0;
            // for (int i = 0; i < stars.size(); i++){
            //     ArrayList<String> n = stars.get(i).getNames();
            //     if (n.size() > 0 && n.size() == 1){
            //         if (n.get(0).equals(curr[0])){
            //             firstX = stars.get(i).getXCord();
            //             firstY = stars.get(i).getYCord();
            //         }
            //         if (n.get(0).equals(curr[1])){
            //             secondX = stars.get(i).getXCord();
            //             secondY = stars.get(i).getYCord();
            //         }
            //     }
            // }
            // StdDraw.line(firstX, firstY, secondX, secondY);
        }
    }
}
