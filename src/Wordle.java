import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

/**
 * This class represents a Wordle Object
 *
 * @author Roy Wu
 */
public class Wordle {
    /** The word that the player will have to guess */
    String theWord;
    /** Determines if the player have guessed the word */
    boolean wordGuessed;
    /** Determines if the player lost */
    boolean gameLost;
    /** Tracks how many guesses the player has took */
    public int numGuesses;
    /** Stores a grid object */
    Grid grid;
    /** Scanner object */
    Scanner scan;
    /** ArrayList that stores all possible legal words that the player can guess */
    ArrayList<String> allowedGuesses;

    /** Instantiates a Grid object.
     *
     * @param grid grid object to show Wordle game
     */
    public Wordle(Grid grid){
        ArrayList<String> possibleAnswers = new ArrayList<>();
        loadAnswers(possibleAnswers);
        theWord = possibleAnswers.get((int) (Math.random() * possibleAnswers.size())).toUpperCase(); // set word to be guessed
        allowedGuesses = new ArrayList<>();
        loadAnswers(allowedGuesses);
        loadExtraWords(allowedGuesses);
        numGuesses = 0;
        gameLost = false;
        wordGuessed = false;
        scan = new Scanner(System.in);
        this.grid = grid;
    }

    /** Runs the Wordle game; Contains all the logic for the Wordle game */
    public void runGame(){
        while(gameLost == false && wordGuessed == false) {
            grid.printGrid();
            System.out.println("Guess a word");
            boolean allowedGuess = false;
            String userGuess = "";
            while(allowedGuess == false || userGuess.length() != 5) {
                userGuess = scan.nextLine();
                if(userGuess.length() != 5){
                    System.out.println("Not a 5 letter word");
                }else if(!isAllowedGuess(userGuess.toLowerCase())) {
                    System.out.println("Not a Word");
                }else {
                    allowedGuess = true;
                }
            }
            userGuess = userGuess.toUpperCase();

            grid.updateColorGrid(checkColor(userGuess), numGuesses);
            grid.updateGrid(userGuess, numGuesses);
            grid.printGrid();

            if(isCorrectWord(userGuess)) {
                wordGuessed = true;
                System.out.println("you win");
            }
            numGuesses++;
            if(numGuesses == 6) {
                gameLost = true;
                System.out.println("You Lost, the word was " + theWord);
            }
       }
    }

    /** Checks to see if the word is a legal word
     *
     * @param guess Word to be checked
     * @return If the word is a legal word
     */
    public boolean isAllowedGuess(String guess) {
            for(String word : allowedGuesses) {
                if(guess.equals(word)) {
                    return true;
                }
            }
        return false;
    }

    /** Gets the color for each character in a word. Yellow if the character is
     * in theWord, green if the character is in theWord and the same index
     * <p>
     * PRECONDITION: word is a 5 letter word.
     *
     * @param word word to be checked
     * @return color array with each element representing a color for each character from word
     */
    public Color[] checkColor(String word) {
       String[] guess = characterToList(word);
       String[] rightWord = characterToList(theWord);
       Color[] colorList = new Color[5];

        boolean wasShown = false;
        boolean rightPosition = false;
       for(int i = 0; i < 5; i++) {
           wasShown = false;
           rightPosition = false;
           for(int j = 0; j < 5; j++) {
               if(guess[i].equals(rightWord[j]) && i == j) {
                    rightPosition = true;
               }
               else if(guess[i].equals(rightWord[j])) {
                   wasShown = true;
               }
           }
           if(rightPosition == true) {
               colorList[i] = new Color("G");
           }else if(wasShown == true) {
               colorList[i] = new Color("Y");;
           }else {
               colorList[i] = new Color("W");
           }
       }
       return colorList;
    }

    /** Checks to see if the word is the right answer
     *
     * @param word the word to be checked
     * @return If the word is the right answer
     */
    public boolean isCorrectWord(String word) {
        if(word.equals(theWord)) {
            wordGuessed = true;
        }
        return wordGuessed;
    }

    /** Static method that turns a word into a character array
     * <p>
     * PRECONDITION: word parameter is a 5 letter word
     *
     * @param word word to be turned into a character array
     * @return character array of given word
     */
    public static String[] characterToList(String word) {
        String[] wordString = new String[5];
        for(int i = 0; i < 5; i++) {
            wordString[i] = word.substring(i, i + 1);
        }
        return wordString;
    }

    /** get the current number of guesses
     *
     * @return the number of guesses
     */
    public int getNumGuesses() {
        return numGuesses;
    }

    /** gets the word that user is supposed to guess
     *
     * @return the word that the user is supposed to guess
     */
    public String getTheWord() {
        return theWord;
    }

    /** Loads wordle-nyt-answers-alphabetical.txt(txt file containing possible answers) into an ArrayList
     *
     * @param wordList ArrayList to load wordle-nyt-answers-alphabetical.txt into
     */
    public static void loadAnswers(ArrayList<String> wordList) {
        try
        {
            Scanner input = new Scanner(new File("src\\wordle-nyt-answers-alphabetical.txt"));
            while (input.hasNext())
            {
                String word = input.next();
                wordList.add(word);
            }
        }
        catch (FileNotFoundException ex)
        {
            ex.printStackTrace();
        }
    }

    /** Loads wordle-nyt-allowed-guesses.txt(txt files containing extra possible guesses) into a ArrayList.
     *
     * @param wordList ArrayList to be loaded into
     */
    public static void loadExtraWords(ArrayList<String> wordList) {
        try
        {
            Scanner input = new Scanner(new File("src\\wordle-nyt-allowed-guesses.txt"));
            while (input.hasNext())
            {
                String word = input.next();
                wordList.add(word);
            }
        }
        catch (FileNotFoundException ex)
        {
            ex.printStackTrace();
        }
    }
}



