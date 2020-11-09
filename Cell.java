/**
 * {@link Cell} instances represent the cells of <i>The Game of Life</i>.
 */

public class Cell {
    private boolean isAlive;
    private String color;

    public Cell(){
	this.isAlive = false;
    }
    
    /**
     * Determines whether this {@link Cell} is alive or not.
     *
     * @return {@code true} if this {@link Cell} is alive and {@code false} otherwise
     */

    public boolean isAlive() { return this.isAlive; }

    /**
     * Determines whether this {@link Cell} is dead or not.
     *
     * @return {@code true} if this {@link Cell} is dead and {@code false} otherwise
     */

    public boolean isDead() {
        return !this.isAlive;
    }

    /**
     * Sets the state of this {@link Cell} to alive.
     *
     * @param cellState the new state of this {@link Cell}
     */

    public void setAlive() {
	this.isAlive = true;
    }

    /**
     * Sets the state of this {@link Cell} to dead.
     *
     * @param cellState the new state of this {@link Cell}
     */

    public void setDead() {
	this.isAlive = false;
    }


    /**
     * Change the state of this {@link Cell} from ALIVE to DEAD or from DEAD to ALIVE.
     */

    public void toggleState() {
	if(this.isAlive)
	    this.isAlive = false;
	else
	    this.isAlive = true;
    }

    public boolean isAliveInNextState(int numberOfAliveNeighbours) {
	if(isAlive()){
	    if (numberOfAliveNeighbours == 2 ||  numberOfAliveNeighbours == 3)
		return true;
	    else
		return false;
	}
	else{
	    if (numberOfAliveNeighbours == 3)
		return true;
	    else
		return false;
	}
    }

    public String getColor() {
        return color;
    }

    public void setRedColor() {
        color = "RED";
    }
    public void setBlueColor() {
        color = "BLUE";
    }
}
