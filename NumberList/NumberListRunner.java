package NumberList;
import java.util.ArrayList;
public class NumberListRunner {
    public static void main(String[] args) 
	{
		/*
		 * NumberList objects should function in the same way as an ArrayList that stores Integers
		 * 
		 * in other words, NumberList list = new NumberList(); should work the
		 * same as ArrayList<Integer> list = new ArrayList<>();
		 */
		
		//test constructor
		NumberList list = new NumberList();
		
		// //test isEmpty()
		System.out.println(list.isEmpty());
		
		// //test cases that will go out of bounds
		try { 
			list.add(-1, 5); //add element 5 at index -1
		} 
		catch (IndexOutOfBoundsException e) { 
			System.out.println("Your out of bounds exception works"); 
		}
		
		// //test the add() method and toString() method
		list.add(2); list.add(4); list.add(6); list.add(8);
		System.out.println(list);
		
		// //test the get() method
		System.out.println(list.get(0));
		System.out.println(list.get(list.size() - 1));
		
		// //test the set() method
		list.set(0, 20);
		list.set(2, 40);
		System.out.println(list);
		
		// //test the remove() method
		list.remove(list.size() - 2);
		System.out.println(list);
		
		// //test misc.
		list.remove(0); list.remove(0); list.remove(0);
		System.out.println(list);
		System.out.println(list.size());
		System.out.println(list.isEmpty());
		
		// //test two parameter add() method, list should begin empty
		list.add(0, 23); list.add(1, 17); list.add(2, 44); 
		System.out.println(list);
		
		list.add(0, 72); list.add(2, 65);
		System.out.println(list);
		System.out.println();

		NumberList primes = new NumberList();
		for (int i = 0; i < 1000000; i++){
			if (isPrime(i)){
				primes.add(i);
			}
		}
		int maxTerms = Integer.MIN_VALUE; int res = 0;
		for (int x = 0; x < primes.size(); x++){
			int sum = primes.get(x);
			int termsSummed = 1;
			for (int y = x + 1; y < primes.size(); y++){
				sum += primes.get(y);
				termsSummed++;

				if (sum > 1000000){
					break;
				}

				if (isPrime(sum) && termsSummed > maxTerms){
					maxTerms = termsSummed;
					res = sum;
				}
			}
		}
		System.out.println("The greatest number below 1 million that can be written as the sum of " + maxTerms +  " consecutive primes is: " + res);

		ArrayList<Integer> abundantNumbers = generateAbundantNumbers();
		ArrayList<Integer> excludedList = new ArrayList<Integer>();
		for (int i = 0; i < 28123; i++){
			boolean cannotBeExpressed = false;
			for (int j = 0; j < abundantNumbers.size(); j++){
				for (int k = j + 1; k < abundantNumbers.size(); k++){
					if (abundantNumbers.get(j) + abundantNumbers.get(k) == i){
						cannotBeExpressed = true;
						break;
					}
				}
				if (cannotBeExpressed){
					break;
				}
			}
			if (!(cannotBeExpressed)){
				excludedList.add(i);
			}
		}
		int excludedSum = 0;
		for (int excludedNum: excludedList) {excludedSum += excludedNum;}
		System.out.println("Sum of all positive integers less than 28123 that cannot be expressed as the sum of two abundant numbers equals " + excludedSum);
	}

	public static ArrayList<Integer> generateAbundantNumbers(){
		ArrayList<Integer> abundant = new ArrayList<Integer>();
		for (int i = 1; i < 100000; i++){
			ArrayList<Integer> factors = new ArrayList<Integer>();
			int current = i;
			for (int j = 1; j < i; j++){
				if (current % j == 0){
					factors.add(j);
				}
			}
			int factorSum = 0;
			for (int factor: factors) {factorSum += factor;}
			if (factorSum > current){
				abundant.add(current);
			}
		}
		return abundant;
	}

	public static boolean isPrime(int n){
		if (n == 0 || n == 1){
			return false;
		}
		for (int i = 2; i < n; i++){
			if (n % i == 0){
				return false;
			}
		}
		return true;
	}
}