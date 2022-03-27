/**
 * Runner Class for Wordle Game
 *
 * @author Roy Wu
 */
public class WordleRunner {

    /** Main method that creates new Grid object, Wordle object, and runs the Wordle game.
     *
     * @param args String[] args
     */
    public static void main(String[] args) {
         Grid wordleGrid = new Grid();
         Wordle game = new Wordle(wordleGrid);
         game.runGame();
    }
}
