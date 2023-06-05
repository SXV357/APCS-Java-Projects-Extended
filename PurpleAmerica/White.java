import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class White {
    public White(String fileName) throws FileNotFoundException{
        Scanner file = new Scanner(new File(fileName));
        double minLong = 0.0; double maxLong = 0.0; double minLat = 0.0; double maxLat = 0.0;
        minLong = file.nextDouble(); minLat = file.nextDouble();
        maxLong = file.nextDouble(); maxLat = file.nextDouble();
        StdDraw.setXscale(minLong, maxLong);
        StdDraw.setYscale(minLat, maxLat);
    }
}
