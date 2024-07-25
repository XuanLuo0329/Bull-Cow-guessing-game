
public abstract class Player {

    //declare relevant variables
    private int numDigit = 4;
    private int guessTurn = 7;
    private String secretNum;
    private String playerName;
    private boolean playerIsWin;

    //constructors
    public Player() {
    }

    //abstract method generate secret number
    public abstract String generateSecretNum();


    //getter and setter
    public int getNumDigit() {
        return numDigit;
    }

    public void setNumDigit(int numDigit) {
        this.numDigit = numDigit;
    }

    public int getGuessTurn() {
        return guessTurn;
    }

    public void setGuessTurn(int guessTurn) {
        this.guessTurn = guessTurn;
    }

    public String getSecretNum() {
        return secretNum;
    }

    public void setSecretNum(String secretNum) {
        this.secretNum = secretNum;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public boolean getIsPlayerWin() {
        return playerIsWin;
    }

    public void setPlayerIsWin(boolean playerIsWin) {
        this.playerIsWin = playerIsWin;
    }

}



