/**
 * This class represents a Color Object
 *
 * @author Roy Wu
 */
public class Color {
    /** Unicode for Green Color */
    public static final String GREEN = "\033[0;32m";
    /** Unicode for Yellow Color */
    public static final String YELLOW = "\033[0;33m";
    /** Unicode for WHITE Color */
    public static final String WHITE = "\033[0;37m";
    /** Color of Object that is to be represented */
    public String color;

    /**
     * Instantiates a Color object.
     *
     * @param colour The color
     */
    public Color(String colour) {
       if(colour.toUpperCase().equals("G")) {
           color = "GREEN";
       }else if(colour.toUpperCase().equals("Y")) {
           color = "YELLOW";
       }else {
           color = "WHITE";
       }
    }

    /**
     * Returns the unicode for the color represented by checking color variable
     *
     * @return The unicode equivalent for the color represented
     */
    public String getColor() {
        if(color.equals("GREEN")) {
            return GREEN;
        }else if(color.equals("YELLOW")) {
            return YELLOW;
        }else {
            return WHITE;
        }
    }

}
