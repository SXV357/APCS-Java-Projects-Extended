import java.io.File;
import java.io.FileNotFoundException;
// import java.io.IOException;
import java.util.Scanner;

public class NBodySimulation
{
	private Body[] bodies;    //stores all the bodies in the simulation
	private int    numBodies; //number of bodies in this simulation
	private double uRadius;   //radius of the universe
	private String fileName;  //file providing the input

	public NBodySimulation(String nameOfFile) throws FileNotFoundException
	{
		this.fileName = nameOfFile;
		Scanner file = new Scanner(new File(fileName));
		while (file.hasNextLine()){
			numBodies = file.nextInt();
			uRadius = file.nextDouble();
			this.bodies = new Body[numBodies];
			for (int j = 0; j < this.bodies.length; j++){
				while (file.hasNext()){
					this.bodies[j] = new Body(file.nextDouble(), file.nextDouble(), file.nextDouble(), file.nextDouble(), file.nextDouble(), file.next());
				}
			}
		}
		initCanvas();
	}

	/** initialize the drawing canvas */
	private void initCanvas()
	{
		StdDraw.setScale(-uRadius, uRadius); //set canvas size / scale
		StdDraw.picture(0, 0, "C:/Users/14058/OneDrive/Desktop/APCS-Projects-Extended/NBodySimulation/Visuals/starfield.jpg"); //paint background
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
		for (int i = 0; i < T; i += dt){
			for (int j = 0; j < bodies.length; j++){
				bodies[j].setNetForce(bodies);
				bodies[j].update(dt);
				StdDraw.picture(0, 0, "C:/Users/14058/OneDrive/Desktop/APCS-Projects-Extended/NBodySimulation/Visuals/starfield.jpg");
				bodies[j].draw();
				StdDraw.show(10); 
			}
		}
	}
}