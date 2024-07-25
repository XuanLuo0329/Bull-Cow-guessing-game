import java.util.ArrayList;
import java.util.Iterator;

public class HardComputer extends Player {

    private String guess;
    ArrayListGenerator arrayListGenerator = new ArrayListGenerator();
    ArrayList<String> originList = arrayListGenerator.generateOriginList("");

    public HardComputer() {
        this.setPlayerName("Hard AI");
    }

    //return guess number by Hard AI
    public String generateSecretNumGuess(String humanSecretNumber) {
        NumberModifier numberModifier = new NumberModifier();
        if (this.getGuessTurn() == 7) {//if this is the first time Hard AI guess
            guess = generateSecretNum();
        } else {
            //get last time result
            int lastBull = numberModifier.generateBullCowNum(guess, humanSecretNumber)[0];
            int lastCow = numberModifier.generateBullCowNum(guess, humanSecretNumber)[1];
            Iterator<String> myIterator = originList.iterator();
            //remove the element not have the same result with last guess
            while (myIterator.hasNext()) {
                String element = myIterator.next();
                int elementBull = numberModifier.generateBullCowNum(element, guess)[0];
                int elementCow = numberModifier.generateBullCowNum(element, guess)[1];
                if (elementBull != lastBull || elementCow != lastCow) {
                    myIterator.remove();
                }
            }
            guess = originList.get((int) (Math.random() * originList.size()));//guess generate randomly from the latest list
        }
        System.out.println("Hard AI guess: " + guess);
        this.setGuessTurn(this.getGuessTurn() - 1);//guess turn decrease 1 when guess once
        return guess;
    }

    @Override
    public String generateSecretNum() {
        return originList.get((int) (Math.random() * originList.size()));
    }

}
