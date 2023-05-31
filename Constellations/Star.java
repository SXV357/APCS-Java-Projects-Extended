import java.util.ArrayList;

public class Star {
    private double xCord;
    private double yCord;
    private int HenryDraper;
    private double magnitude;
    private ArrayList<String> names;

    public Star(double xCord, double yCord, int HenryDraper, double magnitude, ArrayList<String> names){
        this.xCord = xCord;
        this.yCord = yCord;
        this.HenryDraper = HenryDraper;
        this.magnitude = magnitude;
        this.names = names;
    }

    public double getXCord(){return this.xCord;}
    public double getYCord(){return this.yCord;}
    public int getHenryDraper(){return this.HenryDraper;}
    public double getMagnitude(){return this.magnitude;}
    public ArrayList<String> getNames(){return this.names;}

    public String toString(){
        String description = null;
        String representation = null;
        if (this.names.size() > 0){
            if (this.names.size() == 1){
                description = "Name: ";
                representation = this.names.get(0);
            }
            else{
                description = "Names: ";
                representation = this.names.toString();
            }
        }
        else {
            description = "Name/Names: ";
            representation = "None";
        }
        return "X-coordinate: " + this.xCord + ", " + "Y-coordinate: " + this.yCord + ", " + 
        "Henry Draper Number: " + this.HenryDraper + ", " + "Magnitude: " + this.magnitude + ", " 
        + (description != null && representation != null ? description + representation: "");
    }
}
