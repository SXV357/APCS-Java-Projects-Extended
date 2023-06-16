package GameOfLife;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.awt.Color;
import javax.swing.JPanel;
import javax.swing.Timer;
import java.awt.Graphics;

public class LifeModel implements ActionListener
{

	/*
	 *  This is the Model component.
	 */

	private static int SIZE = 60;
	private LifeCell[][] grid;
	
	LifeView myView;
	Timer timer;
	Life life;

	/** Construct a new model using a particular file */
	public LifeModel(LifeView view, String fileName) throws IOException
	{       
		int r, c;
		grid = new LifeCell[SIZE][SIZE];
		for ( r = 0; r < SIZE; r++ )
			for ( c = 0; c < SIZE; c++ )
				grid[r][c] = new LifeCell();

		if ( fileName == null ) //use random population
		{                                           
			for ( r = 0; r < SIZE; r++ )
			{
				for ( c = 0; c < SIZE; c++ )
				{
					if ( Math.random() > 0.85) //15% chance of a cell starting alive
						grid[r][c].setAliveNow(true);
				}
			}
		}
		else
		{                 
			Scanner input = new Scanner(new File(fileName));
			int numInitialCells = input.nextInt();
			for (int count=0; count<numInitialCells; count++)
			{
				r = input.nextInt();
				c = input.nextInt();
				grid[r][c].setAliveNow(true);
			}
			input.close();
		}

		myView = view;
		myView.updateView(grid);

	}

	/** Constructor a randomized model */
	public LifeModel(LifeView view) throws IOException
	{
		this(view, null);
	}

	/** pause the simulation (the pause button in the GUI */
	public void pause()
	{
		timer.stop();
	}
	
	/** resume the simulation (the pause button in the GUI */
	public void resume()
	{
		timer.restart();
	}

	public void randomizeColor(Graphics g){
		if (this.life != null && this.life.getColorStatus()){
			for (int r = 0; r < SIZE; r++){
				for (int c = 0; c < SIZE; c++){
					if (grid[r][c].isAliveNow()){
						int red = (int) (Math.random() * 256);
						int green = (int) (Math.random() * 256);
						int blue = (int) (Math.random() * 256);
						g.setColor(new Color(red, green, blue));
					}
				}
			}
			myView.updateView(this.grid);
		}
		else if (this.life != null && !this.life.getColorStatus()){
			for (int r = 0; r < SIZE; r++){
				for (int c = 0; c < SIZE; c++){
					if (grid[r][c].isAliveNow()){
						g.setColor(Color.BLUE);
					}
				}
			}
			myView.updateView(this.grid);
		}
	}

	/** Reset the simulation by placing cells in random locations once again */
	public void resetSimulation() throws IOException{
		this.pause();
		this.timer = null;
		this.grid = new LifeCell[SIZE][SIZE];
		for (int r = 0; r < SIZE; r++){
			for (int c = 0; c < SIZE; c++){
				grid[r][c] = new LifeCell();
				if (Math.random() > 0.85){
					grid[r][c].setAliveNow(true);
				}
			}
		}
		myView.updateView(grid);
	}
	
	/** run the simulation (the pause button in the GUI */
	public void run()
	{
		timer = new Timer(50, this);
		timer.setCoalesce(true);
		timer.start();
	}

	/** called each time timer fires */
	public void actionPerformed(ActionEvent e)
	{
		oneGeneration();
		myView.updateView(grid);
	}

	public boolean isValid(int r, int c){
		return (r >= 0 && r < this.grid.length) && (c >= 0 && c < this.grid[0].length);
	}

	/**The number of occupied neighbors for a given cell irrespective of whether it is alive or not */
	public int getNumNeighbors(int row, int col){
		int numNeighbors = 0;
		for (int i = -1; i <= 1; i++){
			for (int j = -1; j <= 1; j++){
				int modifiedRow = row + i;
				int modifiedCol = col + j;
				if (this.isValid(modifiedRow, modifiedCol) && (modifiedRow != row || modifiedCol != col)){
					numNeighbors += this.grid[modifiedRow][modifiedCol].isAliveNow() ? 1 : 0;
				}
			}
		}
		return numNeighbors;
	}

	/** main logic method for updating the state of the grid / simulation */
	private void oneGeneration()
	{
		for (int x = 0; x < this.grid.length; x++){
			for (int y = 0; y < this.grid[0].length; y++){
				int neighbors = this.getNumNeighbors(x, y);
				if (this.grid[x][y].isAliveNow()){
					if (neighbors == 0 || neighbors == 1 || neighbors == 4 || neighbors == 5 || neighbors == 6 || neighbors == 7 || neighbors == 8){
						this.grid[x][y].setAliveNext(false);
					}
					else{
						this.grid[x][y].setAliveNext(true);
					}
				}
				else {
					if (neighbors == 3){
						this.grid[x][y].setAliveNext(true);
					}
					else {
						this.grid[x][y].setAliveNext(false);
					}
				}
			}
		}

		// update aliveNow to reflect aliveNext
		for (int j = 0; j < this.grid.length; j++){
			for (int k = 0; k < this.grid[0].length; k++){
				this.grid[j][k].setAliveNow(this.grid[j][k].isAliveNext());
			}
		}
}
}