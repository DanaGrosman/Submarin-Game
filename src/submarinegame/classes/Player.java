package submarinegame.classes;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class Player implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -5702896062610783258L;
	protected String name;
	protected String email;
	protected String phoneNumber;
	protected Guess[] guesses;
	
	public Player() {
	}
	
	public Player(String name, String email, String phoneNumber) {
		super();
		this.name = name;
		this.email = email;
		this.phoneNumber = phoneNumber;
	}
	
	public Guess[] getGuesses() {
		return guesses;
	}

	public void initGuesses(int numOfGuesses){
		guesses = new Guess[numOfGuesses];
	}

	@Override
	public String toString() {
		return "Player [name=" + name + ", email=" + email + ", phoneNumber=" + phoneNumber + "]";
	}

	public void saveGuessesToFile(File guessesFile) {
		try (FileOutputStream file = new FileOutputStream(guessesFile);
				ObjectOutputStream outputStream = new ObjectOutputStream(file)) {
			outputStream.writeObject(this);

			for(int i=0; i< guesses.length; i++) {
				if(guesses[i]!=null) {
					outputStream.writeObject(guesses[i]);
				}
			}
		} catch (IOException e) {
			System.out.println(e);
		}		
	}	
}
