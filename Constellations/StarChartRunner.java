import java.io.FileNotFoundException;

public class StarChartRunner {

    static final int SIZE = 512;

    public static void main(String[] args) throws FileNotFoundException{
        StdDraw.setCanvasSize(SIZE, SIZE);
        StdDraw.setXscale(0, SIZE);
        StdDraw.setYscale(0, SIZE); 
        StarChart chart = new StarChart("C:/Users/14058/OneDrive/Desktop/APCS-Projects-Extended/Constellations/stars.txt");
        chart.drawStars();
        chart.drawConstellation("C:/Users/14058/OneDrive/Desktop/APCS-Projects-Extended/Constellations/Constellation Files/BigDipper.txt");
    }
}