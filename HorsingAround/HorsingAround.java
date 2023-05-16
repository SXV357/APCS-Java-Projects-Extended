import java.util.*;

public class HorsingAround
{
    public static int totalWidth(ArrayList<Animal> animals)
    {
        int total = 0;
        for (int i = 0; i < animals.size(); i++){
            total += animals.get(i).getWidth();
        }
        return total;
    }

    public static Animal tallestAnimal(ArrayList<Animal> animals)
    {
        int greatestHeight = 0;
        Animal greatest = animals.get(0);
        for (int i = 0; i < animals.size(); i++){
            if (animals.get(i).getHeight() > greatestHeight){
                greatestHeight = animals.get(i).getHeight();
                greatest = animals.get(i);
            }
        }
        return greatest;
    }

    public static int countYourChickens(ArrayList<Animal> animals)
    {
        int count = 0;
        for (int j = 0; j < animals.size(); j++){
            if (animals.get(j).getName().equals("chicken")){
                count++;
            }
        }
        return count;
    }

    public static ArrayList<String> inventory(ArrayList<Animal> animals)
    {
        ArrayList<String> names = new ArrayList<String>();
        for (Animal a: animals){
            names.add(a.getName());
        }
        return names;
    }

    public static void selectionSort(ArrayList<Animal> animals){
        for (int i = 0; i < animals.size(); i++){
            int maxIndex = i;
            for (int j = i + 1; j < animals.size(); j++){
                if (animals.get(j).compareTo(animals.get(maxIndex)) > 0){
                    maxIndex = j;
                }
            }
            Animal temp = animals.get(maxIndex);
            animals.set(maxIndex, animals.get(i));
            animals.set(i, temp);
        }
        // ArrayList<Animal> reversed = new ArrayList<Animal>();
        // for (int k = animals.size() - 1; k >= 0; k--){
        //     reversed.add(animals.get(k));
        // }
        // animals = reversed;
    }

    public static void pestControl(ArrayList<Animal> animals)
    {
    	for (int i = animals.size() - 1; i >= 0; i--){
            if (animals.get(i).getName().equals("mouse")){
                animals.remove(i);
            }
        }
    }

    public static void horsingAround(ArrayList<Animal> animals)
    {
    	Animal horse = new Animal("horse");
        animals.add(0, horse);
        animals.add(animals.size() - 1, horse);
        for (int k = 1; k < animals.size() - 2; k++){
            animals.add(k + 1, horse);
        }
    }

    public static void feelingSheepish(ArrayList<Animal> animals)
    {
    	for (int i = 1; i < animals.size() - 2; i++){
            if (animals.get(i).getName().equals("sheep")){
                if (!animals.get(i - 1).getName().equals("sheep")){
                    animals.set(i - 1, new Animal("sheep"));
                }
                else if (!animals.get(i + 1).getName().equals("sheep")){
                    animals.set(i + 1, new Animal(("sheep")));
                }
            }
        }
    }
}