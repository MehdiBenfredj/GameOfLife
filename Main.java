import java.io.*;
import java.awt.*;
import javax.swing.*;

public class Main{
    public static void main(String args[]) throws IOException {
	int NUMBER_OF_ROWS = 64;
	int NUMBER_OF_COLUMNS = 64;
	
	GameOfLife gameOfLife = new GameOfLife(new Grid(NUMBER_OF_ROWS, NUMBER_OF_COLUMNS));
	
	GameOfLifeGUI gui = new GameOfLifeGUI(gameOfLife.getGrid());

	for(int generation =0; generation < 1000; generation++){
	    try {
		Thread.sleep(100);
	    } catch (InterruptedException ie) {

	    }
	    gameOfLife.next();
	    gui.update(gameOfLife.getGrid());
	}
	
    }
}
