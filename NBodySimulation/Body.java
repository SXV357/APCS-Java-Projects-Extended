public class Body
{
	public static final double G = 6.67E-11;  //Newtons' gravitational constant

	private double x, y; //planet's x and y coordinate position
	private double xVelocity, yVelocity;
	private double mass;
	private double xNetForce, yNetForce; //net forces action on this planet
	private double xAccel, yAccel;
	private String image; //image of this planet (for drawing)

	/*
	 * TODO - student written methods here
	 */

	/** calculates / sets the net force exerted on this body by all the (input) bodies */
	public void setNetForce(Body[] bodies)
	{
		//TODO
	}

	/** updates this body's accel, vel, and position, given the time step */
	public void update(double dt)
	{
		//TODO
	}

	/** Draws the body using the StdDraw library file's drawing method */
	public void draw()
	{
		StdDraw.picture(x, y, image);
	}
}