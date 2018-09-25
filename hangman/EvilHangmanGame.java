package hangman;

import java.util.*;
import java.io.*;
import java.lang.*;

public class EvilHangmanGame implements IEvilHangmanGame {
	
	public EvilHangmanGame(){}

	Set <String> curWords = new HashSet<String>();
	Map<String, Set<String>> wordMap = new HashMap<String, Set<String>>();
	List <Character> guessedLetters = new ArrayList<Character>();
	StringBuilder guessedWord = new StringBuilder();
	int numberOfCorrect = 0;
	boolean completedWord = false;
	String fakeWord;
	boolean passedThrough = false;

	/*@SuppressWarnings("serial")
	public static class GuessAlreadyMadeException extends Exception {
		public GuessAlreadyMadeException(String message){
			super(message);
		}
	}*/
	
	/**
	 * Starts a new game of evil hangman using words from <code>dictionary</code>
	 * with length <code>wordLength</code>.
	 *	<p>
	 *	This method should set up everything required to play the game,
	 *	but should not actually play the  (ie. There should not be
	 *	a loop to prompt for input from the user.)
	 * 
	 * @param dictionary Dictionary of words to use for the game
	 * @param wordLength Number of characters in the word to guess
	 */
	public void startGame(File dictionary, int wordLength) {
		guessedLetters.clear();
		wordMap.clear();
		guessedWord.setLength(0);
		numberOfCorrect = 0;
		completedWord = false;
		fakeWord = "";
		passedThrough = false;
		curWords.clear();

		try{
			Scanner scan = new Scanner(dictionary);
			while(scan.hasNext()){
				String file_in = scan.next();
				if(isAlpha(file_in)){
					if (file_in.length() == wordLength) {
						curWords.add(file_in);	
					}
				}
			}
			scan.close();
			for (int i = 0; i < wordLength; i++ ) {
				guessedWord.append("-");
			}
		}
		catch(Exception e){
			System.out.println("Invalid file");
			return;
		}
	}
	
	public boolean isAlpha(String name){
		return name.matches("[A-Za-z]+");
	}

	/**
	 * Make a guess in the current 
	 * 
	 * @param guess The character being guessed
	 *
	 * @return The set of strings that satisfy all the guesses made so far
	 * in the game, including the guess made in this call. The game could claim
	 * that any of these words had been the secret word for the whole  
	 * 
	 * @throws GuessAlreadyMadeException If the character <code>guess</code> 
	 * has already been guessed in this 
	 */
	//@Override
	public Set<String> makeGuess(char guess) throws GuessAlreadyMadeException{

		if (Character.isLetter(guess) == false) {
			System.out.println("Invalid input");
			throw new GuessAlreadyMadeException();	
		}
		if (guessedLetters.contains(guess))	{
			System.out.println("You already used that letter");
			throw new GuessAlreadyMadeException();	
		}

		guessedLetters.add(guess);
		buildMap(guess);
		//System.out.println(wordMap + "\n");
		curWords = findBiggest(guess);
		wordMap.clear();

		return curWords;
	}

	public void buildMap(char guess){
		char[] thisWord;
		StringBuilder lettersInWord = new StringBuilder();

		for (String s : curWords) {
			lettersInWord.setLength(0);
			thisWord = s.toCharArray();
			for (int j = 0; j < thisWord.length; j++) {
					if (thisWord[j] == guess) {
					lettersInWord.append(guess);
				}
				else{
					lettersInWord.append("-");
				}
			}
			if (wordMap.get(lettersInWord.toString()) == null){
				Set <String> tempSet = new HashSet<String>();
				tempSet.add(s);
				wordMap.put(lettersInWord.toString(),tempSet);
			}
			else{
				Set <String> tempSet = wordMap.get(lettersInWord.toString());
				tempSet.add(s);
				wordMap.put(lettersInWord.toString(),tempSet);
			}
			fakeWord = s;
		}	
		/*int counter = 0;
		int counter1 = 0;
		for (String key: wordMap.keySet()) {
			System.out.println("Key " + counter + ": " + key);
			System.out.print("\n");
			counter++;
		}
		for (Set<String> key1: wordMap.values()) {
			System.out.println("Value " + counter1 + ": " + key1);
			System.out.print("\n");
			counter1++;
		}*/
	}

	public Set<String> findBiggest(char guess){
		Set <String> biggestSet = new HashSet<>();
		Set <String> curSet = new HashSet<>();
		String curKey = "";
		String biggestKey = "";
		int curCount = 0;
		int biggestCount = 0;
		StringBuilder wordbuilder;
		passedThrough = false;
		numberOfCorrect = 0;

		for (Map.Entry<String,Set<String>> entry : wordMap.entrySet()) {
			curCount = 0;
			biggestCount = 0;
			curSet = entry.getValue();
			curKey = entry.getKey();
			if (curSet.size() > biggestSet.size()) {
				biggestSet = curSet;
				biggestKey = curKey;
			}
			else if (curSet.size() == biggestSet.size()){
				List<Integer> curIndicies = new ArrayList<Integer>();
				List<Integer> biggestIndicies = new ArrayList<Integer>();
				for (int i = 0; i < curKey.length(); i++) {
					if (curKey.charAt(i) == guess) {
						curCount++;
						curIndicies.add(i);
					}
					if (biggestKey.charAt(i) == guess) {
						biggestCount++;
						biggestIndicies.add(i);
					}
				}	
				if (curCount < biggestCount ) {
					biggestSet = curSet;
					biggestKey = curKey;
					//biggestCount = curCount;
				}
				else if (curCount == biggestCount){
					for (int j = curIndicies.size() - 1; j > 0; j--) {
						if (curIndicies.get(j) > biggestIndicies.get(j)) {
							biggestSet = curSet;
							biggestKey = curKey;
							//biggestCount = curCount;
							break;					
						}
					}
				}
			}
		}
			for (int k = 0; k < biggestKey.length(); k++) {
				if (biggestKey.charAt(k) == guess) {
					biggestCount++;
					guessedWord.setCharAt(k, guess);
					passedThrough = true;
					numberOfCorrect++;
				}
			}
			if (guessedWord.toString().contains("-") == false) {
				completedWord = true;
			}
		//numberOfCorrect = biggestCount;
		return biggestSet;
	}

	public void runGame(int guesses, int wordLength){
		boolean invalid = true;
		char guess = 'x';
		boolean firstpass = true;
		guessedLetters.clear();
		wordMap.clear();
		numberOfCorrect = 0;
		completedWord = false;
		fakeWord = "";
		passedThrough = false;


		do{
			invalid = true;
			System.out.println("You have " + guesses + " guesses left");
			System.out.print("Used letters: ");
			if (guessedLetters.size() == 0) {
				System.out.print("\n");
			}
			else{
				Collections.sort(guessedLetters);
				for (int j = 0; j < guessedLetters.size(); j++) {
						if (j < guessedLetters.size() - 1){
							System.out.print(guessedLetters.get(j) + " ");
						}
						else{
							System.out.println(guessedLetters.get(j));	
						}
				}
			}
			//if (guessedWord.toString().equals("")) {
			/*if (firstpass) {
				System.out.print("Word: ");
				for (int i = 0; i < wordLength; i++ ) {
					guessedWord.append("-");
					System.out.print("-");
				}
				System.out.print("\n");
				firstpass = false;
			}
			else {*/
				System.out.println("Word: " + guessedWord.toString());
			//}
			while(invalid){
				System.out.print("Enter guess: ");
				Scanner in = new Scanner(System.in);
				guess = in.next().charAt(0);
				guess = Character.toLowerCase(guess);
				try{
					if (invalid) {
						makeGuess(guess);
					}
					invalid = false;
				} catch(GuessAlreadyMadeException e){
					invalid = true;
				}
			}
			if (passedThrough == false) {
				guesses--;
			}
			if (numberOfCorrect == 0) {
				System.out.println("Sorry, there are no " + guess + "'s" + "\n");
			}
			else if (numberOfCorrect == 1 ){
				System.out.println("Yes, there is 1 " + guess + "\n");
			}
			else{
				System.out.println("Yes, there are " + numberOfCorrect + " " + guess + "'s" + "\n");
			}
		} while(guesses > 0 && completedWord == false);

		if (completedWord == true) {
			System.out.println("Congrats, you won.");
			System.out.println("The word was: " + guessedWord.toString());
		}
		else{
			System.out.println("You lose!");
			System.out.println("The word was: " + fakeWord);
		}
	}
}
