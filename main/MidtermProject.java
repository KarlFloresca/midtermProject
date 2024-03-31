package main;

import java.util.Scanner;

public class MidtermProject {

    public static int academicYear;

    static MethodCaller mc = new MethodCaller();

    
    public static void main(String[] args) {
        @SuppressWarnings("resource")
        Scanner sc = new Scanner(System.in);
        Subject.addDescription();
        for (int i = 0; i < Person.subjects.size(); i++) {
            Person.subjectsMap.put(Person.subjects.get(i), Person.descriptions.get(i));
        }

        boolean valid = false;
        System.out.print("Hello user, welcome to our program\nBefore we start please enter the academic year (for example 2024)\nENTER HERE: ");
        Person.addSubject(null, null);
        while (!valid) {
            try {        
                academicYear = sc.nextInt();        
                if (academicYear > 2024) {
                    System.out.print("please do not enter years over the current year\ndi tayu time travler: ");
                }else if (academicYear < 1992) {
                    System.out.print("CNSC was created into a college in 1992, please enter a valid year: ");
                }else{      
                    valid = true;
                }
            } catch (Exception e) {
                System.out.print("please enter a numerical value:");
                sc.next();
            }
        }

        optionHandler();
    }

    public static void optionHandler(){
        @SuppressWarnings("resource")
        Scanner sc = new Scanner(System.in);

        System.out.println("""
                            ______________________________________________________________________________________________________________________
                            ()()()()()()()()()()()()()()()()()()()()()()()()()()()()()()()()()()()()()()()()(()()()()()())()()()()()()()()()()()()
                            ||__________________________________________________________________________________________________________________||    
                            ||==================================================================================================================||    
                            ||                                                                                                                  ||    
                            ||    ░██╗░░░░░░░██╗███████╗██╗░░░░░░█████╗░░█████╗░███╗░░░███╗███████╗                                             ||       
                            ||    ░██║░░██╗░░██║██╔════╝██║░░░░░██╔══██╗██╔══██╗████╗░████║██╔════╝                                             ||    
                            ||    ░╚██╗████╗██╔╝█████╗░░██║░░░░░██║░░╚═╝██║░░██║██╔████╔██║█████╗░░                                             ||    
                            ||    ░░████╔═████║░██╔══╝░░██║░░░░░██║░░██╗██║░░██║██║╚██╔╝██║██╔══╝░░                                             ||    
                            ||    ░░╚██╔╝░╚██╔╝░███████╗███████╗╚█████╔╝╚█████╔╝██║░╚═╝░██║███████╗                                             ||    
                            ||    ░░░╚═╝░░░╚═╝░░╚══════╝╚══════╝░╚════╝░░╚════╝░╚═╝░░░░░╚═╝╚══════╝                                             ||    
                            ||                                                                                                                  ||    
                            ||    ▀█▀ █▀█   ▀█▀ █ █ █▀▀   █ █ █▄ █ █ █ █ █▀▀ █▀█ █▀ █ ▀█▀ █▄█   █▀▄▀█ ▄▀█ █▄ █ ▄▀█ █▀▀ █▀▀ █▀▄▀█ █▀▀ █▄ █ ▀█▀   ||    
                            ||     █  █▄█    █  █▀█ ██▄   █▄█ █ ▀█ █ ▀▄▀ ██▄ █▀▄ ▄█ █  █   █    █ ▀ █ █▀█ █ ▀█ █▀█ █▄█ ██▄ █ ▀ █ ██▄ █ ▀█  █    ||    
                            ||                                                                                                                  ||    
                            ||    █▀█ █▀█ █▀█ █▀▀ █ █   █ █▄ █ █▀▀ █                                                                            ||    
                            ||    █▀▀ █▀▄ █▄█ █▀  █ █▄▄ █ █ ▀█ █▄█ ▄                                                                            ||    
                            ||                                                                                                                  ||
                            ||                                                                                                                  ||
                            ||    █ █ █ █ █ ▄▀█ ▀█▀   █ █ █ █▀█ █ █ █   █▀▄   █▄█ █▀█ █ █   █   █ █▄▀ █▀▀   ▀█▀ █▀█   █▀▄ █▀█ ▀█                ||
                            ||    ▀▄▀▄▀ █▀█ █▀█  █    ▀▄▀▄▀ █▄█ █▄█ █▄▄ █▄▀    █  █▄█ █▄█   █▄▄ █ █ █ ██▄    █  █▄█   █▄▀ █▄█  ▄                ||
                            ||                                                                                                                  ||
                            ||                                                                                                                  ||
                            ||__________________________________________________________________________________________________________________||    
                            |0==================================================================================================================0|    
                            |0                                                                                                                  0|
                            |0     [1] Add student, Instructor or subjects.         [5] Search for Students.                                    0|
                            |0                                                                                                                  0|
                            |0     [2] Edit students/Teachers/subjects info.        [6] display the list of students, subject or teacher        0|
                            |0                                                                                                                  0|
                            |0     [3] Search subject                               [7] Exit                                                    0|
                            |0                                                                                                                  0|
                            |0     [4] Search for teachers/Instructors.                                                                         0|
                            |0                                                                                                                  0|
                            ()()()()()()()()()()()()()()()()()()()()()()()()()()()()()()()()()()()()()()()()()()()()()()()()()()()()()()()()()()()
                            ----------------------------------------------------------------------------------------------------------------------
                """);

                boolean valid1 = false;
                System.out.print("Enter Here: ");
                while (!valid1) {
                    try {
                        int decision = sc.nextInt();
                        while (decision < 1|| decision>7) {
                            System.out.print("out of bounds, try again: ");
                            decision = sc.nextInt();
                        }
                        mc.methodcaller(decision);
                        valid1 = true;
                    } catch (Exception e) {
                        System.out.print("input is invalid, try again: ");
                        sc.next();
                    }
                }
                
                
    }
    public int getAcademicYear(){
        return academicYear;
    }
}