import java.awt.Color;

public class Game
{
	static final int STARTING_SPEED = 50;
	
	private GridDisplay display; //GUI class
	private Block block;   //current block on screen, controlled by player
	private int speed;   //sleep duration, for controlling game speed

	/** make a new game starting at the default speed */
	public Game()
	{
		this(STARTING_SPEED); //calls this single-parameter constructor, with the "default" starting speed
	}
	
	public Game(int speed)
	{
		this.speed   = speed;
		this.display = new GridDisplay(20, 10);
		this.block   = new Block(display);
		
		this.display.setTitle("Tetris");
		this.display.setLineColor(Color.BLACK);
	}

	public void play()
	{
		while (true)
		{
			for (int i = 0; i < 10; i++) //loop to process 10 key presses in 10*speed (0.5 seconds at default speed)
			{
				display.pause(speed);
				
				int key = display.checkLastKeyPressed(); 
				
				if (key == 37) { 
					this.block.shift(0, -1);
				}				
				
				else if (key == 38) {
					this.block.rotate();
				}
				
				else if (key == 39) {
					this.block.shift(0, 1);
				}
				
				else if (key == 40) {
					this.block.shift(1, 0);
				}
			}
		}
	}

	public boolean isCompletedRow(int row)
	{
		//TODO
		
		return false; //replace
	}

	public void removeSquare(Location loc) 
	{
		//TODO
	}

	public void removeRow(int row) 
	{
		//TODO
	}

	public void removeCompletedRows() 
	{
		//TODO
	}
}