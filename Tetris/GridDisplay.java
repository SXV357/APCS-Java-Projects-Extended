import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class GridDisplay extends JComponent implements KeyListener, MouseListener
{
	private Cell[][] cells;
	private JFrame   frame;
	private int      lastKeyPressed;
	private Location lastLocationClicked;
	private Color    lineColor;

	public GridDisplay(int numRows, int numCols)
	{
		int cellSize = Math.max(Math.min(600 / numRows, 600 / numCols), 1);
		init(numRows, numCols, cellSize);
	}

	public GridDisplay(int numRows, int numCols, int cellSize)
	{
		init(numRows, numCols, cellSize);
	}

	public GridDisplay(String imageFileName)
	{
		BufferedImage image = loadImage(imageFileName);
		int height = image.getHeight();
		int width = image.getWidth();
		int cellSize = Math.max(Math.min(600 / height, 600 / width), 1);
		init(height, width, cellSize);
		showImage(image);
		setTitle(imageFileName);
	}

	private BufferedImage loadImage(String imageFileName)
	{
		URL url = getClass().getResource(imageFileName);
		if (url == null)
			throw new RuntimeException("cannot find file:  " + imageFileName);
		try
		{
			return ImageIO.read(url);
		}
		catch(IOException e)
		{
			throw new RuntimeException("unable to read from file:  " + imageFileName);
		}
	}

	/** get number of rows in the grid display */
	public int getNumRows()
	{
		return cells.length;
	}

	/** get number of columns in the grid display */
	public int getNumCols()
	{
		return cells[0].length;
	}

	private void init(int numRows, int numCols, int cellSize)
	{
		lastKeyPressed = -1;
		lastLocationClicked = null;
		lineColor = null;

		cells = new Cell[numRows][numCols];
		for (int row = 0; row < numRows; row++)
		{
			for (int col = 0; col < numCols; col++)
				cells[row][col] = new Cell();
		}

		frame = new JFrame("Grid");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.addKeyListener(this);

		setPreferredSize(new Dimension(cellSize * numCols, cellSize * numRows));
		addMouseListener(this);
		frame.getContentPane().add(this);

		frame.pack();
		frame.setVisible(true);
	}

	private void showImage(BufferedImage image)
	{
		for (int row = 0; row < getNumRows(); row++)
		{
			for (int col = 0; col < getNumCols(); col++)
			{
				int x = col * image.getWidth() / getNumCols();
				int y = row * image.getHeight() / getNumRows();
				int c = image.getRGB(x, y);
				int red = (c & 0x00ff0000) >> 16;
			int green = (c & 0x0000ff00) >> 8;
			int blue = c & 0x000000ff;
			cells[row][col].setColor(new Color(red, green, blue));
			}
		}
		repaint();
	}

	private int getCellSize()
	{
		int cellWidth = getWidth() / getNumCols();
		int cellHeight = getHeight() / getNumRows();
		return Math.min(cellWidth, cellHeight);
	}

	public void keyPressed(KeyEvent e)
	{
		lastKeyPressed = e.getKeyCode();
	}

	public void keyReleased(KeyEvent e)
	{
		//ignored
	}

	public void keyTyped(KeyEvent e)
	{
		//ignored
	}

	public void mousePressed(MouseEvent e)
	{
		int cellSize = getCellSize();
		int row = e.getY() / cellSize;
		if (row < 0 || row >= getNumRows())
			return;
		int col = e.getX() / cellSize;
		if (col < 0 || col >= getNumCols())
			return;
		lastLocationClicked = new Location(row, col);
	}

	public void mouseReleased(MouseEvent e)
	{
		//ignore
	}

	public void mouseClicked(MouseEvent e)
	{
		//ignore
	}

	public void mouseEntered(MouseEvent e)
	{
		//ignore
	}

	public void mouseExited(MouseEvent e)
	{
		//ignore
	}

	private static java.awt.Color toJavaColor(Color color)
	{
		return new java.awt.Color(color.getRed(), color.getGreen(), color.getBlue());
	}

	public void paintComponent(Graphics g)
	{
		for (int row = 0; row < getNumRows(); row++)
		{
			for (int col = 0; col < getNumCols(); col++)
			{
				Location loc = new Location(row, col);
				Cell cell = cells[loc.getRow()][loc.getCol()];

				Color color = cell.getColor();
				g.setColor(toJavaColor(color));
				int cellSize = getCellSize();
				int x = col * cellSize;
				int y = row * cellSize;
				g.fillRect(x, y, cellSize, cellSize);

				String imageFileName = cell.getImageFileName();
				if (imageFileName != null)
				{
					URL url = getClass().getResource(imageFileName);
					if (url == null)
						System.out.println("File not found:  " + imageFileName);
					else
					{

						Image image = new ImageIcon(url).getImage();
						int width = image.getWidth(null);
						int height = image.getHeight(null);
						if (width > height)
						{
							int drawHeight = cellSize * height / width;
							g.drawImage(image, x, y + (cellSize - drawHeight) / 2, cellSize, drawHeight, null);
						}
						else
						{
							int drawWidth = cellSize * width / height;
							g.drawImage(image, x + (cellSize - drawWidth) / 2, y, drawWidth, cellSize, null);
						}
					}
				}

				if (lineColor != null)
				{
					g.setColor(toJavaColor(lineColor));
					g.drawRect(x, y, cellSize, cellSize);
				}
			}    
		}
	}

	public void setTitle(String title)
	{
		frame.setTitle(title);
	}

	/** return true if the given location is valid (within the bounds of the grid display */
	public boolean isValid(Location loc)
	{
		int row = loc.getRow();
		int col = loc.getCol();
		return 0 <= row && row < getNumRows() && 0 <= col && col < getNumCols();
	}

	/** set the color to the given color at the given location */
	public void setColor(Location loc, Color color)
	{
		if (!isValid(loc))
			throw new RuntimeException("cannot set color of invalid location " + loc + " to color " + color);
		cells[loc.getRow()][loc.getCol()].setColor(color);
		repaint();
	}

	/** get the color at the given location */
	public Color getColor(Location loc)
	{
		if (!isValid(loc))
			throw new RuntimeException("cannot get color from invalid location " + loc);
		return cells[loc.getRow()][loc.getCol()].getColor();
	}

	public void setImage(Location loc, String imageFileName)
	{
		if (!isValid(loc))
			throw new RuntimeException("cannot set image for invalid location " + loc + " to \"" + imageFileName + "\"");
		cells[loc.getRow()][loc.getCol()].setImageFileName(imageFileName);
		repaint();
	}

	public String getImage(Location loc)
	{
		if (!isValid(loc))
			throw new RuntimeException("cannot get image for invalid location " + loc);
		return cells[loc.getRow()][loc.getCol()].getImageFileName();
	}

	/** wait the given number of ms between updates */
	public void pause(int milliseconds)
	{
		try {
			Thread.sleep(milliseconds);
		}
		catch (InterruptedException e) {
			//ignore
		}
	}

	/** returns -1 if no key pressed since last call.
	  * otherwise returns the code for the last key pressed. */
	public int checkLastKeyPressed()
	{
		int key = lastKeyPressed;
		lastKeyPressed = -1;
		return key;
	}

	/** returns null if no location clicked since last call. */
	public Location checkLastLocationClicked()
	{
		Location loc = lastLocationClicked;
		lastLocationClicked = null;
		return loc;
	}

	public void load(String imageFileName)
	{
		showImage(loadImage(imageFileName));
		setTitle(imageFileName);
	}

	public void save(String imageFileName)
	{
		try
		{
			BufferedImage bi = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_RGB);
			paintComponent(bi.getGraphics());
			int index = imageFileName.lastIndexOf('.');
			if (index == -1)
				throw new RuntimeException("invalid image file name:  " + imageFileName);
			ImageIO.write(bi, imageFileName.substring(index + 1), new File(imageFileName));
			setTitle(imageFileName);
		}
		catch(IOException e)
		{
			throw new RuntimeException("unable to save image to file:  " + imageFileName);
		}
	}

	public void setLineColor(Color color)
	{
		lineColor = color;
		repaint();
	} 

	/** display the message in a dialog box */
	public void showMessageDialog(String message)
	{
		JOptionPane.showMessageDialog(this, message);
	}

	public String showInputDialog(String message)
	{
		return JOptionPane.showInputDialog(this, message);
	}

	public String showInputDialog(String message, String[] choices)
	{
		return (String)JOptionPane.showInputDialog(this, message, null, JOptionPane.PLAIN_MESSAGE, null, choices, null);
	}

	//returns number of seconds that have elapsed since January 1, 1970
	public static int getCurrentTime()
	{
		return (int)(System.currentTimeMillis() / 1000);
	}
}