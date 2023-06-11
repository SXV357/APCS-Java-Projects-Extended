import java.io.FileNotFoundException;

public class NBodyRunner 
{
	public static void main(String[] args) throws FileNotFoundException
	{
		final double T = 1_000_000_000; //simulation time in seconds
		
		final double dt = 25_000; //time step in seconds, one day
		
		final String fileName = "/Users/shreyasviswanathan/Desktop/Programming/Java Projects/APCS-Java-Projects-Extended/NBodySimulation/planets.txt";
		
		NBodySimulation sim = new NBodySimulation(fileName);
				
		sim.run(T, dt);
	}
}
