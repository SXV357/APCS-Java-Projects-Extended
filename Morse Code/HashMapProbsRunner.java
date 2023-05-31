import java.util.HashMap;
import java.util.*;

import java.io.FileNotFoundException;

public class HashMapProbsRunner {
    public static void main(String[] args) throws FileNotFoundException{
        HashMap<String, String> animalSounds = new HashMap<String, String>();
        animalSounds.put("Dog", "woof");
        animalSounds.put("Cat", "meow");
        animalSounds.put("Lion", "roar");
        animalSounds.put("Cow", "moo");
        System.out.println(animalSounds);

        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter a string: ");
        String str = scanner.next();
        System.out.println(animalSounds.get(str));
        scanner.nextLine();

        System.out.println(animalSounds.size());

        System.out.print("Enter a new key: ");
        String newAnimal = scanner.next();
        System.out.print("Enter a new value: ");
        String newSound = scanner.next();
        animalSounds.put(newAnimal, newSound);

        System.out.println(animalSounds);
        scanner.close();

        System.out.println(HashMapProbs.takeBefore("str", "bye"));
        System.out.println(HashMapProbs.multiple("hello"));
        System.out.println(HashMapProbs.charWord(new String[] {"tea", "salt", "soda", "taco"}));
        HashMapProbs.determineHighestFrequency("C:/Users/14058/OneDrive/Desktop/APCS-Projects-Extended/Morse Code/dream.txt");

        MorseCode m = new MorseCode();
        System.out.println(m.encode("hello world"));
        System.out.println(m.decode("--- -- --. | .. - | .-- --- .-. -.- . -.. "));
    }
}

class MorseCode{

    private final String alphabet = "abcdefghijklmnopqrstuvwxyz1234567890 ";
    private final String[] morse = {".-", "-...", "-.-.", "-..", ".", "..-.", "--.", "....", "..", ".---", "-.-", ".-..", "--", "-.", "---", ".--.", "--.-", ".-.", "...", 
    "-", "..-", "...-", ".--", "-..-", "-.--", "--..", ".----", "..---", "...--", "....-", ".....", "-....", "--...", "---..", "----.", "-----", "|"};
    private HashMap<String, String> toText;
    private HashMap<String, String> toCode;

    public MorseCode(){
        this.toText = new HashMap<String, String>();
        this.toCode = new HashMap<String, String>();
        for (int i = 0; i < alphabet.length(); i++){
            toCode.put(String.valueOf(alphabet.charAt(i)), morse[i]);
            toText.put(morse[i], String.valueOf(alphabet.charAt(i)));
        }
    }
    public String encode(String s){
        String res = "";
        for (int i = 0; i < s.length(); i++){
            res += toCode.get(String.valueOf(s.charAt(i))) + " ";
        }
        return res;
    }
    public String decode(String s){
        Scanner text = new Scanner(s);
        String result = "";
        while (text.hasNext()){
            String sequence = text.next();
            result += toText.get(sequence);
        }
        text.close();
        return result;
    }
}
