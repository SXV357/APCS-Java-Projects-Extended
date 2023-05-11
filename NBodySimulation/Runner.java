import java.io.FileNotFoundException;
// import java.io.IOException;

public class Runner 
{
	public static void main(String[] args) throws FileNotFoundException
	{
		final double T = 1_000_000_000; //simulation time in seconds
		
		final double dt = 25_000; //time step in seconds, one day
		
		final String fileName = "C:/Users/14058/OneDrive/Desktop/APCS-Projects-Extended/NBodySimulation/planets.txt";
		
		NBodySimulation sim = new NBodySimulation(fileName);
				
		sim.run(T, dt);
	}
}
