import java.awt.Color;

public class Block
{
	private GridDisplay display;
	private Color       color;
	private Location[]  locs;

	public Block(GridDisplay disp)
	{
		//TODO
	}

	public void drawSelf(Color col)
	{
		//TODO
	}

	/** check potential new locations to see if this block has a valid move there */
	public boolean areValidAndEmpty(Location[] newLocs)
	{
		//TODO

		return false; //replace
	}

	public boolean shift(int deltaRow, int deltaCol)
	{
		//TODO

		return false; //replace
	}

	public void rotate()
	{
		//TODO
	}
}
