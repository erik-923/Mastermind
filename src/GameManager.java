import java.util.Scanner;

public class GameManager {


    private int guessNum;
    private Solution currSolution;
    private Scanner scan;
    private History hist;

    // constructor for the game manager
    public GameManager(){
        guessNum = 0;
        currSolution = new Solution();
        scan = new Scanner(System.in);
        hist = new History();
    }


    public void play(){

        // asks about entering developer mode which shows solution before playing for development
        System.out.print("Run in developer mode? (y/n): ");
        if (scan.nextLine().equals("y")){
            System.out.println(currSolution);
        }

        // asks player about using AI mode which automatically guesses the solution
        // otherwise calls the player input method for manual input
        System.out.print("Run in AI mode? (y/n): ");
        if(scan.nextLine().equals("y")){
            takeAiInput();
        } else {
            takePlayerInput();
        }
    }

    // method does all the main game functions
    public void takePlayerInput(){
        Guess currGuess = new Guess();
        String input = "";
        // creates a loop that asks for input and call the evaluate function and prints the feedback
        while (!evaluate(currGuess.getPattern(), currSolution.getPattern()).equals("hhhh") && guessNum < 10) {
            // takes input
            System.out.print("Make your guess (or type \"history\"): ");
            input = scan.nextLine();
            // prints history if input is history
            if (input.equals("history")) {
                hist.printHistory();
            } else {
                // otherwise calls the evaluate function and prints feedback
                currGuess = new Guess(input);
                // checks for invalid input, and prints Invalid Input Try Again
                if (!currGuess.toString().equals("Invalid Input")) {
                    System.out.println("Code: " + currGuess + " Feedback: " + evaluate(currGuess.getPattern(), currSolution.getPattern()));
                    hist.addGuess("Code: " + currGuess + " Feedback: " + evaluate(currGuess.getPattern(), currSolution.getPattern()));
                    guessNum++;
                } else{
                    System.out.println("\nInvalid Input\nTry Again\n");
                }
            }
        }
        // Loop ends with either greater than 10 guesses or a correct solution
        // If the last guess was correct it prints the message for a correct solution
        // Otherwise prints that the answer was incorrect
        if (evaluate(currGuess.getPattern(), currSolution.getPattern()).equals("hhhh")){
            System.out.println("\nYou won after only " + guessNum + " guesses!");
        } else {
            System.out.println("\nSorry, you did not guess the answer after 10 guesses.");
        }
        System.out.println("The answer was: " + currSolution);
    }


    // The method used when AI mode is selected
    public void takeAiInput(){
        Ai bot = new Ai();
        Guess currGuess = new Guess("rrgg");

        // Creates a loop that iterates while there have been fewer than 10 guesses and the answer is incorrect
        while (!evaluate(currGuess.getPattern(), currSolution.getPattern()).equals("hhhh") && guessNum < 10) {
            System.out.println("Code: " + currGuess + " Feedback: " + evaluate(currGuess.getPattern(), currSolution.getPattern()));
            // As long as it isn't the first guess call the AI removeOptions method
            bot.removeOptions(evaluate(currGuess.getPattern(), currSolution.getPattern()), currGuess.getPattern());

            // Make an automated guess
            currGuess = new Guess(bot.makeGuess());
            guessNum++;

        }

        // Prints whether the AI won or lost the game along with the correct solution
        if (evaluate(currGuess.getPattern(), currSolution.getPattern()).equals("hhhh")){
            System.out.println("\nYou won after only " + guessNum + " guesses!");
        } else {
            System.out.println("\nSorry, you did not guess the answer after 10 guesses.");
        }
        System.out.println("The answer was: " + currSolution);
    }

    // Method evaluates a guess and creates the hits and misses
    public String evaluate(String gs, String sln){
        String[] solution = sln.split("");
        String[] guess = gs.split("");
        int hits = 0;
        int misses = 0;

        // finds hits
        for (int i = 3; i >= 0; i--) {
            if (solution[i].equals(guess[i])) {
                hits += 1;
                solution[i] = "";
                guess[i] = "";
            }
        }

        // Finds misses
        for (int g = 0; g < 4; g++) {
            for (int s = 0; s < 4; s++){
                if (guess[g].equals(solution[s]) && !guess[g].equals("")){
                    misses++;
                    guess[g] = "";
                    solution[s] = "";
                }
            }
        }
        return arrangeHits(hits, misses);
    }

    // creates the string for hits and misses
    public String arrangeHits(int hits, int misses){
        String result = "";

        for (int i = 0; i < hits; i++){
            result += "h";
        }
        for (int i = 0; i < misses; i++){
            result += "m";
        }

        return result;
    }

}
