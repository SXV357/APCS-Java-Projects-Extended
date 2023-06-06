import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.awt.Color;

public class White {
    public White(String geographicData, String electionData, boolean mapType) throws FileNotFoundException{

        Scanner election = new Scanner(new File(electionData));
        String first = election.nextLine();
        HashMap<String, Color> check = new HashMap<String, Color>();
        HashMap<String, Color> purple = new HashMap<String, Color>();
        while (election.hasNextLine()){
            String[] data = election.nextLine().split(",");
            String[] voteSubset = Arrays.copyOfRange(data, 1, data.length);

            double ratioSum = Double.parseDouble(voteSubset[0]) + Double.parseDouble(voteSubset[1]) + Double.parseDouble(voteSubset[2]);
            int red = (int) Math.round((Double.parseDouble(voteSubset[0]) / ratioSum) * 255);
            int green = (int) Math.round((Double.parseDouble(voteSubset[1]) / ratioSum) * 255);
            int blue = (int) Math.round((Double.parseDouble(voteSubset[2]) / ratioSum) * 255);
            purple.put(data[0], new Color(red, green, blue));
            
            Color majority = null;
            if (Integer.parseInt(data[1]) > Integer.parseInt(data[2])) majority = Color.RED;
            else if (Integer.parseInt(data[1]) < Integer.parseInt(data[2])) majority = Color.BLUE;
            check.put(data[0], majority);
        }


        Scanner file = new Scanner(new File(geographicData));
        double minLong = 0.0; double maxLong = 0.0; double minLat = 0.0; double maxLat = 0.0;
        minLong = file.nextDouble(); minLat = file.nextDouble();
        maxLong = file.nextDouble(); maxLat = file.nextDouble();
        StdDraw.setXscale(minLong, maxLong);
        StdDraw.setYscale(minLat, maxLat);
        int numRegions = file.nextInt();
        file.nextLine(); // line break after numRegions
        for (int i = 0; i < numRegions; i++){
            String subRegion = file.next();
            String mainRegion = file.next();
            int numPoints = file.nextInt();
            double[] xCords = new double[numPoints]; double[] yCords = new double[numPoints];
            for (int j = 0; j < numPoints; j++){
                xCords[j] = file.nextDouble();
                yCords[j] = file.nextDouble();
            }
            file.nextLine(); // line break after each region's set of points
            for (String region: check.keySet()){
                if (region.equals(subRegion)){
                    if (mapType) StdDraw.setPenColor(check.get(region));
                    else StdDraw.setPenColor(purple.get(region));
                }
            }
            StdDraw.filledPolygon(xCords, yCords);
        }
    }
}