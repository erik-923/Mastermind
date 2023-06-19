public class Solution extends KeyPegs{

    // default constructor
    public Solution(){
        super();
        super.setPattern(generateCode());

    }

    // generates a random string of colors for the solution
    public String generateCode(){
        String[] colors = {"r", "y", "g", "a", "p", "b"};
        int randResult = (int)(Math.random() * 6); // generates random number from 0 to 5, inclusive
        String temp = "";

        // chooses 4 random elements of the colors and adds them to the pattern
        for (int i = 0; i < 4; i++){
            temp += colors[randResult];
            randResult = (int)(Math.random() * 6);
        }
        return temp;
    }


}
