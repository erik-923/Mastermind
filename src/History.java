import java.util.ArrayList;

public class History {
    private ArrayList<String> history;

    public History(){
        history = new ArrayList<>();
    }

    // adds a guess to the ArrayList for the history
    public void addGuess(String guess){
        history.add(guess);
    }

    // prints out the history
    public void printHistory(){
        System.out.println();
        for (String str : history){
            System.out.println(str);
        }
        System.out.println();
    }
}
