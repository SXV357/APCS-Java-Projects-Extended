package GameOfLife;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import javax.swing.Timer;

public class LifeModel implements ActionListener
{

	/*
	 *  This is the Model component.
	 */

	private static int SIZE = 60;
	private LifeCell[][] grid;
	
	LifeView myView;
	Timer timer;

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

	/** Reset the simulation by placing cells in random locations once again */
	public void resetSimulation() throws IOException{
		new LifeModel(this.myView);
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

	/** main logic method for updating the state of the grid / simulation */
	private void oneGeneration()
	{
		// If an occupied(isAliveNow = true) cell has 0, 1, 4, 5, 6, 7, or 8 occupied(isAliveNow = true) neighbors, 
		// the organism dies(setAliveNext(false)) (0 or 1 neighbor(s), it dies of loneliness; 4 to 8 of overcrowding). 
		
		// If an occupied(isAliveNow = true) cell has two or three neighbors(isAliveNow = true), 
			// the organism survives to the next generation. (setAliveNext(true))

		// If an unoccupied(isAliveNow = false) cell has three occupied(isAliveNow = true) neighbors, it becomes occupied(setAliveNext(true))

		// deal with occupied cells first
		int numNeighbors = 0;
		for (int r = 0; r < this.grid.length; r++){
			for (int c = 0; c < this.grid[0].length; c++){
				// if the current cell is valid and occupied
				if (this.isValid(r, c) && this.grid[r][c].isAliveNow()){
					if (r == 0){
						// top corners and anything along
						if (c == 0){
							if (this.grid[r][c + 1].isAliveNow() || this.grid[r+1][c].isAliveNow() || this.grid[r+1][c+1].isAliveNow()){
								numNeighbors++;
							}
						}
						else if (c == this.grid[0].length - 1){
							if (this.grid[r][c-1].isAliveNow() || this.grid[r+1][c].isAliveNow() || this.grid[r+1][c-1].isAliveNow()){
								numNeighbors++;
							}
						}
						else {
							if (this.grid[r+1][c-1].isAliveNow() || this.grid[r+1][c].isAliveNow() || this.grid[r+1][c+1].isAliveNow()){
								numNeighbors++;
							}
						}
					}
					else if (r == this.grid.length - 1){
						// bottom corners and anything along
						if (c == 0){
							if (this.grid[r-1][c].isAliveNow() || this.grid[r-1][c+1].isAliveNow() || this.grid[r][c+1].isAliveNow()){
								numNeighbors++;
							}
						}
						else if (c == this.grid[0].length - 1){
							if (this.grid[r-1][c].isAliveNow() || this.grid[r-1][c-1].isAliveNow() || this.grid[r][c-1].isAliveNow()){
								numNeighbors++;
							}
						}
						else {
							if (this.grid[r-1][c-1].isAliveNow() || this.grid[r-1][c].isAliveNow() || this.grid[r-1][c+1].isAliveNow()){
								numNeighbors++;
							}
						}
					}
					else if (c == 0){
						if (!(r == 0 || r == this.grid.length - 1)){
							if (this.grid[r-1][c+1].isAliveNow() || this.grid[r][c+1].isAliveNow() || this.grid[r+1][c+1].isAliveNow()){
								numNeighbors++;
							}
						}
					}
					else if (c == this.grid[0].length - 1){
						if (!(r == 0 || r == this.grid.length - 1)){
							if (this.grid[r-1][c-1].isAliveNow() || this.grid[r][c-1].isAliveNow() || this.grid[r+1][c-1].isAliveNow()){
								numNeighbors++;
							}
						}
					}
					else { // any other location in the grid apart from 1st/last row or 1st/last column
						if (this.grid[r-1][c-1].isAliveNow() || this.grid[r-1][c].isAliveNow() || this.grid[r-1][c+1].isAliveNow()
						 || this.grid[r][c-1].isAliveNow() || this.grid[r][c+1].isAliveNow() || this.grid[r+1][c-1].isAliveNow()
						 || this.grid[r+1][c].isAliveNow() || this.grid[r+1][c+1].isAliveNow()){
							numNeighbors++;
						 }
					}
				}
				// if the current cell is valid but not occupied
				else if (this.isValid(r, c) && !(this.grid[r][c].isAliveNow())){

				}
				if (numNeighbors == 0 || numNeighbors == 1 || numNeighbors == 4 || numNeighbors == 5 ||  numNeighbors == 6 || numNeighbors == 7 || numNeighbors == 8){
					this.grid[r][c].setAliveNext(false);
				}
				else if (numNeighbors == 2 || numNeighbors == 3){
					this.grid[r][c].setAliveNext(true);
				}
			}
		}
	}
}