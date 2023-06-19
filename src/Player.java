public class Player {

    // This is the main player class which calls the opening message and then creates a new game manager
    public static void main(String[] args){
        openingMessage();
        GameManager game = new GameManager();
        game.play();
        closingMessage();
    }

    public static void openingMessage(){
        System.out.println("Welcome to Mastermind! Become a codebreaker!");
        System.out.println("Your computer opponent has created a secret code.");
        System.out.println("You have 10 guesses to break it! Create your guess");
        System.out.println("by choosing any combination of 4 from the colors listed");
        System.out.println("(repeats are allowed)\n");
        System.out.println("Colors: r (red), y (yellow), g (green), a (gray), p (purple), b (blue)");
        System.out.println("EX: To guess red,red,blue,purple, enter rrbp\n\n");
    }

    public static void closingMessage(){
        System.out.println("\n\nCSC 2300 Project 3 - Erik Blix");
    }


}
