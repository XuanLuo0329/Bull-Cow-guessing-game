import java.util.*;

public class ArrayListGenerator {
    //Generate an Arraylist<String> which contains all four digits number string with no same digit
    public ArrayList<String> generateOriginList(String str) {
        ArrayList<String> list = new ArrayList<>();
        for (int i = 0; i <= 9; i++) {
            if (!str.contains(String.valueOf(i))) {//If current string doesn't have the number i, add i to string.
                list.addAll(generateOriginList(str + i));//add all the element to list.
            }
        }
        if (str.length() == 4) {//Add current string to list when digits come to 4.
            list.add(str);
        }
        return list;
    }
}

