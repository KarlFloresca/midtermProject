package main;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Subject {
    static Scanner sc = new Scanner(System.in);
    static Person person;
    static Instructor inst = new Instructor();
    static Student st = new Student();
    static MethodCaller mc = new MethodCaller();

    public static void displaySearch(int index){
        System.out.println("=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=");
        System.out.println(Person.subjects.get(index)+"   "+Person.subID.get(index));
        System.out.println("-----------------------------------------------------------------------------------------------------------------------");
        System.out.println("DESCRIPTION: \n"+Person.subjectsMap.get(Person.subjects.get(index)));
        System.out.println("=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=");
    }
    public static List<String> addDescription(){
        List<String> subjectDescriptions = new ArrayList<>();
        subjectDescriptions.add("""

                Introduction to computing - Overview of computing concepts 
                and principles, Hands-on experience with basic software tools
            """);
        subjectDescriptions.add("""

                Computer fundamentals - Understanding computer 
                hardware and software, Operating system basics
            """);
        subjectDescriptions.add("""

                Computer programming 1 - Introduction to 
                programming logic,Fundamentals of algorithm 
                design
            """);
        subjectDescriptions.add("""
            
                Discrete math - Mathematical logic and
                proofs, Graph theory etcs
            """);
        subjectDescriptions.add("""

                Creative writing - Exploration of various writing styles,
                Creative expression through storytelling
                """);
        subjectDescriptions.add("""

                English - Literary analysis,
                Language skills development
            """);
        subjectDescriptions.add("""

                Social science - Study of human behavior and societies,
                Social theories and research methodologies
            """);
        subjectDescriptions.add("""

                Arts - Introduction to visual arts, 
                Exploration of artistic techniques
            """);

        return subjectDescriptions;
    }

    public static void addSubToList(){
        String add;
        String desciption = "no description";
        String id = "SUB-"+generateID();

        System.out.print("\nWhat subject do you want to add: ");
        add = sc.nextLine().trim();

        while (add.equals(null)||add.equals("")) {
            System.out.println("\nplease dont leave the subject name blank: ");
            add = sc.nextLine().trim();
        }

        System.out.print("\nAdd a description: ");
        desciption = sc.nextLine().trim();

        while (desciption==""||desciption==null) {
            System.out.print("do not leave blank: ");
            desciption = sc.nextLine().trim();
        }

        // updating of variables
        Person.subjects.add(add);
        Person.subID.add(id);
        Person.descriptions.add(desciption);
        Person.addSubject(add, desciption);
        System.out.println("PROCESSING");
        loading();
        System.out.println("\n=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=");
        System.out.println("Succesfully added "+add+" with the subject id of "+id);
        System.out.println("=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=");
    }

    // another version of id generator for subjects
    public static int generateID(){
        int max=1000,min=9999;
		int init = (min + (int)(Math.random() * ((max - min) + 1)));

        while (Person.subID.contains("SUB-"+init)) {
            generateID();
        }
        return init;
    }

    public static void display(List<String> subject, List<String> id, Map<String, String> subjectsMap){
        for (int i = 0; i < subject.size(); i++) {
            System.out.println("[==================================================================]");
            System.out.println(" "+subject.get(i)+"    "+id.get(i));
            System.out.println("____________________________________________________________________");
            if (subjectsMap.containsKey(subject.get(i))) {
                System.out.println(subjectsMap.get(subject.get(i)));
            }
            System.out.println("[==================================================================]\n");
            System.out.println("\n");
        }
    }

    public static void loading(){
        int idk = 3;
        for (int i = 1; i <= idk; i++) {
            Person.delayInSeconds(1);
            if (i==1) {
                System.out.println("pogi ...");
            }else if (i==2) {
                System.out.println("nyo  .....");
            }else if (i==3) {
                System.out.println("Sir:).......");
                Person.delayInSeconds(2);
            }
        }
    }

    public static void edit(){

        @SuppressWarnings("resource")
        Scanner scan = new Scanner(System.in);
        System.out.print("""

            pick an action:
            
            1. rename subject
            2. replace description
            3. remove a subject
            
            enter here: """);
        boolean valid = false;
        int deci = 0;
        while (!valid) {
            try {
                deci = scan.nextInt();
                while (deci<1||deci>3) {
                    System.out.print("\ninput is invalid, try again: ");
                    deci = scan.nextInt();
                }
                valid = true;
            } catch (Exception e) {
                System.out.println("\nPlease enter a numerical value: ");
                scan.next();
            }
        }

        switch (deci) {
            case 1:
                rename();
                break;
            case 2:
                replaceDes();
                break;
            case 3:
                removeSub();
                break;
            default:
                break;
        }
    }

    @SuppressWarnings("resource")
    public static void rename(){
        Scanner scan = new Scanner(System.in);

        System.out.print("\nWhich subject would you like to replace\n");
        for (int i = 0; i < Person.subjects.size(); i++) {
            System.out.println((1+i+". ")+Person.subjects.get(i));
        }

        System.out.print("Enter here: ");
        boolean valid = false;
        int deci = -1;
        while (!valid) {
            Scanner sc = new Scanner(System.in);
            try {
                deci = sc.nextInt();
                while (deci < 1||deci>Person.subjects.size()) {
                    System.out.print("Input is invalid, try again: ");
                    deci = sc.nextInt();
                }
                valid = true;
            } catch (Exception e) {
                System.out.print("Please enter a number: ");
                sc.next();
            }
        }

        System.out.print("Enter new Subject title: ");
        String newT = scan.nextLine().trim().toUpperCase();

        while (newT=="") {
            System.out.print("\nPlease dont leave blank: ");
            newT = scan.nextLine().trim().toUpperCase();
        }
        while (Person.subjects.contains(newT)) {
            System.out.println("that is already in the subject list");
        }
        System.out.println("Processing...");
        loading();

        if (Instructor.subjectsMap.containsKey(Person.subjects.get(deci-1))) {
            Instructor.subjectsMap.put(newT, Instructor.subjectsMap.get(Person.subjects.get(deci-1)));
            Instructor.subjectsMap.remove(Person.subjects.get(deci));
        }
        for (int i = 0; i < Instructor.getname().size(); i++) {
            if (Instructor.instructorSubjects.containsKey(Instructor.getname().get(i))) {
                if (Instructor.instructorSubjects.get(Instructor.getname().get(i)).equalsIgnoreCase(Person.subjects.get(deci-1))) {
                    Instructor.instructorSubjects.replace(Instructor.getname().get(i), newT);
                }
            }
        }

        Person.subjects.set(deci-1, newT);
        System.out.println("Successfuly change");
    }

    public static void replaceDes(){
        @SuppressWarnings("resource")
        Scanner scan = new Scanner(System.in);
        int index = Person.iterator(Person.subjects);
        
        System.out.print("\nPlease provide the new description: ");
        String description = scan.nextLine();

        System.out.println("Processing...");
        loading();

        if (Person.subjectsMap.containsKey(Person.subjects.get(index))) {
            Person.subjectsMap.replace(Person.subjects.get(index), description);
        }
        System.out.println("================================");
        System.out.println(" Successfuly change description");
        System.out.println("================================");
    }

    public static int iterator(List<String> list){
        @SuppressWarnings("resource")
        Scanner iterSC = new Scanner(System.in);
        System.out.println("\nHere is the list of available options\n");
        for (int i = 0; i < list.size(); i++) {
            System.out.println((i+1)+". "+list.get(i));
        }
        System.out.print("\npick the number of the file you want to edit: ");
        boolean valid = false;
        int index = -1;
        while (!valid) {
            try {
                index = iterSC.nextInt();
                while (index < (list.size()-(list.size()-1))||index > list.size()) {
                    System.out.print("that is not an option: ");
                    index = iterSC.nextInt();
                }
                valid = true;
            } catch (Exception e) {
                System.out.print("please enter a numirical value: ");
                iterSC.next();
            }
        }
        return index-1;
    }

    public static void removeSub(){
        int index2 = iterator(Person.subjects);

        System.out.println("processing...");
        Person.delayInSeconds(1);

        System.out.println("\nProcess successful");
        
        if (Person.subjects.contains(Person.subjects.get(index2))) {
            removal(index2);
        }

        if (Person.subjectsMap.containsKey(Person.subjects.get(index2))) {
            Person.subjectsMap.remove(Person.subjects.get(index2));
        }
        Person.subjects.remove(index2);

        mc.returner();
    }

    public static void removal(int index2){
        // removeds from students enrolled subjects.
        
        for (int i = 0; i < st.getname().size() ; i++) {
            if (Student.enrolledSubject.containsKey(st.getname().get(i))) {
                List<String> newList = Student.enrolledSubject.get(st.getname().get(i));
                for (int j = 0; j < newList.size(); j++) {
                    if (newList.get(j).equalsIgnoreCase(Person.subjects.get(index2))) {
                        newList.remove(j);
                    }
                }
            }
        }

        for (int i = 0; i < Instructor.getname().size(); i++) {
            if (Instructor.instructorSubjects.containsKey(Instructor.getname().get(i))) {
                if (Instructor.instructorSubjects.get(Instructor.getname().get(i))==Person.subjects.get(index2)) {
                    Instructor.instructorSubjects.remove(Instructor.getname().get(i));
                }
            }
            else{
                continue;
            }
        }
    }
}