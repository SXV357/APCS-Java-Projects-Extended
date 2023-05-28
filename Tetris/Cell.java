import java.awt.Color;

public class Cell
{
	private Color color;
	private String imageFileName; //Cell can also draw an image

	public Cell()
	{
		color = Color.BLACK;
		imageFileName = null;
	}

	public void setColor(Color c)
	{
		color = c;
	}

	public Color getColor()
	{
		return color;
	}

	public String getImageFileName()
	{
		return imageFileName;
	}

	public void setImageFileName(String fileName)
	{
		imageFileName = fileName;
	}
}