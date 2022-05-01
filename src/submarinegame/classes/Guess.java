package submarinegame.classes;

import java.io.Serializable;

public class Guess implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7793042475784301325L;
	protected int xCoordinate;
	protected int yCoordinate;
	protected int guessNumber;
	
	public Guess(int xCoordinate, int yCoordinate, int guessNumber) {
		super();
		this.xCoordinate = xCoordinate;
		this.yCoordinate = yCoordinate;
		this.guessNumber = guessNumber;
	}	
	
	public int getxCoordinate() {
		return xCoordinate;
	}

	public int getyCoordinate() {
		return yCoordinate;
	}

	@Override
	public String toString() {
		return "Guess [xCoordinate=" + xCoordinate + ", yCoordinate=" + yCoordinate + ", guessNumber=" + guessNumber
				+ "]";
	}
}
