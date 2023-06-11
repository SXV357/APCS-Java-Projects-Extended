package ShoppingCart;

import java.io.FileNotFoundException;
import java.util.Arrays;

public class Runner {

    public static void main(String[] args){
        System.out.println(ReviewProbs.getMiddle("hello"));
        System.out.println(ReviewProbs.getMiddle("zilChCzA"));
        System.out.println(ReviewProbs.getMiddle("abcdefghij"));

        System.out.println(Arrays.toString(ReviewProbs.sumNumbers(5)));

        System.out.println(ReviewProbs.sumDigits(234));
        System.out.println(ReviewProbs.sumDigitsRecur(234));

        System.out.println(ReviewProbs.keepSummingDigits(29));
        System.out.println(ReviewProbs.getIntersection(new int[] {1,2,3,4}, new int[] {9,0,4,3,4,1}));
        System.out.println(ReviewProbs.mapLengths(new String[] {"a", "b", "hello", "hi", "yo", "I"}));

        System.out.println(ReviewProbs.sumWithoutCarry(861, 450));
        System.out.println(ReviewProbs.buySell1(new int[] {3,4,3,2,1,5}));
        System.out.println(ReviewProbs.buySell1(new int[] {5, 4, 3, 2, 1, 1}));

        try {
            ReviewProbs.zeck("/Users/shreyasviswanathan/Desktop/Programming/Java Projects/APCS-Java-Projects-Extended/ShoppingCart/zeck.txt");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

}
