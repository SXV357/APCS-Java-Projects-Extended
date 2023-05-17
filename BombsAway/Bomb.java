import java.awt.Color;

public class Bomb
{
	private static double xLocation;
	private static double yLocation;
	private static double yVelocity;
	private static double radius;
	private static int strength;
	private static boolean isSplitter;

	public Bomb()
	{
		xLocation = Math.random();
		yLocation = 1.0;
		yVelocity = (Math.random() * -0.003) - 0.001;
		radius = (Math.random() * 0.02) + 0.05;
		strength = (int)(Math.random() * 50) + 10;
		isSplitter = Math.random() < 0.1;
	}

	public Bomb(Bomb other, double deltaX)
	{
		xLocation = other.getX() + deltaX;
		yLocation = other.getY();
		yVelocity = other.getYVelocity();
		radius = other.getRadius() / 2.0;
		strength = other.getStrength();
		isSplitter = false;
	}
		
	public void draw()
	{
		int initialStrength = strength;
		int remainingStrength = attack();
		Color bombColor = new Color(255 * (1 - remainingStrength/initialStrength), 0, (int) 255 * remainingStrength/initialStrength);
		StdDraw.setPenColor(bombColor);
		StdDraw.filledCircle(xLocation, yLocation, radius);

	}

	public void updatePos(){yLocation += yVelocity;}
	public boolean isSplitter(){return isSplitter;}
	public double getX(){return xLocation;}
	public double getYVelocity(){return yVelocity;}
	public double getY(){return yLocation;}
	public double getRadius(){return radius;}
	public int getStrength(){return strength;}
 
	public int attack()
	{
		strength--;
		return strength;
	}
	
	public boolean intersects(double mouseX, double mouseY)
	{
		if (Math.sqrt(Math.pow(yLocation - mouseY, 2) + Math.pow(xLocation - mouseX, 2)) <= radius){
			strength--;
			return true;
		}
		return false;
	}
}