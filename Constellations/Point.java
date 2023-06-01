public class Point {
    
    private double x;
    private double y;

    public Point(double x, double y){
        this.x = x;
        this.y = y;
    }

    public double getX(){return this.x;}
    public double getY(){return this.y;}

    public String toString(){
        return "X-Location: " + this.x + ", " + "Y-Location: " + this.y;
    }
}
