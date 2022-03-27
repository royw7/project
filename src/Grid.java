/**
 * This class represents a Grid Object
 *
 * @author Roy Wu
 */


public class Grid {
    /** String 2D Array that will contain the player's guesses */
    String[][] wordGrid;
    /** Color 2D Array that will contain the colors of the player's guesses */
    Color[][] colorGrid;

    /** Instantiates a Grid object. */
    public Grid(){
        wordGrid = new String[][] {
                {" ", " ", " ", " ", " "},
                {" ", " ", " ", " ", " "},
                {" ", " ", " ", " ", " "},
                {" ", " ", " ", " ", " "},
                {" ", " ", " ", " ", " "},
                {" ", " ", " ", " ", " "},

        };
        Color W = new Color("W");
        colorGrid = new Color[][]{
                {W, W, W, W, W},
                {W, W, W, W, W},
                {W, W, W, W, W},
                {W, W, W, W, W},
                {W, W, W, W, W},
                {W, W, W, W, W},
        };
    }

    /**
     * Sets a row of wordGrid to a word
     *
     * @param word The word to be split into characters and added to a row of the 2D array
     * @param row The row of wordGrid to be updated
     */
    public void updateGrid(String word, int row) {
       int numGuess = row;
       String[] wordList = Wordle.characterToList(word);
       for(int i = 0; i < 5; i++) {
           wordGrid[numGuess][i] = wordList[i];
       }
    }

    /**
     * Sets a row of colorGrid to a new row of colors.
     *
     * @param colors Array of colors to be added
     * @param row The row of  colorGrid to be updated
     */
    public void updateColorGrid(Color[] colors, int row) {
            colorGrid[row] = colors;
    }

    /** Prints wordGrid in a readable form with colors accounted for. */
    public void printGrid() {
        String toDisplay = "[";
        for(int i = 0; i < 6; i++) {
            for(int j = 0; j < 5; j++) {
                toDisplay += colorGrid[i][j].getColor() + wordGrid[i][j] + ", ";
            }
            toDisplay += "\n";
        }
        toDisplay += "]";
        System.out.println(toDisplay);
    }


}
