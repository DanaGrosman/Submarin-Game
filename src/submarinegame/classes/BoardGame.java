package submarinegame.classes;

import java.awt.Point;
import java.util.Random;

public class BoardGame {
	protected final int SUBMARINES_NUMBER = 5;
	protected static Random rand = new Random();

	protected int rows;
	protected int cols;
	protected char[][] boardMatrix;
	protected Submarine[] submarines = new Submarine[SUBMARINES_NUMBER];

	public BoardGame(int rows, int cols) {
		this.rows = rows;
		this.cols = cols;
		boardMatrix = new char[rows + 1][cols + 1];
	}

	public int getRows() {
		return rows;
	}

	public int getCols() {
		return cols;
	}

	public void intBoardGame() {
		for (int i = 0; i < cols; i++) {
			boardMatrix[0][i] = '#';
		}
		for (int i = 1; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				if (j == 0 || j == cols - 1 || i == rows - 1)
					boardMatrix[i][j] = '#';
				else
					boardMatrix[i][j] = ' ';
			}
		}
	}

	public void printBoardGame() {
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				System.out.print(boardMatrix[i][j]);
			}
			System.out.println();
		}
	}

	public void placeSubmarinesOnBoard() {
		for (int i = 0; i < submarines.length; i++) {
			submarines[i] = new Submarine(randSize());
			setLocationsOnboard(submarines[i]);
		}
	}

	private int randSize() {
		return rand.nextInt(4) + 1;
	}

	private void setLocationsOnboard(Submarine submarine) {
		// Random first location to the submarine
		submarine.getLocations()[0] = randLocation(submarine, 0);

		// Random other locations
		for (int i = 1; i < submarine.getLocations().length; i++) {
			Point stepBefore = submarine.getLocations()[i - 1];
			Point direction = randDirection();

			while (!checkLocation(stepBefore.x, stepBefore.y, direction)) {
				direction = randDirection();
			}
			submarine.getLocations()[i] = new Point(stepBefore.x + direction.x, stepBefore.y + direction.y);
		}

		updateBoardMatrixWithSubmarine(submarine);
	}

	private Point randDirection() {
		int direction = rand.nextInt(4);
		Point directionPoint = new Point(0, 0);

		switch (direction) {
		case 0: { // Right
			directionPoint = new Point(0, 1);
			break;
		}
		case 1: { // Left
			directionPoint = new Point(0, -1);
			break;
		}
		case 2: { // Up
			directionPoint = new Point(1, 0);
			break;
		}
		case 3: { // Down
			directionPoint = new Point(-1, 0);
			break;
		}
		}
		return directionPoint;
	}

	private Point randLocation(Submarine submarine, int locationIndex) {
		Random rand = new Random();
		Point location = new Point();
		boolean locationFound = false;

		while (!locationFound) {
			location.x = rand.nextInt(rows) + 1;
			location.y = rand.nextInt(cols) + 1;

			if (checkLocation(location.x, location.y, new Point(0, 0)))
				locationFound = true;
		}
		return location;
	}

	public boolean checkLocation(int locationRow, int locationCol, Point direction) {
		return (boardMatrix[locationRow + direction.x][locationCol + direction.y] == ' ')
				&& checkAround(new Point(locationRow + direction.x, locationCol + direction.y)) ? true : false;
	}

	private boolean checkAround(Point nextStep) {
		if ((boardMatrix[nextStep.x][nextStep.y + 1] == ' ') // Right
				&& (boardMatrix[nextStep.x][nextStep.y - 1] == ' ') // Left
				&& (boardMatrix[nextStep.x + 1][nextStep.y] == ' ') // Up
				&& (boardMatrix[nextStep.x - 1][nextStep.y] == ' ')) // Down
			return true;
		else
			return false;
	}

	private void updateBoardMatrixWithSubmarine(Submarine submarine) {
		for (int j = 0; j < submarine.getLocations().length; j++) {
			int row = submarine.getLocations()[j].x;
			int col = submarine.getLocations()[j].y;
			boardMatrix[row][col] = 'S';
		}
	}
}
