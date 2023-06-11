package ShoppingCart;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class ReviewProbs {
    
    public static String getMiddle(String str){
        String res = "";
        if (str.length() % 2 != 0){
            res = String.valueOf(str.charAt(str.length() / 2));
        }
        else {
            String p1 = String.valueOf(str.charAt((str.length() - 1) / 2));
            String p2 = String.valueOf(str.charAt(str.length() / 2));
            res = p1 + p2;
        }
        return res;
    }

    public static int[] sumNumbers(int n){
        int len = Math.abs(n) + 1;
        int[] nums = new int[len];
        for (int i = 0; i <= n; i++){
            nums[i] = i;
        }
        int[] res = new int[len];
        for (int j = 0; j < nums.length; j++){
            int sum = 0;
            int[] copy = Arrays.copyOfRange(nums, 0, j + 1);
            for (int num: copy) {sum += num;}
            res[j] = sum;
        }
        return res;
    }

    public static int sumDigits(int num){
        int sum = 0;
        while (num > 0){
            sum += num % 10;
            num /= 10;
        }
        return sum;
    }

    public static int keepSummingDigits(int num){
        int curr = num;
        while (String.valueOf(curr).length() != 1){
            ArrayList<String> nums = new ArrayList<String>();
            String val = String.valueOf(curr);
            for (int i = 0; i < val.length(); i++){
                nums.add(String.valueOf(val.charAt(i)));
            }
            int sum = 0;
            for (String n: nums){sum += Integer.parseInt(n);}
            if (!(sum >= 0 && sum < 10)){
                curr = sum;
                nums.clear();
            }
            else {
                return Integer.parseInt(nums.get(0));
            }
        }
        return curr;
    }

    public static String getIntersection(int[] a, int[] b){
        String res = "";
        for (int i = 0; i < a.length; i++){
            int currentComparisonElem = a[i];
            for (int j = 0; j < b.length; j++){
                if (b[j] == currentComparisonElem){
                    res += String.valueOf(b[j]);
                    break;
                }
            }
        }
        return res;
    }

    public static Map<Integer, Integer> mapLengths(String[] words){
        Map<Integer, Integer> lengths = new HashMap<Integer, Integer>();
        for (int i = 0; i < words.length; i++){
            if (!(lengths.containsKey(words[i].length()))){
                lengths.put(words[i].length(), 1);
            }
            else {
                lengths.put(words[i].length(), lengths.get(words[i].length()) + 1);
            }
        }
        return lengths;
    }

    public static int sumDigitsRecur(int n){
        if (String.valueOf(n).length() == 1){ // if n only has 1 digit
            return n;
        }
        else {
            return (n % 10) + sumDigitsRecur(n / 10);
        }
    }

    public static int sumWithoutCarry(int a, int b){
        String res = "";
        while (a > 0 && b > 0){
            int currSum = (a % 10) + (b % 10);
            if (String.valueOf(currSum).length() == 1){
                res += String.valueOf(currSum);
            }
            else if (String.valueOf(currSum).length() > 1){
                res += String.valueOf(currSum % 10);
            }
            a /= 10; b /= 10;
        }
        String rev = "";
        for (int i = res.length() - 1; i >= 0; i--){
            rev += res.charAt(i);
        }
        return Integer.parseInt(rev);
    }

    public static int buySell1(int[] stock){
        int count = 0;
        for (int i = 0; i < stock.length - 1; i++){
            if (stock[i + 1] <= stock[i]){
                count++;
            }
        }
        if (count == stock.length - 1){
            return 0;
        }
        int maxProfit = 0;
        for (int j = 0; j < stock.length - 1; j++){
            if (stock[j+1] - stock[j] > maxProfit){
                maxProfit = stock[j+1] - stock[j];
            }
        }
        return maxProfit;
    }

    public static int generateNthFib(int n){
        if (n == 0) {return 0;}
        if (n == 1) {return 1;}
        else {return generateNthFib(n-1) + generateNthFib(n-2);}
    }

    public static void zeck(String fileName) throws FileNotFoundException{
        Integer[] fibNums = new Integer[20];
        for (int i = 0; i < 20; i++) {
            fibNums[i] = generateNthFib(i);
        }
        Scanner file = new Scanner(new File(fileName));
        HashMap<Integer, Integer[]> zecker = new HashMap<Integer, Integer[]>();
        int numsToRead = file.nextInt();
        for (int j = 0; j < numsToRead; j++) {
            Integer currentNum = file.nextInt();
            int index = 0;
            for (int k = 0; k < fibNums.length; k++) {
                if (fibNums[k] > currentNum) {
                    index = k;
                    break;
                }
            }
            Integer[] subset = Arrays.copyOfRange(fibNums, 0, index);
            zecker.put(currentNum, subset);
        }
        for (Integer[] arr: zecker.values()){
            System.out.println(Arrays.toString(arr));
        }
    }
}
