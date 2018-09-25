package hangman;

import java.util.*;
import java.io.*;
import java.lang.*;
import hangman.IEvilHangmanGame.GuessAlreadyMadeException;

public class Main {
	private static void testGame(){
		EvilHangmanGame otherGame = new EvilHangmanGame();
		File dictionary = new File("dictionary.txt");
		System.out.println("you in");

		try{
		otherGame.startGame(dictionary, 2);
		otherGame.makeGuess('v');
		otherGame.makeGuess('l');
		otherGame.makeGuess('z');
		otherGame.makeGuess('f');
		otherGame.startGame(dictionary, 3);
		otherGame.makeGuess('e');
		otherGame.makeGuess('p');
		otherGame.makeGuess('v');
		otherGame.makeGuess('w');
		otherGame.makeGuess('g');
		otherGame.makeGuess('x');
		otherGame.startGame(dictionary, 4);
		otherGame.makeGuess('p');
		otherGame.makeGuess('a');
		otherGame.makeGuess('h');
		otherGame.makeGuess('u');
		otherGame.makeGuess('l');
		otherGame.makeGuess('g');
		otherGame.makeGuess('j');
		otherGame.makeGuess('e');
		otherGame.startGame(dictionary, 5);
		otherGame.makeGuess('k');
		otherGame.makeGuess('w');
		otherGame.makeGuess('e');
		otherGame.makeGuess('u');
		otherGame.makeGuess('s');
		otherGame.makeGuess('b');
		otherGame.makeGuess('t');
		otherGame.makeGuess('i');
		otherGame.makeGuess('z');
		otherGame.makeGuess('n');
		otherGame.startGame(dictionary, 6);
		otherGame.makeGuess('t');
		otherGame.makeGuess('m');
		otherGame.makeGuess('r');
		otherGame.makeGuess('n');
		otherGame.makeGuess('o');
		otherGame.makeGuess('j');
		otherGame.makeGuess('a');
		otherGame.makeGuess('q');
		otherGame.makeGuess('e');
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