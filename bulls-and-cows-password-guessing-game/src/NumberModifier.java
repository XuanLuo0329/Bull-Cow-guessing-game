
public class NumberModifier {

    private int bullNum;
    private int cowNum;

    public void compareGuessNumber(String guess, String secretNum, Player player) {
        generateBullCowNum(guess, secretNum);
        System.out.println("Result: " + this.bullNum + " bulls and " + this.cowNum + " cows");
        if (this.bullNum == secretNum.length()) {//if the player get right guess, set the player's isWin variable to true.
            System.out.println(player.getPlayerName() + ", you win!");
            player.setPlayerIsWin(true);
        }
    }

    public int[] generateBullCowNum(String guess, String secretNum) {
        int[] bullCowResultArr = new int[2];
        this.bullNum=0;//initial bull and cow number to be 0
        this.cowNum=0;
        for (int i = 0; i < secretNum.length(); i++) {
            if (guess.charAt(i) == secretNum.charAt(i)) {
                this.bullNum++;
            } else if (secretNum.contains(String.valueOf(guess.charAt(i)))) {
                this.cowNum++;
            }
        }
        bullCowResultArr[0] = this.bullNum;
        bullCowResultArr[1] = this.cowNum;
        return bullCowResultArr;
    }
}
