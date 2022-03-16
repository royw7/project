public class Grid {
    String[][] wordGrid; //list that will contain player's guesses
    String[][] colorGrid;
    public static final String GREEN = "\033[0;32m";   // GREEN
    public static final String YELLOW = "\033[0;33m";  // YELLOW
    public static final String WHITE = "\033[0;37m";   // WHITE
    public static final String PURPLE = "\033[0;35m";  // PURPLE

    // set word grid to 6 rows and 5 columns
    public Grid(){
        wordGrid = new String[][]{
                {" ", " ", " ", " ", " "},
                {" ", " ", " ", " ", " "},
                {" ", " ", " ", " ", " "},
                {" ", " ", " ", " ", " "},
                {" ", " ", " ", " ", " "},
                {" ", " ", " ", " ", " "},

        };
        colorGrid = new String[][]{
                {"", " ", " ", " ", " "},
                {" ", " ", " ", " ", " "},
                {" ", " ", " ", " ", " "},
                {" ", " ", " ", " ", " "},
                {" ", " ", " ", " ", " "},
                {" ", " ", " ", " ", " "},
        };
    }

    public void updateGrid(String word, int row){
       int numGuess = row;
       String[] wordList = Wordle.characterToList(word);
       for(int i = 0; i < 5; i++) {
           wordGrid[numGuess][i] = wordList[i];
       }
    }

    public void updateColorGrid(String word, int row){
        int numGuess = row;
        String[] wordList = Wordle.characterToList(word);
        for(int i = 0; i < 5; i++) {
            colorGrid[numGuess][i] = wordList[i];
        }
    }
    public void printGrid(){
        String toDisplay = "[";
        for(int i = 0; i < 6; i++){
            for(int j = 0; j < 5; j++){
                if(colorGrid[i][j].equals("G")){
                    toDisplay += GREEN + wordGrid[i][j] + ", ";
                }
                else if(colorGrid[i][j].equals("Y")){
                    toDisplay += YELLOW + wordGrid[i][j] + ", ";
                }else{
                    toDisplay += WHITE + wordGrid[i][j] + ", ";
                }
            }
            toDisplay += "\n";
        }
        toDisplay += "]";
        System.out.println(toDisplay);
    }
    public String[][] returnGrid(){
        return wordGrid;
    }

}
