import java.util.ArrayList;

public class Ai {

    private ArrayList<String> options;
    private GameManager game;

    // constructor for the AI
    public Ai(){
        options = new ArrayList<>();
        generateOptions();
        game = new GameManager();
    }

    // generates all possible combinations of key pegs
    public void generateOptions(){
        String[] colors = {"r", "g", "y", "a", "b", "p"};

        for (int a = 0; a < 6; a++){
            for (int b = 0; b < 6; b++){
                for (int c = 0; c < 6; c++){
                    for (int d = 0; d < 6; d++){
                        String temp = colors[a] + colors[b] + colors[c] + colors[d];
                        options.add(temp);
                    }
                }
            }
        }
    }

    // picks a random guess from the ArrayList of possibilities
    public String makeGuess() {
        String bestGuess = null;
        int minMaxElimination = Integer.MAX_VALUE;

        for (String guess : options) {
            int maxElimination = calculateMaxElims(guess, guess);
            if (maxElimination < minMaxElimination) {
                minMaxElimination = maxElimination;
                bestGuess = guess;
            }
        }

        return bestGuess;
    }

    private int calculateMaxElims(String guess, String remainingOption) {
        int maxEliminations = 0;

        for (String remaining : options) {
            String feedback = game.evaluate(guess, remaining);
            if (!feedback.equals(game.evaluate(guess, remainingOption))) {
                maxEliminations++;
            }
        }

        return maxEliminations;
    }

    // removes the options that can't be the solution based on the feedback
    public void removeOptions(String feedback, String guess){
        for (int i = options.size() - 1; i >= 0; i--){
            if (!game.evaluate(guess, options.get(i)).equals(feedback)){
                options.remove(i);
            }
        }
    }

}
