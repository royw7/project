public class WordleRunner {
    public static void main(String[] args) {
         Grid wordleGrid = new Grid();
         Wordle game = new Wordle(wordleGrid);
         game.runGame();
    }
}
