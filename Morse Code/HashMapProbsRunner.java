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

    }
}
