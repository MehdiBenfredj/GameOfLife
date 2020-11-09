import javax.swing.*;
import java.util.*;

/**
 * {@code Grid} instances represent the grid in <i>The Game of Life</i>.
 */
public class Grid implements Iterable<Cell> {

    private final int numberOfRows;
    private final int numberOfColumns;
    private final Cell[][] cells;

    /**
     * Creates a new {@code Grid} instance given the number of rows and columns.
     *
     * @param numberOfRows    the number of rows
     * @param numberOfColumns the number of columns
     * @throws IllegalArgumentException if {@code numberOfRows} or {@code numberOfColumns} are
     *                                  less than or equal to 0
     */
    public Grid(int numberOfRows, int numberOfColumns) {
        this.numberOfRows = numberOfRows;
        this.numberOfColumns = numberOfColumns;
        this.cells = createCells();
    }

    @Override
    public Iterator<Cell> iterator() {
        return new GridIterator(this);
    }

    private Cell[][] createCells() {
        Cell[][] cells = new Cell[getNumberOfRows()][getNumberOfColumns()];
        for (int rowIndex = 0; rowIndex < getNumberOfRows(); rowIndex++) {
            for (int columnIndex = 0; columnIndex < getNumberOfColumns(); columnIndex++) {
                cells[rowIndex][columnIndex] = new Cell();
            }
        }
        return cells;
    }

    /**
     * Returns the {@link Cell} at the given index.
     *
     * <p>Note that the index is wrapped around so that a {@link Cell} is always returned.
     *
     * @param rowIndex    the row index of the {@link Cell}
     * @param columnIndex the column index of the {@link Cell}
     * @return the {@link Cell} at the given row and column index
     */
    public Cell getCell(int rowIndex, int columnIndex) {
        return cells[getWrappedRowIndex(rowIndex)][getWrappedColumnIndex(columnIndex)];
    }

    private int getWrappedRowIndex(int rowIndex) {
        return (rowIndex + getNumberOfRows()) % getNumberOfRows();
    }

    private int getWrappedColumnIndex(int columnIndex) {
        return (columnIndex + getNumberOfColumns()) % getNumberOfColumns();
    }

    /**
     * Returns the number of rows in this {@code Grid}.
     *
     * @return the number of rows in this {@code Grid}
     */
    public int getNumberOfRows() {
        return numberOfRows;
    }

    /**
     * Returns the number of columns in this {@code Grid}.
     *
     * @return the number of columns in this {@code Grid}
     */
    public int getNumberOfColumns() {
        return numberOfColumns;
    }

    /**
     * Transitions all {@link Cell}s in this {@code Grid} to the next generation.
     *
     * <p>The following rules are applied:
     * <ul>
     * <li>Any live {@link Cell} with fewer than two live neighbours dies, i.e. underpopulation.</li>
     * <li>Any live {@link Cell} with two or three live neighbours lives on to the next
     * generation.</li>
     * <li>Any live {@link Cell} with more than three live neighbours dies, i.e. overpopulation.</li>
     * <li>Any dead {@link Cell} with exactly three live neighbours becomes a live cell, i.e.
     * reproduction.</li>
     * </ul>
     */
    void nextGeneration() {
        goToNextState(calculateNextStates());
    }


    private boolean[][] calculateNextStates() {
        boolean[][] nextStates = new boolean[getNumberOfRows()][getNumberOfColumns()];
        for (int rowIndex = 0; rowIndex < getNumberOfRows(); rowIndex++) {
            for (int columnIndex = 0; columnIndex < getNumberOfColumns(); columnIndex++) {
                nextStates[rowIndex][columnIndex] = calculateNextState(rowIndex,columnIndex,getCell(rowIndex,columnIndex));
            }
        }
        return nextStates;
    }

    private boolean calculateNextState(int rowIndex, int columnIndex, Cell cell) {
        return cell.isAliveInNextState(countAliveNeighbours(rowIndex,columnIndex));
    }

    private int countAliveNeighbours(int rowIndex, int columnIndex) {
        int aliveNeighbours=0;
        List<Cell> neighbours = getNeighbours(rowIndex,columnIndex);
        for (int i = 0;i< neighbours.size(); i++){
            if(neighbours.get(i).isAlive()){
                aliveNeighbours++;
            }
        }
        return aliveNeighbours;
    }


    private List<Cell> getNeighbours(int rowIndex, int columnIndex) {
        List<Cell> neighbours = new ArrayList<>();
        neighbours.add(getCell(rowIndex-1,columnIndex-1));
        neighbours.add(getCell(rowIndex-1,columnIndex));
        neighbours.add(getCell(rowIndex-1,columnIndex+1));
        neighbours.add(getCell(rowIndex,columnIndex-1));
        neighbours.add(getCell(rowIndex,columnIndex+1));
        neighbours.add(getCell(rowIndex+1,columnIndex-1));
        neighbours.add(getCell(rowIndex+1,columnIndex));
        neighbours.add(getCell(rowIndex+1,columnIndex+1));

        return neighbours;
    }

    private void goToNextState(boolean[][] nextState) {
        for(int rowIndex=0; rowIndex<getNumberOfRows();rowIndex++){
            for(int columnIndex=0; columnIndex<getNumberOfColumns(); columnIndex++){
                if(nextState[rowIndex][columnIndex]){
                    getCell(rowIndex,columnIndex).setAlive();
                }
                else{
                    getCell(rowIndex,columnIndex).setDead();
                }
            }
        }
    }

    /**
     * Sets all {@link Cell}s in this {@code Grid} as dead.
     */
    void clear() {
        for (int rowIndex = 0; rowIndex < getNumberOfRows(); rowIndex++) {
            for (int columnIndex = 0; columnIndex < getNumberOfColumns(); columnIndex++) {
                getCell(rowIndex, columnIndex).setDead();
            }
        }
    }


    /**
     * Goes through each {@link Cell} in this {@code Grid} and randomly sets it as alive or dead.
     *
     * @param random {@link Random} instance used to decide if each {@link Cell} is alive or dead
     * @throws NullPointerException if {@code random} is {@code null}
     */
 
    void randomGeneration(Random random) {
        for (int rowIndex = 0; rowIndex < getNumberOfRows(); rowIndex++) {
            for (int columnIndex = 0; columnIndex < getNumberOfColumns(); columnIndex++) {
                if(random.nextBoolean()){
                    getCell(rowIndex,columnIndex).setRedColor();
                    getCell(rowIndex,columnIndex).setAlive();
                }
                else{getCell(rowIndex,columnIndex).setDead();}
            }
        }
    }
    /**
     * Selected begining
     */
    void selectedGenerator(){
        Scanner scanner1 = new Scanner(System.in);
        System.out.println("How many cells alive: ");
        int numberOfAlive = scanner1.nextInt();

        int[] aliveCells = new int[numberOfAlive*2];
        Scanner scanner = new Scanner(System.in);
        for(int i=0;i<numberOfAlive;i++){
            System.out.println("Row: ");
            aliveCells[i] = scanner.nextInt();
            System.out.println("Column: ");
            aliveCells[i*2] = scanner.nextInt();
        }

        for (int i = 0; i<numberOfAlive; i++){
            getCell(aliveCells[i],aliveCells[i*2]).setAlive();
        }
    }


    public String setToMajorAliveColor(int rowIndex, int columnIndex) {
        int red = 0;
        int blue = 0;
        List<Cell> neighbours = getNeighbours(rowIndex,columnIndex);
        for (int i = 0; i<neighbours.size() ; i++) {
            if(neighbours.get(i).isAlive() && neighbours.get(i).getColor() == "RED"){
                red++;
            }
            else if (neighbours.get(i).isAlive() && neighbours.get(i).getColor() == "BLUE"){
                blue++;
            }
        }
        if(red>blue){return "RED";}
        else{return "BLUE";}
    }


    public void nextColor(Cell cell, int rowIndex, int columnIndex){
        int aliveNeighbours = countAliveNeighbours(rowIndex,columnIndex);
        boolean isAliveNextState = cell.isAliveInNextState(aliveNeighbours);
        boolean isAlive = cell.isAlive();

        if (!isAlive && isAliveNextState){
            setToMajorAliveColor(rowIndex, columnIndex);
        }
    }

}