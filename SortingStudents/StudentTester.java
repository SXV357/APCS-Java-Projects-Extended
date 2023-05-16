package SortingStudents;

import java.util.List;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.ArrayList;
import java.util.Collections;

public class StudentTester
{
	public static void main(String[] args) throws FileNotFoundException
	{
		Student a = new Student("Joe", 100009);
		Student b = new Student("Sally", 100004);
		Student c = new Student("Sam", 100007);
		Student d = new Student("Bob", 100006);

		List<Student> list = new ArrayList<>();

		list.add(a); list.add(b); list.add(c); list.add(d);

		Collections.sort(list);

		System.out.println(list);

		ArrayList<Student> students = new ArrayList<Student>();
		String fileName = "C:/Users/14058/OneDrive/Desktop/APCS-Projects-Extended/SortingStudents/names.csv";
		Scanner scanner = new Scanner(new File(fileName));
		Random rand = new Random();
		while (scanner.hasNext()){
			String name = scanner.next();
			int id = rand.nextInt(900000) + 100000;
			students.add(new Student(name, id));
		}
	}

	public static void selectionSort(ArrayList<Student> students){
		for (int i = 0; i < students.size() - 1; i++){
			int currentIndex = i;
			for (int j = i+1; j < students.size(); j++){
				if (students.get(j).getName().compareTo(students.get(currentIndex).getName()) < 0){
					currentIndex = j;
				}
			}
			Student temp = students.get(currentIndex);
			students.set(currentIndex, students.get(i));
			students.set(i, temp);
		}
	}

	public static void insertionSort(ArrayList<Student> students){
		for (int i = 1; i < students.size(); i++){
			String current = students.get(i).getName();
			int j = i - 1;
			while (j >= 0 && students.get(j).getName().compareTo(current) > 0){
				students.set(j + 1, students.get(j));
				j -= 1;
			}
			students.set(j + 1, students.get(i));
		}
		System.out.println(students);
	}

	public static int indexOf(ArrayList<Student> entries, Student s){
		int low = 0;
		int high = entries.size() - 1;
		while (low < high){
			int middle = (low + high) / 2;
			if (entries.get(middle).compareTo(s) == 0){
				return middle;
			}
			if (entries.get(middle).compareTo(s) < 0){
				high = middle - 1;
			}
			if (entries.get(middle).compareTo(s) > 0){
				low = middle + 1;
			}
		}
		return -1;
	}
}
