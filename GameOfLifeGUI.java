import java.awt.*;
import java.awt.Graphics;
import javax.swing.*;

public class GameOfLifeGUI extends JFrame {
    private int squareSize = 7;
    private int numberOfRows;
    private int numberOfColumns;
    private JLabel[][] labelGrid;
    private GridLayout gridLayout;
    private JPanel gridPanel;
    private JFrame frame;
    
    public GameOfLifeGUI(Grid g) {
	this.numberOfRows = g.getNumberOfRows();
	this.numberOfColumns = g.getNumberOfColumns();
	gridLayout = new GridLayout(numberOfRows, numberOfColumns);
        gridPanel = new JPanel(gridLayout);
	labelGrid = new JLabel[numberOfRows][numberOfColumns];
        for (int x = 0; x < numberOfColumns; x++)
            for (int y = 0; y < numberOfRows; y++){
		labelGrid[x][y] = new JLabel("*");
		JLabel label;
		if(g.getCell(x,y).isAlive())
		   labelGrid[x][y].setForeground(Color.red);
		else
		    labelGrid[x][y].setForeground(Color.white);
		gridPanel.add(labelGrid[x][y]);
	    }
        frame = new JFrame("Game of Life");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setContentPane(gridPanel);
	frame.setSize(squareSize * numberOfRows, squareSize * numberOfColumns);
	frame.setLocationByPlatform(true);
        frame.setVisible(true);
    }

    public void update(Grid g){
        for (int x = 0; x < numberOfColumns; x++)
            for (int y = 0; y < numberOfRows; y++){
		JLabel label = labelGrid[x][y];
		if(g.getCell(x,y).isAlive() && g.setToMajorAliveColor(x,y) == "RED")
		    label.setForeground(Color.red);
		else if (g.getCell(x,y).isAlive() && g.setToMajorAliveColor(x,y) == "BLUE")
		    label.setForeground(Color.blue);
		else
		    label.setForeground(Color.white);
	    }
    }
}
