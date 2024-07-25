import java.util.*;

public class EasyComputer extends Player {
    public EasyComputer() {
        this.setPlayerName("Easy AI");
    }

    //return secret number String by Easy AI
    @Override
    public String generateSecretNum() {
        List<Integer> numbers = new ArrayList<>();//Define a list [0123456789];
        for (int i = 0; i < 10; i++) {
            numbers.add(i);
        }
        Collections.shuffle(numbers);//shuffle the elements order of list [0123456789] to make it random.
        String sn = "";
        for (int i = 0; i < this.getNumDigit(); i++) {
            sn += numbers.get(i);//Get the String with the preceding digit number
        }
        return sn;
    }

    public String generateSecretNumGuess() {
        String guess = generateSecretNum();//same with the method of generating secret number.
        System.out.println("Easy AI guess: " + guess);
        return guess;
    }

    public void customizeEasyComp() {
        String[] validNumArr = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10"};//generate a valid input area Array.
        //human set inputTurns
        System.out.println("Please set the maximum allowed turns before the game ends in a draw.");
        System.out.println("It can be integer 1 to 10,including 1 and 10.");//set the number of turns from 1 to 10
        String inputTurns = Keyboard.readInput();
        while (!Arrays.asList(validNumArr).contains(inputTurns)) {//if input number is not 1-10, enter again.
            System.out.println("Please enter an integer from 1- 10, including 1 and 10.");
            inputTurns = Keyboard.readInput();
        }
        this.setGuessTurn(Integer.parseInt(inputTurns));
        //human set inputDigitsNum
        System.out.println("Please set the length of a secret code.");
        System.out.println("It can be integer 1 to 10,including 1 and 10.");
        String inputDigitNum = Keyboard.readInput();
        while (!Arrays.asList(validNumArr).contains(inputDigitNum)) {//if input number is not 1-10, enter again.
            System.out.println("Please enter an integer from 1 - 10, including 1 and 10.");
            inputDigitNum = Keyboard.readInput();
        }
        this.setNumDigit(Integer.parseInt(inputDigitNum));
    }
}
