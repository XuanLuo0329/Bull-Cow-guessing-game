import java.util.ArrayList;

public class MediumComputer extends Player {
    ArrayListGenerator arrayListGenerator = new ArrayListGenerator();
    ArrayList<String> originList = arrayListGenerator.generateOriginList("");
    ArrayList<Integer> indexArrList = new ArrayList<>();//create object of index ArrayList for originList
    //return secret number String by Medium AI

    public MediumComputer() {
        this.setPlayerName("Medium AI");
    }

    public String generateSecretNumGuess() {
        int index = (int) (Math.random() * originList.size());//get the random element inside the origin list
        //check if the latest index is contained in the index ArrayList or not
        while (indexArrList.contains(index)) {//if true, it means the number has already been guessed.
            index = (int) (Math.random() * originList.size());
            //so the index should be random again until the latest index is not contained in the index ArrayList.
        }
        indexArrList.add(index);//add the latest index number into the index ArrayList
        String guess = originList.get(index);
        System.out.println("Medium AI guess: " + guess);
        return guess;
    }

    @Override
    public String generateSecretNum() {
        return originList.get((int) (Math.random() * originList.size()));
    }
}

