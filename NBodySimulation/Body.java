public class Body {

  public static final double G = 6.67E-11; //Newtons' gravitational constant

  private double x, y; //planet's x and y coordinate position
  private double xVelocity, yVelocity;
  private double mass;
  private double xNetForce, yNetForce; //net forces action on this planet
  private double xAccel, yAccel;
  private String image; //image of this planet (for drawing)

  public Body(double x, double y, double xVelocity, double yVelocity, double mass, String image) {
    this.x = x;
    this.y = y;
    this.xVelocity = xVelocity;
    this.yVelocity = yVelocity;
    this.mass = mass;
    this.image = image;
    this.xNetForce = 0;
    this.yNetForce = 0;
    this.xAccel = 0;
    this.yAccel = 0;
  }

  public String toString(){
    return "X-coordinate: " + this.x + ", Y-Coordinate: " + this.y + ", X-Velocity: " + this.xVelocity + ", Y-Velocity: " + this.yVelocity + ", Mass: " + this.mass + ", Image File Path: " + this.image;
  }

  public double getX(){return this.x;}
  public double getY(){return this.y;}
  public double getMass(){return this.mass;}

  public double getDistance(Body other){
	return Math.sqrt(Math.pow(other.getY() - this.y, 2) + Math.pow(other.getX() - this.x, 2));
  }

  public double getPairwiseForce(Body other){
	return (G * this.mass * other.getMass()) / Math.pow(getDistance(other), 2);
  }

  public double getPairwiseForceX(Body other){
	double dx = other.getX() - this.x;
  double dy = other.getY() - this.y;
  double theta = Math.atan2(dy, dx);
  return getPairwiseForce(other) * Math.cos(theta);
  }
  public double getPairwiseForceY(Body other){
  double dx = other.getX() - this.x;
  double dy = other.getY() - this.y;
  double theta = Math.atan2(dy, dx);
  return getPairwiseForce(other) * Math.sin(theta);
  }

  /** calculates / sets the net force exerted on this body by all the (input) bodies */
  public void setNetForce(Body[] bodies) {
  this.xNetForce = 0;
  this.yNetForce = 0;
  for (int i = 0; i < bodies.length; i++) {
    if (bodies[i] != this) {
      this.xNetForce += getPairwiseForceX(bodies[i]);
      this.yNetForce += getPairwiseForceY(bodies[i]);
    }
  }
}

  /** updates this body's accel, vel, and position, given the time step */
  public void update(double dt) {
    this.xAccel = this.xNetForce / this.mass;
    this.yAccel = this.yNetForce / this.mass;
    this.xVelocity += this.xAccel * dt;
    this.yVelocity += this.yAccel *  dt;
    this.x += this.xVelocity * dt;
    this.y += this.yVelocity * dt;
  }

  /** Draws the body using the StdDraw library file's drawing method */
  public void draw() {
    StdDraw.picture(x, y, image);
  }
}

class Test{
	public static void main(String[] args){
		new Body(0.5, 0.5, 0, 0, 0, "/Users/shreyasviswanathan/Desktop/Programming/Java Projects/APCS-Java-Projects-Extended/NBodySimulation/Visuals/earth.gif").draw();
		System.out.println(new Body(0.5, 0.5, 0, 0, 6E24, "").getDistance(new Body(1, 1, 0, 0, 6E24, "")));
    Body b1 = new Body(0, 0, 0, 0, 6e24, "");
    Body b2 = new Body(1, 1, 0, 0, 7e24, "");
    System.out.println(b1.getPairwiseForce(b2));
    System.out.println(b1.getPairwiseForceX(b2));
    System.out.println(b1.getPairwiseForceY(b2));
	}
}
