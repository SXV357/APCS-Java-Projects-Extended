import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class NBodySimulation
{
	private Body[] bodies;    //stores all the bodies in the simulation
	private int numBodies; //number of bodies in this simulation
	private double uRadius;   //radius of the universe
	private String fileName;  //file providing the input

	public NBodySimulation(String nameOfFile) throws FileNotFoundException
	{
		this.fileName = nameOfFile;
		Scanner file = new Scanner(new File(fileName));
		numBodies = file.nextInt();
		uRadius = file.nextDouble();
		this.bodies = new Body[numBodies];
		int idx = 0;
		while (file.hasNextLine() && idx < this.numBodies){
			String line = file.nextLine();
			String[] values = line.split("\\s+");
			if (values.length >= 6){
				double x = Double.parseDouble(values[0]);
				double y = Double.parseDouble(values[1]);
				double xVel = Double.parseDouble(values[2]);
				double yVel = Double.parseDouble(values[3]);
				double mass = Double.parseDouble(values[4]);
				String img = values[5] + " " + values[6];
				this.bodies[idx] = new Body(x, y, xVel, yVel, mass, img);
				idx++;
			}
		}
		initCanvas();
	}

	/** initialize the drawing canvas */
	private void initCanvas()
	{
		StdDraw.setScale(-uRadius, uRadius); //set canvas size / scale
		StdDraw.picture(0, 0, "/Users/shreyasviswanathan/Desktop/Programming/Java Projects/APCS-Java-Projects-Extended/NBodySimulation/Visuals/starfield.jpg"); //paint background
		for (Body body : bodies)
			body.draw();
	}

	/**
	 * run the n-bodies simulation
	 * @param T total time to run the simulation, in seconds
	 * @param dt time step, in seconds
	 */
	public void run(double T, double dt)
	{
		for (double time = 0; time < T; time += dt){
			for (Body body : bodies){
				body.setNetForce(bodies);
				body.update(dt);
			}
			StdDraw.picture(0, 0, "/Users/shreyasviswanathan/Desktop/Programming/Java Projects/APCS-Java-Projects-Extended/NBodySimulation/Visuals/starfield.jpg");
			for (Body body : bodies){
				body.draw();
			}
			StdDraw.show(10);
		}
	}
}