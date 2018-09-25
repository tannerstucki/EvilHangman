package hangman;

import java.util.*;
import java.io.*;
import java.lang.*;
import hangman.IEvilHangmanGame.GuessAlreadyMadeException;

public class Main {
	private static void testGame(){
		EvilHangmanGame otherGame = new EvilHangmanGame();
		File dictionary = new File("dictionary.txt");

		try{
		otherGame.startGame(dictionary, 8);
		otherGame.makeGuess('z');
		otherGame.makeGuess('s');
		otherGame.makeGuess('y');
		otherGame.makeGuess('n');
		otherGame.makeGuess('q');
		otherGame.makeGuess('k');
		otherGame.makeGuess('e');
		otherGame.makeGuess('a');
		otherGame.makeGuess('h');
		otherGame.makeGuess('c');
		otherGame.makeGuess('x');
		otherGame.makeGuess('j');
		otherGame.makeGuess('b');
		otherGame.makeGuess('g');
		otherGame.makeGuess('d');
		System.out.println(otherGame.curWords+"\n");
		otherGame.makeGuess('r');
		System.out.println(otherGame.curWords+"\n");
		}
		catch(GuessAlreadyMadeException e){
			System.out.println("Invalid command line arguement");
		}
	}

	public static void main(String[] args) throws IOException {
		//testGame();
		File dictionary = new File(args[0]);
		int wordLength;
		int guesses;

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
	}
}