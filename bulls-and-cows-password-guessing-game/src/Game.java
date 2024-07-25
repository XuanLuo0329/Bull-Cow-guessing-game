import java.util.ArrayList;
import java.io.*;

public class Game {

    private String compSecretNum = "";
    private String humanSecretNum = "";

    private String compGuess = "";
    private String humanGuess = "";

    private int round = 1;

    //create relevant objects. 4 players and 1 number modifier.
    EasyComputer easyComputer = new EasyComputer();
    MediumComputer mediumComputer = new MediumComputer();
    HardComputer hardComputer = new HardComputer();
    Human human = new Human();
    NumberModifier numberModifier = new NumberModifier();

    //arraylist of guesses
    ArrayList<String> humanGuessList = new ArrayList<>();
    ArrayList<String> compGuessList = new ArrayList<>();

    public static void main(String[] args) {
        Game game = new Game();
        game.start();
    }

    public void start() {
        //ask human to enter names
        System.out.println("============================Bull&Cow Game===================================");
        System.out.println("Hello human! Welcome to Bull&Cow Game. Please enter your name:");
        human.setPlayerName(Keyboard.readInput());//it is ok to have everything for human name. No exception check.
        System.out.println("=============================Mode choose====================================");
        System.out.println("Hi " + human.getPlayerName() + ", please choose game difficulty.");
        System.out.println("Easy mode, enter 1. Medium mode, enter 2. Hard mode, enter 3.");//choose a mode.
        String inputMode = Keyboard.readInput();
        int mode = human.checkMode(inputMode);//check if the input is valid.
        if (mode == 1) {
            easyComputer.customizeEasyComp();//if choose easy mode, human player needs to set digits and turns.
            human.setNumDigit(easyComputer.getNumDigit());//update human NumDigit;
            human.setGuessTurn(easyComputer.getGuessTurn());//update human GuessTurn;
        }
        System.out.println("=========================Secret number setting==============================");
        System.out.println("Please enter your secret number:");
        System.out.println("Secret number is a " + human.getNumDigit() + " digits number. Each digit is different from others. The first digit can be 0.");
        humanSecretNum = human.generateSecretNum();
        human.setSecretNum(humanSecretNum);//set human secret number.
        //generate computer secret number using generateSecretNum method in each computer object
        if (mode == 1) {
            compSecretNum = easyComputer.generateSecretNum();
        }
        if (mode == 2) {
            compSecretNum = mediumComputer.generateSecretNum();
        }
        if (mode == 3) {
            compSecretNum = hardComputer.generateSecretNum();
        }
        System.out.println("Your secret number has been assigned to " + human.getSecretNum() + " successfully!");
        System.out.println("==========================Guess way choose==================================");
        human.chooseHumanGuessWay();
        System.out.println("============================let's begin!====================================");
        for (int i = human.getGuessTurn(); i > 0; i--) {
            System.out.println("Round " + round);
            System.out.println("You have " + (human.getGuessTurn() - round + 1) + " times guesses left.");
            //human guess and get result
            humanGuess = human.generateSecretNumGuess();// Assign human guesses secret number to a variable
            humanGuessList.add(humanGuess);//add human guess number to human guess list
            numberModifier.compareGuessNumber(humanGuess, compSecretNum, human);//get human bulls and cows number and game results
            if (human.getIsPlayerWin()) {
                System.out.println("Congratulations! You did a good job.");
                break;//if human win, game over.
            }
            //AI guess and get bulls and cows number and game results
            if (mode == 1) {
                compGuess = easyComputer.generateSecretNumGuess();
                numberModifier.compareGuessNumber(compGuess, human.getSecretNum(), easyComputer);
            }
            if (mode == 2) {
                compGuess = mediumComputer.generateSecretNumGuess();
                numberModifier.compareGuessNumber(compGuess, human.getSecretNum(), mediumComputer);
            }
            if (mode == 3) {
                compGuess = hardComputer.generateSecretNumGuess(human.getSecretNum());
                numberModifier.compareGuessNumber(compGuess, human.getSecretNum(), hardComputer);
            }
            compGuessList.add(compGuess);//add computer guess number to computer guess list
            if (easyComputer.getIsPlayerWin() || mediumComputer.getIsPlayerWin() || hardComputer.getIsPlayerWin()) {
                System.out.println("Oh no! You have been defeat. Good luck next time!");
                break;//if AI win, game over.
            }
            System.out.println("Round " + round + " is over!");
            if (round == human.getGuessTurn()) {
                System.out.println("Game over! You and computer have played a draw.");
                System.out.println("====================================End==========================================");
                break;
            }//If the last guess turn finished, it comes to a draw.
            round++;
            System.out.println("==============================================================================");
        }
        saveFile();//save all the result to a file.
    }

    public void saveFile() {
        System.out.println("Do you want to save all the game data to a file? Yes, enter 1. No, enter 2.");
        String save = Keyboard.readInput();
        int turn = 1;
        while (true) {
            if (save.equals("1") || save.equalsIgnoreCase("yes")) {
                System.out.println("Please enter a file name.");
                String fileName = Keyboard.readInput();
                String path = fileName + ".txt";
                File saveFile = new File(path);
                try (BufferedWriter bw = new BufferedWriter(new FileWriter(saveFile))) {
                    bw.write("Bulls & Cows game result.");
                    bw.write("\n");
                    bw.write("Your code: " + humanSecretNum);
                    bw.write("\n");
                    bw.write("Computer's code: " + compSecretNum);
                    bw.write("\n");
                    while (turn <= human.getGuessTurn()) {
                        bw.write("-----");
                        bw.write("\n");
                        bw.write("Turn " + turn + ":");
                        bw.write("\n");
                        //get human bull and cow number in this round
                        int[] humanBullCow = numberModifier.generateBullCowNum(humanGuessList.get(turn - 1), compSecretNum);
                        int humanBull = humanBullCow[0];
                        int humanCow = humanBullCow[1];
                        bw.write("you guessed " + humanGuessList.get(turn - 1) + ", scoring " + humanBull + " bulls and " + humanCow + " cows");
                        bw.write("\n");
                        if (humanBull == human.getNumDigit()) {
                            bw.write("-----");
                            bw.write("\n");
                            bw.write("You win!^_^");
                            bw.write("\n");
                            return;
                        }
                        //get comp this round bow and cow number
                        int[] compBullCow = numberModifier.generateBullCowNum(compGuessList.get(turn - 1), humanSecretNum);
                        int compBull = compBullCow[0];
                        int compCow = compBullCow[1];
                        bw.write("Computer guessed " + compGuessList.get(turn - 1) + ", scoring " + compBull + " bulls and " + compCow + " cows");
                        bw.write("\n");
                        if (compBull == human.getNumDigit()) {
                            bw.write("-----");
                            bw.write("\n");
                            bw.write("Computer win!T_T");
                            bw.write("\n");
                            return;
                        }
                        turn++;
                        if (turn > human.getGuessTurn()) {
                            bw.write("-----");
                            bw.write("\n");
                            bw.write("You and computer have played a draw.");
                            bw.write("\n");
                            return;
                        }
                    }
                } catch (IOException e) {
                    System.out.println("Error: " + e.toString());
                }
                return;
            } else if (save.equals("2") || save.equalsIgnoreCase("no")) {
                System.out.println("Good Bye!");
                return;
            } else {
                System.out.println("please enter number 1 or 2.");
                save = Keyboard.readInput();
            }
        }


    }
}
