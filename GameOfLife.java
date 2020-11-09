import java.util.Random;

/**
 * {@link GameOfLife} instances run <i>The Game of Life</i>.
 */
public class GameOfLife {

    private final Random random = new Random();
    private final Grid grid;
    private int generationNumber;

    /**
     * Creates a new {@code GameOfLife} instance given the underlying {@link Grid}.
     *
     * @param grid the underlying {@link Grid}
     * @throws NullPointerException if {@code grid} is {@code null}
     */
    public GameOfLife(Grid grid) {
	//        this.grid = requireNonNull(grid, "grid is null");
	this.grid = grid;
        grid.randomGeneration(random);
    }

    /**
     * Transitions into the next generationNumber.
     */
    public void next() {
        grid.nextGeneration();
	generationNumber++;
    }


    /**
     * Returns the current generationNumber.
     *
     * @return the current generationNumber
     */
    private int getGenerationNumber() {
        return generationNumber;
    }

    /**
     * Returns the {@link Grid}.
     *
     * @return the {@link Grid}
     */
    public Grid getGrid() {
        return grid;
    }

    /**
     * Plays the game.
     */
    public void play(int maxGenerations) {
	for(int generation =0; generation < maxGenerations; generation++){
	    this.next();
	    System.out.println("generation : " + generation);
	}
    }

    /**
     * Pauses the game.
     */
    public void pause() {
	//        timeline.pause();
    }

    /**
     * Clears the current game.
     */
    public void clear() {
        pause();
        grid.clear();
	generationNumber = 0;
    }

    /**
     * Clears the current game and randomly generates a new one.
     */
    public void reset() {
        clear();
        grid.randomGeneration(random);
    }

}
