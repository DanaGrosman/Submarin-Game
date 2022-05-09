package submarinegame.classes;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class Player implements Serializable {

	private static final long serialVersionUID = -5702896062610783258L;
	protected final int GAMES_NUMBER = 10;

	protected String name;
	protected String email;
	protected String phoneNumber;
	protected Guess[] guesses;
	protected Game[] games;
	protected int currentGame;

	public Player() {
	}

	public Player(String name, String email, String phoneNumber) {
		super();
		this.name = name;
		this.email = email;
		this.phoneNumber = phoneNumber;
		games = new Game[GAMES_NUMBER];
	}

	public Guess[] getGuesses() {
		return guesses;
	}

	public void initGuesses(int numOfGuesses) {
		guesses = new Guess[numOfGuesses];
	}

	@Override
	public String toString() {
		return "Player [name=" + name + ", email=" + email + ", phoneNumber=" + phoneNumber + "]";
	}

	public void saveGameDetailsToFile(File gameFile) {
		try (FileOutputStream file = new FileOutputStream(gameFile);
				ObjectOutputStream outputStream = new ObjectOutputStream(file)) {
			
			// Save player details
			outputStream.writeObject(this);
			
			// Save game details
			saveGameObject(gameFile);
			
			// Save guesses
			saveGuessesToFile(gameFile);
			
		} catch (IOException e) {
			System.out.println(e);
		}
	}
	
	private void saveGameObject(File gameFile) {
		try (FileOutputStream file = new FileOutputStream(gameFile);
				ObjectOutputStream outputStream = new ObjectOutputStream(file)) {
			
			outputStream.writeInt(games[currentGame].getPoints());
			outputStream.writeInt(games[currentGame].getGuesses());
			outputStream.writeInt(games[currentGame].getHits());

			BoardGame boardGame = games[currentGame].getBoardGame();
			outputStream.writeInt(boardGame.getCols());
			outputStream.writeInt(boardGame.getRows());
			
			Submarine[] submarines = boardGame.getSubmarines();
			for (int i = 0; i < submarines.length; i++) {
				outputStream.writeObject(submarines[i]);
			}
			
			// TODO: continue write objects to file, check if works..
			
		} catch (IOException e) {
			System.out.println(e);
		}		
	}

	public void saveGuessesToFile(File gameFile) {
		try (FileOutputStream file = new FileOutputStream(gameFile);
				ObjectOutputStream outputStream = new ObjectOutputStream(file)) {
			
			outputStream.writeObject(this);
			
			for (int i = 0; i < guesses.length; i++) {
				if (guesses[i] != null) {
					outputStream.writeObject(guesses[i]);
				}
			}
		} catch (IOException e) {
			System.out.println(e);
		}
	}

	public void addGame(Game game) {
		for (int i = 0; i < GAMES_NUMBER; i++) {
			if (games[i] == null) {
				games[i] = game;
				currentGame = i;
			}
		}
	}
}
