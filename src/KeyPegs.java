import java.util.HashMap;
import java.util.HashSet;

public class KeyPegs {

    protected HashMap<String, String> colorMatch;
    private String pattern;
    protected String reset = "\u001B[0m";

    // default constructor
    public KeyPegs(){
        this("xxxx");
    }

    // custom constructor
    public KeyPegs(String pattern){
        setPattern(pattern);

        colorMatch = new HashMap<>();
        colorMatch.put("r", "\u001B[31m");
        colorMatch.put("g", "\u001B[32m");
        colorMatch.put("y", "\u001B[33m");
        colorMatch.put("b", "\u001B[34m");
        colorMatch.put("p", "\u001B[35m");
        colorMatch.put("a", "\u001B[37m");
    }

    // setter access for the pattern field
    // validates length of the input
    public void setPattern(String pattern) {

        if (pattern.length() != 4){
            System.out.println("Invalid Input");
            pattern = "xxxx";
        }
        this.pattern = pattern;
    }

    // toString method for printing the pattern as colored squares
    public String toString(){
        String str = "";
        // checks for invalid colors and changes the string to Invalid Input if it is invalid
        for (int i = 0; i < 4; i++){
            String letter = getPattern().substring(i, i + 1);
            if (colorMatch.get(letter) == null){
                str = "Invalid Input";
            }else if (!str.equals("Invalid Input")) {
                str += colorMatch.get(letter) + "\u25A0" + reset;
            }
        }

        return str;
    }

    public String getPattern() {
        return pattern;
    }
}
