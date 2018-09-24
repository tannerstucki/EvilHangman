package hangman;

import java.util.*;
import java.io.*;
import java.lang.*;
import hangman.IEvilHangmanGame.GuessAlreadyMadeException;

public class Main {
	public static void main(String[] args) throws IOException {
		File dictionary = new File(args[0]);
		//boolean invalid = true;
		int wordLength;
		int guesses;
		//char guess = 'x';

		EvilHangmanGame game = new EvilHangmanGame();
		try{
			wordLength = Integer.parseInt(args[1]);
			guesses = Integer.parseInt(args[2]);
			game.startGame(dictionary, wordLength);
		}
		catch(NumberFormatException e){
			System.out.println("Invalid command line arguement");
			return;
		}

		game.runGame(guesses, wordLength);
		/*do{
			invalid = true;
			System.out.println("You have " + guesses + " guesses left");
			System.out.print("Used letters: ");
			if (game.guessedLetters.size() == 0) {
				System.out.print("\n");
			}
			else{
				Collections.sort(game.guessedLetters);
				for (int j = 0; j < game.guessedLetters.size(); j++) {
						if (j < game.guessedLetters.size() - 1){
							System.out.print(game.guessedLetters.get(j) + " ");
						}
						else{
							System.out.println(game.guessedLetters.get(j));	
						}
				}
			}
			if (game.guessedWord.toString().equals("")) {
				System.out.print("Word: ");
				String dash = "-";
				for (int i = 0; i < wordLength; i++ ) {
					game.guessedWord.append(dash);
					System.out.print("-");
				}
				System.out.print("\n");
			}
			else {
				System.out.println("Word: " + game.guessedWord.toString());
			}
			while(invalid){
				System.out.print("Enter guess: ");
				Scanner in = new Scanner(System.in);
				guess = in.next().charAt(0);
				guess = Character.toLowerCase(guess);
				/*if (Character.isLetter(guess) == false) {
					System.out.println("Invalid input");
					invalid = true;
				}
				try{
					if (invalid) {
						game.makeGuess(guess);
					}
					invalid = false;
				} catch(GuessAlreadyMadeException e){
					invalid = true;
				}
				//in.close();
			}
			if (game.passedThrough == false) {
				guesses--;
			}
			if (game.numberOfCorrect == 0) {
				System.out.println("Sorry, there are no " + guess + "'s" + "\n");
			}
			else if (game.numberOfCorrect == 1 ){
				System.out.println("Yes, there is 1 " + guess + "\n");
			}
			else{
				System.out.println("Yes, there are " + game.numberOfCorrect + " " + guess + "'s" + "\n");
			}
		} while(guesses > 0 && game.completedWord == false);

		if (game.completedWord == true) {
			System.out.println("Congrats, you won.");
			System.out.println("The word was: " + game.guessedWord.toString());
		}
		else{
			System.out.println("You lose!");
			System.out.println("The word was: " + game.fakeWord);
		}*/
	}
}