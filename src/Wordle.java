import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

public class Wordle {
    String theWord; //word player has to guess
    boolean wordGuessed; //determines if player has guessed the word
    boolean gameLost;
    public int numGuesses;
    Grid grid;
    Scanner scan;
    ArrayList<String> allowedGuesses;

    public Wordle(Grid grid){
        ArrayList<String> possibleAnswers = new ArrayList<>();
        loadWordsInto(possibleAnswers);
        theWord = possibleAnswers.get((int) Math.random() * possibleAnswers.size()); // set word to be guessed
        allowedGuesses = new ArrayList<>();
        loadWordsInto2(allowedGuesses);
        numGuesses = 0;
        gameLost = false;
        wordGuessed = false;
        scan = new Scanner(System.in);
        this.grid = grid;
    }

    public void runGame(){
        while(gameLost == false && wordGuessed == false){
            grid.printGrid();
            System.out.println("Guess a word");
            boolean allowedGuess = false;
            String userGuess = "";
            while(allowedGuess == false){
                userGuess = scan.nextLine();
                if(!isAllowedGuess(userGuess.toLowerCase())){
                    System.out.println("Not a Word");
                }else{
                    allowedGuess = true;
                }
            }
            userGuess = userGuess.toUpperCase();

            grid.updateColorGrid(checkColor(userGuess), numGuesses);
            grid.updateGrid(userGuess, numGuesses);
            System.out.println(checkColor(userGuess));
            grid.printGrid();
            if(isCorrectWord(userGuess)){
                wordGuessed = true;
                System.out.println("you win");
            }
            numGuesses++;
            if(numGuesses == 5){
                gameLost = true;
            }
       }
    }

    public boolean isAllowedGuess(String guess){
        if(guess.length() == 5){
            for(String word : allowedGuesses){
                if(guess.equals(word)){
                    return true;
                }
            }
        }
        return false;
    }

    public String checkColor(String word){
       String[] guess = characterToList(word);
       String[] rightWord = characterToList(theWord);
       String returnString = "";
        boolean wasShown = false;
        boolean rightPosition = false;
       for(int i = 0; i < 5; i++){
           wasShown = false;
           rightPosition = false;
           for(int j = 0; j < 5; j++){
               if(guess[i].equals(rightWord[j]) && i == j){
                    rightPosition = true;
               }
               else if(guess[i].equals(rightWord[j])){
                   wasShown = true;
               }
           }
           if(rightPosition == true){
               returnString += "G";//GREEN + guess[i];
           }else if(wasShown == true){
               returnString += "Y";//YELLOW + guess[i];
           }else {
               returnString += "W";//WHITE + guess[i];
           }
       }
       return returnString;
    }

    public boolean isCorrectWord(String word){
        if(word.equals(theWord)){
            wordGuessed = true;
        }
        return wordGuessed;
    }

    //breaks word into character list
    public static String[] characterToList(String word){
        String[] wordString = new String[5];
        for(int i = 0; i < 5; i++){
            wordString[i] = word.substring(i, i + 1);
        }
        return wordString;
    }

    public int getNumGuesses(){
        return numGuesses;
    }
    public String getTheWord(){
        return theWord;
    }

    public static void loadWordsInto(ArrayList<String> wordList)
    {
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

    public static void loadWordsInto2(ArrayList<String> wordList)
    {
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



