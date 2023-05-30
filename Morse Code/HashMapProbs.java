import java.util.ArrayList;
import java.util.HashMap;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class HashMapProbs{

    public static HashMap<String, String> takeBefore(String a, String b) {
        HashMap<String, String> chars = new HashMap<String, String>();
        for (int i = 0; i < a.length(); i++){
            if (a.charAt(i) < b.charAt(i)){
                chars.put(String.valueOf(a.charAt(i)), String.valueOf(b.charAt(i)));
            }
            else if (a.charAt(i) > b.charAt(i)){
                chars.put(String.valueOf(b.charAt(i)), String.valueOf(a.charAt(i)));
            }
        }
        return chars;
    }

    public static HashMap<String, Boolean> multiple(String s) {
        HashMap<String, Boolean> chars = new HashMap<String, Boolean>();
        for (int i = 0; i < s.length(); i++){
            if (chars.containsKey(String.valueOf(s.charAt(i)))){
                chars.put(String.valueOf(s.charAt(i)), true);
            }
            else{
                chars.put(String.valueOf(s.charAt(i)), false);
            }
        }
        return chars;
    }

    public static HashMap<String, String> charWord(String[] words){
        HashMap<Character, Boolean> startingLetters = new HashMap<Character, Boolean>();
        HashMap<Character, ArrayList<String>> mapped = new HashMap<Character, ArrayList<String>>();
        HashMap<String, String> combined = new HashMap<String, String>();
        for (int i = 0; i < words.length; i++){
            if (startingLetters.containsKey(words[i].charAt(0))){
                startingLetters.put(words[i].charAt(0), true);
            }
            else {
                startingLetters.put(words[i].charAt(0), false);
            }
        }
        for (char key: startingLetters.keySet()){
            ArrayList<String> matchedWords = new ArrayList<String>();
            for (int j = 0; j < words.length; j++){
                if (words[j].charAt(0) == key){
                    matchedWords.add(words[j]);
                }
            }
            mapped.put(key, matchedWords);
        }
        for (char letter: mapped.keySet()){
            combined.put(String.valueOf(letter), String.join("", mapped.get(letter)));
        }
        return combined;
    }

    public static void determineHighestFrequency(String fileName) throws FileNotFoundException{
        Scanner file = new Scanner(new File(fileName));
        HashMap<String, Integer> frequencies = new HashMap<String, Integer>();
        ArrayList<String> words = new ArrayList<String>();
        while (file.hasNext()){
            String currentWord = file.next().toLowerCase();
            words.add(currentWord);
        }
        for (int i = 0; i < words.size(); i++){
            if (frequencies.containsKey(words.get(i))){
                frequencies.put(words.get(i), frequencies.get(words.get(i)) + 1);
            }
            else {
                frequencies.put(words.get(i), 1);
            }
        }
        int max = Collections.max(frequencies.values());
        for (String key : frequencies.keySet()){
            if (frequencies.get(key) == max){
                System.out.println("Highest frequency word: " + key + "." + " Frequency: " + max);
            }
        }

    }
}