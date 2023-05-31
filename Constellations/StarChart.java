import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.*;

public class StarChart {
    private ArrayList<Star> stars;

    public StarChart(String fileName) throws FileNotFoundException{
        this.stars = new ArrayList<Star>();
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
    }
}
