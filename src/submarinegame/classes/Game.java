package submarinegame.classes;

public class Game {
	protected final int START_POINTS = 1000;
	protected final int GUESSES = 100;

	protected int points;
	protected int guesses;
	protected int hits;
	protected BoardGame boardGame;

	public Game() {
		setPoints(START_POINTS);
		setGusses(GUESSES);
		setHits(0);
		boardGame = new BoardGame(10,20);
	}
	
	private void setHits(int hits) {
		this.hits = hits;
	}

	private void setGusses(int guesses) {
		this.guesses = guesses;
	}

	private void setPoints(int points) {
		this.points = points;
	}

	public void play() {
		boardGame.intBoardGame();
		boardGame.placeSubmarinesOnBoard();
		boardGame.printBoardGame();
	}
}
