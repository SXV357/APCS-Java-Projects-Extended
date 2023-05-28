public class Location
{
	private int row;
	private int col;

	public Location(int r, int c)
	{
		row = r;
		col = c;
	}

	public int getRow()
	{
		return row;
	}

	public int getCol()
	{
		return col;
	}

	public boolean equals(Location otherLoc)
	{
		return row == otherLoc.getRow() && col == otherLoc.getCol();
	}

	@Override
	public String toString() //for printing a location, for debugging purposes
	{
		return "(" + row + ", " + col + ")";
	}

	public void setRow(int newRow)
	{
		row = newRow;
	}

	public void setCol(int newCol)
	{
		col = newCol;
	}
}
