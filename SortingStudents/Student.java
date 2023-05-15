package SortingStudents;

public class Student implements Comparable<Student>{
    private String name;
    private int studentID;

    public Student(String name, int studentID){
        this.name = name;
        this.studentID = studentID;
    }

    public String getName(){return this.name;}
    public int getID(){return this.studentID;}

    public boolean equals(Student other){
        return other.getName().equals(this.name) && other.getID() == this.studentID;
    }

    @Override
    public String toString(){
        return this.studentID + ":" + " " + this.name;
    }

    @Override
    public int compareTo(Student s){
        return this.name.compareTo(s.getName());
    }
    // public int compareTo(Student s){
    //     if (this.studentID > s.getID()){
    //         return 1;
    //     }
    //     else if (this.studentID < s.getID()){
    //         return -1;
    //     }
    //     return 0;
    // }
}
