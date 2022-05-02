package submarinegame.classes;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.Scanner;

public class Game {
	protected static Scanner scanner = new Scanner(System.in);

	protected final int START_POINTS = 1000;
	protected final int GUESSES = 5;

	protected int points;
	protected int guesses;
	protected int hits;
	protected BoardGame boardGame;
	protected BoardGame boardGameWithGuesses;

	protected Player player;
	protected File guessesFile;

	public Game() {
		setPoints(START_POINTS);
		setGusses(GUESSES);
		setHits(0);
		boardGame = new BoardGame(10, 20);
		boardGameWithGuesses = new BoardGame(10, 20);
		player = new Player();
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
		initPlayer();

		boardGame.initBoardGame();
		boardGameWithGuesses.initBoardGame();
		boardGame.placeSubmarinesOnBoard();
		boardGameWithGuesses.printBoardGame();

		while (guesses > 0) {
			getGuess();
			guesses--;
		}

		player.saveGuessesToFile(guessesFile);
		// replay();
	}

	public void replay(String strFile) {
		BoardGame boardGameReplay = new BoardGame(10, 20);
		boardGameReplay.initBoardGame();

		try (FileInputStream file = new FileInputStream(strFile);
				ObjectInputStream inputStream = new ObjectInputStream(file)) {

			Player player = (Player) inputStream.readObject();
			System.out.println(player);

			for (int i = 0; i < player.getGuesses().length; i++) {
				if (player.getGuesses()[i] != null) {
					Guess guess = (Guess) inputStream.readObject();
					System.out.println(guess);
					setGuessOnboard(guess.getxCoordinate(), guess.getyCoordinate());
					boardGameReplay.printBoardGame();
					// TODO: create delay
				}
			}

		} catch (IOException | ClassNotFoundException e) {
			System.out.println(e);
		}
	}

	private void initPlayer() {
		// TODO: get player info from user
		player = new Player("Dana", "dana@gmail.com", "0526998773");
		player.initGuesses(GUESSES);
		guessesFile = new File("gameFiles/guessesFile.txt");
	}

	private void getGuess() {
		// TODO: create exceptions
		int x, y;
//		boolean inputIsOK = true;

//		while (inputIsOK) {
//			try {
		System.out.println("x coordinate: ");
		x = scanner.nextInt();
		System.out.println("y coordinate: ");
		y = scanner.nextInt();

		setGuessOnboard(x, y);
		boardGameWithGuesses.printBoardGame();
//				inputIsOK = true;

//			} catch (InputMismatchException e) {
//				inputIsOK = false;
//				System.out.println("Input must be an integer.\n Please try again: ");
//			}
//		}

		player.getGuesses()[GUESSES - guesses] = new Guess(x, y, GUESSES - guesses);
	}

	private void setGuessOnboard(int x, int y) {
		if (boardGame.checkGuess(x, y)) {
			boardGameWithGuesses.setHitOnBoard(x, y);
			// TODO: check if the ship is fallen
		} else
			boardGameWithGuesses.setMissOnBoard(x, y);
	}
}
