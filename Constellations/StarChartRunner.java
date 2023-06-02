import java.io.FileNotFoundException;

public class StarChartRunner {
    public static void main(String[] args) throws FileNotFoundException{
        StarChart chart = new StarChart("C:/Users/14058/OneDrive/Desktop/APCS-Projects-Extended/Constellations/stars.txt");
        chart.drawStars();
        chart.drawConstellations();
    }
}