package main;

import java.util.List;
import java.util.Scanner;

public class SearchFor extends MethodCaller{
    public static Student student = new Student();
    public static Instructor instructor = new Instructor();
    static Scanner scan = new Scanner(System.in);

    public void searchINST(){
        String id;
        System.out.println("\nsuggestions: "+Instructor.teacherID.toString()+"\n");
        System.out.print("Enter the ID of the instructor(ex. INS24-1234): ");
        id = scan.nextLine().trim().toUpperCase();

        int core = checkForMatch(id, Instructor.teacherID);
        inst.displaySearch(core);
    }

    public void searchST(){
        String id;
        System.out.println("\nsuggestions: "+Student.studentID.toString()+"\n");
        System.out.print("Enter the ID of the student(ex. ST24-1234): ");
        id = scan.nextLine().trim();

        int core = checkForMatch(id, Student.studentID);
        student.displaySearch(core);
    }
    public void searchSub(){
        String id;
        System.out.println("suggestions: "+Person.subID.toString()+"\n");
        System.out.print("Enter the ID of the student(ex. SUB-1234): ");
        id = scan.nextLine().trim();
        int core = checkForMatch(id, Person.subID);
        Subject.displaySearch(core);

    }

    public static int checkForMatch(String id, List<String> ids) {
        int index = -1;
        
        for (int i = 0; i < ids.size(); i++) {
            if (id.equalsIgnoreCase(ids.get(i))) {
                index = i;
                break;
            }
        }
    
        if (index == -1) {
            System.out.print("There is no such ID in the system. Please try again: ");
            id = scan.nextLine().trim();
            return checkForMatch(id, ids);
        }
    
        return index;
    }
}
