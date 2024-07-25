import java.io.*;
import java.lang.Exception;
import java.util.ArrayList;

public class Human extends Player {

    private int humanGuessWay = 0;
    private int round = 1;
    private String path;

    public void chooseHumanGuessWay() {
        System.out.println("please choose your way of guessing.");
        System.out.println("Guess by yourself, enter 1. Obtain guesses from a file, enter 2");
        while (true) {
            String guessWay = Keyboard.readInput();
            if (guessWay.equals("1")) {
                System.out.println("Guess by yourself? You can make it!");
                this.humanGuessWay = 1;
                break;
            } else if (guessWay.equals("2")) {
                System.out.println("Guess by file? Maybe you want to take it easy.");
                this.humanGuessWay = 2;
                System.out.println("Please enter the file name.");
                path = (Keyboard.readInput()).toLowerCase() + ".txt";
                //transfer input to lower case to accept input with right characters but wrong case
                File inputFile = new File(path);
                while (!inputFile.exists()) {
                    System.out.println("The file is not exist. Please enter an existing file name.");
                    path = (Keyboard.readInput()).toLowerCase() + ".txt";
                    inputFile = new File(path);
                }
                System.out.println("you have chosen to use the guesses in the " + path);
                break;
            } else {
                System.out.println("please enter 1 or 2.");
            }
        }
    }

    @Override
    public String generateSecretNum() {
        String sc = Keyboard.readInput();
        return checkSecretNumFormant(sc);
    }

    //return guess number from human
    public String generateSecretNumGuess() {
        String guess = null;
        if (this.humanGuessWay == 1) {//choose to guess by self,guess is input value
            guess = getInputGuess();
        } else if (this.humanGuessWay == 2) {//obtain guesses from current file
            guess = getFileGuess(path);
        }
        System.out.println("You guess: " + guess);
        return guess;
    }

    //when human choose to guess by self, the guess number can be generated by this method
    public String getInputGuess() {
        System.out.println("Please enter your guess:");
        //use this method to check the validation of the input secret number.
        String humanInputGuess = Keyboard.readInput();
        return checkSecretNumFormant(humanInputGuess);
    }

    //return guess number from existing file.
    public String getFileGuess(String path) {
        String line;
        String fileNum;
        ArrayList<String> fileGuessList = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(new File(path)))) {
            while ((line = reader.readLine()) != null) {
                fileGuessList.add(line);
            }
        } catch (IOException e) {
            System.out.println("Error: " + e.toString());
        }
        if (round > fileGuessList.size()) {
            System.out.println("No more guess number in the file.");
            fileNum = getInputGuess();
        } else {
            fileNum = fileGuessList.get(round - 1);
        }
        round++;
        return fileNum;
    }

    //this method is used for checking the validation of the input secret number.
    //Reuse each time when a new secret number come up from human. Including reading file.
    public String checkSecretNumFormant(String inputNum) {
        while (true) {
            if (inputNum.length() != getNumDigit()) {
                System.out.println("Your number's digits is not " + getNumDigit() + ". Please enter a " + getNumDigit() + " digits number.");
                inputNum = Keyboard.readInput();
            } else {
                try {
                    int num = Integer.parseInt(inputNum);
                } catch (Exception e) {
                    System.out.println("Please enter a secret number without character. ");
                    inputNum = Keyboard.readInput();
                    continue;
                }
                if (numberHasDuplicate(Integer.parseInt(inputNum))) {
                    System.out.println("Please enter a secret number with no same digits.");
                    inputNum = Keyboard.readInput();
                } else {
                    return inputNum;
                }
            }
        }
    }

    public boolean numberHasDuplicate(int num) {
        boolean[] used = new boolean[10]; // initial 0-9 has not used
        for (int i = 0; i < getNumDigit(); i++) {
            int digit = num % 10; // get last digit num
            if (used[digit]) { // if digit has used, the used[digit] has already been changed to true.
                return true;
            } else {
                used[digit] = true; // change the used[digit] to true
                num /= 10;
            }
        }
        return false; // if not return true, it means no duplicate digits.return false.
    }

    //method for check the mode choosing input validation. It is needed to enter again until input value is valid.
    //return number that indicates the chosen computer.
    public int checkMode(String inputMode) {
        String mode = inputMode;
        while (true) {
            //enter 1 or easy means choose easy AI.
            if (mode.equals("1") || mode.equalsIgnoreCase("easy")) {
                System.out.println("You have chosen the Easy Mode. Enjoy your game!");
                return 1;
                //enter 2 or medium means choose medium AI.
            } else if (mode.equals("2") || mode.equalsIgnoreCase("medium")) {
                System.out.println("Oh,you have chosen the Medium Mode! It seems you want a little bit challenge.");
                return 2;
                //enter 3 or hard means choose hard AI.
            } else if (mode.equals("3") || mode.equalsIgnoreCase("hard")) {
                System.out.println("Hey genius! You have chosen the Hard Mode. Good luck!");
                return 3;
            } else {//invalid input is needed to enter again
                System.out.println("please enter number from 1-3.");
                mode = Keyboard.readInput();
            }
        }
    }
}
