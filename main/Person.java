package main;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public abstract class Person extends MidtermProject {
    // Scanner
    public static Scanner sc = new Scanner(System.in);

    // ids list
    private static List<String> existingStudentIDs = new ArrayList<>(List.of("ST24-1291","ST24-2101","ST24-9381","ST24-8981","ST24-6381"));
    private static List<String> existingTeacherIDs = new ArrayList<>(List.of("INS24-3834","INS24-1024","INS24-8234","INS24-2234","INS24-1123"));
    public static List<String> existingSubjectIDs = new ArrayList<>();
    public static List<String> subjects = new ArrayList<>(List.of("Introduction to computing","Computer fundementals","Computer programing 1","Discrete math","Creative writing","English","Social science","Arts"));
    public static List<String> subID = generateID(subjects);
    public static Map<String, String> subjectsMap = new HashMap<>();
    static List<String> descriptions = Subject.addDescription();

    // instanciation of classes
    private static Student st = new Student();
    private static Instructor inst = new Instructor();
    private static MethodCaller mCaller = new MethodCaller();

    // variables for adding/searching/editing person
    private static String name;
    private static int age;
    private static String ID;
    private static String address;

    // abstraction 
    public abstract void edit();
    public abstract void changeName();
    public abstract void changeAge();
    public abstract void remove();
    public abstract void editSub();

    public static String generateID() {
		int max=1000,min=9999;
		int init = (min + (int)(Math.random() * ((max - min) + 1)));
        int acadYear = getYear(academicYear);

        String ID=acadYear+"-"+init;
        return ID;
	}

    public static void addSubject(String subject, String description) {
        subjectsMap.put(subject, description);
    }

    public static int editOption(){
        int deci = 0;
        System.out.print("""

            Pick an option:
            1. change name
            2. change Age
            3. remove/delete
            4. edit subjects of Student/Instructor.
            5. change address.
            
            enter here: """);
        boolean valid = false;
        while (!valid) {
            try {
                deci = sc.nextInt();
                while (deci<0||deci>5) {
                    System.out.print("input is Invalid, try again: ");
                    deci = sc.nextInt();
                }
                valid = true;
            } catch (Exception e) {
                System.out.print("enter a numirical value: ");
                sc.next();
            }
        }
        return deci;
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
        System.out.println("");
        return index-1;
    }

    public static List<String> generateID(List<String> subs) {
		int max=1000,min=9999;
        List<String> subID = new ArrayList<>();
        for (int i = 0; i < subs.size(); i++) {
            int init = (min + (int)(Math.random() * ((max - min) + 1)));
            subID.add("SUB-"+init);
        }
        return subID;
	}

    // use to add info to the system
    @SuppressWarnings("resource")
    public void add(int decision){
        Scanner sc1 = new Scanner(System.in);
        String fName, lName, mName;

        // for information about the person being added.
        System.out.println("\nHello user, please fill in the following");

        System.out.println("First name or Given name");
        System.out.print("Enter here: ");
        fName = sc1.nextLine();
        
        System.out.println("\nLast name / Family name ");
        System.out.print("Enter here: ");
        lName = sc1.nextLine();

        System.out.println("\nMiddle name[optional]");
        System.out.print("Enter here: ");
        mName = sc1.nextLine();

        System.out.print("\nAddress: ");
        address = sc.nextLine();

        while (fName == ""||fName == null|| lName==""||lName==null||address=="") {
            System.out.println("\n<======( please dont leave the areas blank, try again )======>");
            add(decision);
        }

        name = lName+", "+fName+" "+mName;
        name = name.toUpperCase();

        // this is run if option picked is instructor.
        if (decision == 1) {
            // checks if name already exists
            if (Instructor.getname().contains(name)) {
                System.out.print("that name already exist");
                mCaller.returner();
            }

            ID = "INS"+generateID();
            boolean valid = false;
            System.out.print("\nplease enter your age: ");  
            while (!valid) {
                try {
                    age = sc1.nextInt();
                    while (age<24) {
                        System.out.print("24 is the required age for an instructor: ");
                        age = sc1.nextInt();
                    }
                    valid = true;
                } catch (Exception e) {
                    System.out.print("Please enter an integer: ");
                    sc1.next();
            }
        }

        // checks if the id is already an existing id
        while (checkID(existingTeacherIDs, ID)==true) {
            ID = generateID();
        }
        existingTeacherIDs.add(ID);
        inst.add(name, age, ID, address);
        }
        // operates if the option pick is student
        else if (decision == 2) {
            // checks if name already exists
            if (st.getname().contains(name)) {
                System.out.println("=========================");
                System.out.println("that name already exist");
                System.out.println("=========================");
                mCaller.returner();
            }

            ID = "ST"+generateID();
            boolean valid = false;
            System.out.print("\nplease enter your age: ");  
            while (!valid) {
                try {
                    age = sc.nextInt();
                    while (age<17) {
                        System.out.print("17 is the minimum age of a student: ");
                        age = sc.nextInt();
                    }
                    valid = true;
                } catch (Exception e) {
                    System.out.print("Please enter an integer: ");
                    sc.next();
            }
        }
        // checks if the id is already an existing id
        while (checkID(existingStudentIDs, ID)==true) {
            ID = generateID();
        }
        existingStudentIDs.add(ID);
        st.add(name, age, ID, address);
        }
    }

    public static int getYear(int year){
        int lastTwoDigits = year % 100;
        return lastTwoDigits;
    }

    public List<String> getSubjects(){
        return subjects;
    }


    public static void delayInSeconds(int seconds) {
        try {
            // Convert seconds to milliseconds and put the current thread to sleep
            Thread.sleep(seconds * 1000);
        } catch (InterruptedException e) {
            // Handle interrupted exception if necessary
            e.printStackTrace();
        }
    }

    public boolean checkID(List<String> ids, String ID){
        boolean checker;
        if (ids.contains(ID)) {
            checker = true;
        }else{
            checker = false;
        }
        return checker;
    }
    public static void display(int deci){
        switch (deci) {
            case 1:
                System.out.println(Instructor.getname());
                inst.displayInstructor(Instructor.getname(), inst.getAges(), inst.getIDS());
                break;
            case 2:
                st.displayStudents(st.getname(), st.getAges(), st.getIDS());
                break;
            case 3:
                Subject.display(subjects, subID, subjectsMap);
                break;
            default:
                break;
        }
    }
}

// inheritance
class Instructor extends Person {

    private static List<String> tNames = new ArrayList<>(List.of("ROXAS, ALTHEA","DOLORES, CHARLES JOHN","DOMINGO, EMERSON","DELLOS SANTOS, ROGER","MENDOZA, VIKTOR"));
    private static List<Integer> tAge = new ArrayList<>(List.of(25,26,24,30,39));
    public static List<String> teacherID = new ArrayList<>(List.of("INS24-3834","INS24-1024","INS24-8234","INS24-2234","INS24-1123"));
    public static Map<String, String> instructorSubjects = new HashMap<>();
    public static List<String> tadList = new ArrayList<>(List.of("p6 parang","p7 bagong bayan","p2 motherload","p3 bagong silang","p1 plaridel"));

    public void add(String name, int age, String id, String add){
        @SuppressWarnings("resource")
        Scanner sc1 = new Scanner(System.in);
        tNames.add(name);
        tAge.add(age);
        teacherID.add(id);
        tadList.add(add);

        System.out.println("Available subjects:");

        for (int i = 0; i < subjects.size(); i++) {
            System.out.println((i + 1) + ". " + subjects.get(i));
        }

        try {
            System.out.print("Select the subject for Prof. " + name + " (Enter the subject number): ");
            int subjectIndex = sc1.nextInt();
            
            if (subjectIndex < 1 || subjectIndex > subjects.size()) {
                System.out.println("Invalid subject selection. Please try again.");
                add(name, age, id, add); // Retry adding the instructor
            } else {
                String subject = subjects.get(subjectIndex - 1);
                instructorSubjects.put(name, subject);
                
                System.out.println("Adding information....");
                delayInSeconds(2);

                System.out.println("\n=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-==-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=");
                System.out.println("SUCCESSFULLY ADDED Prof."+name +" with the Teacher id of "+id + " teaching " + subject);
                System.out.println("=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-==-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=");
                
                mc.returner();
            }

        } catch (Exception e) {
            // retries if the type inputed is not the same type in the list
            System.out.println("Invalid input format. Please enter a valid subject number.");
            sc1.next(); 
            add(name, age, id, add);
        }
    }

    public void edit(){

        int action = editOption();

        switch (action) {
            case 1:
                changeName();
                break;
            case 2:
                changeAge();
                break;
            case 3:
                remove();
                break;
            case 4:
                editSub();
                break;
            case 5:
                changeAddress();
                break;
            default:
                break;
        }
    }

    // gets the list of information
    public static List<String> getname(){
        return tNames;
    }
    public List<String> getIDS(){
        return teacherID;
    }
    public List<Integer> getAges(){
        return tAge;
    }

    public void changeAddress(){
        @SuppressWarnings("resource")
        Scanner scan = new Scanner(System.in);
        int index = iterator(tNames);
        System.out.println("\nEnter the new Address: ");
        String newAdd = scan.nextLine();

        tadList.set(index, newAdd);
    }
    
    public void displayInstructor(List<String> instructor, List<Integer> ages, List<String> ids){
        for (int i = 0; i < instructor.size(); i++) {
            System.out.println("\n\n=================================================================");
            System.out.println("  Name: "+instructor.get(i)+"           "+ids.get(i)+"");
            System.out.println("  Age: "+ages.get(i)+"");
            System.out.println("  Address: "+tadList.get(i));
            System.out.println("_________________________________________________________________");
            System.out.print(" Subject Assigned: ");displaySub(tNames.get(i));
            System.out.println("=================================================================");
        }
    }
    public void displaySub(String name){
        String instructorName = name;
        if (instructorSubjects.containsKey(instructorName)) {
            String subject = instructorSubjects.get(instructorName);
            System.out.println(subject);
        } else {
            System.out.println("No subjects assigned yet.");
        }
    }
    public void displaySearch(int index){
        System.out.println("===========================================================================");
        System.out.println(tNames.get(index)+"                     "+teacherID.get(index));
        System.out.println("  Address: "+tadList.get(index));
        System.out.println("===========================================================================");
        System.out.print(" Subject Assigned: ");displaySub(tNames.get(index));
        System.out.println("===========================================================================\n");
    }

    public void changeName() {
        @SuppressWarnings("resource")
        Scanner newSc = new Scanner(System.in);
        int index = iterator(tNames);
        System.out.println("\nYou have chosen "+tNames.get(index));

        System.out.print("\nplease enter first name: ");
        String fname = newSc.nextLine();
        System.out.print("\nplease enter your surname: ");
        String lname = newSc.nextLine();
        System.out.print("\nplease enter your middle name[optional]: ");
        String mname = newSc.nextLine();

        while (fname == ""||fname == null|| lname==""||lname==null) {
            System.out.println("\n<======( please dont leave the areas blank, try again )======>");
            System.out.println("\nrestarting");
            delayInSeconds(1);
            changeName();
        }

        String name = (lname+", "+fname+" "+mname).trim();

        while (tNames.contains(name)) {
            System.out.println("that name already exists");
            System.out.println("\nRestarting");
            delayInSeconds(1);
            changeName();
        }

        Subject.loading();

        tNames.set(index, name.toUpperCase());
        System.out.println("-----------------------------------");
        System.out.println("<===( succesfully change name )===>");
        System.out.println("-----------------------------------");
        mc.returner();
    }

    @Override
    public void remove() {
        int index = iterator(tNames);

        instructorSubjects.remove(tNames.get(index));
        tAge.remove(index);
        teacherID.remove(index);
        tNames.remove(index);
        tadList.remove(index);
        System.out.println("\nPROCESSING\n");
        Subject.loading();

        System.out.println("--------------------------------");
        System.out.println("Successfully remove information.");
        System.out.println("--------------------------------");
    }

    @Override
    public void changeAge() {
        @SuppressWarnings("resource")
        Scanner intSc = new Scanner(System.in);
        int index = iterator(tNames);

        System.out.print("\nenter the new age/updated age: ");
        int age = 0;
        boolean valid = false;
        while (!valid) {
            try {
                age = intSc.nextInt();
                while (age<24) {
                    System.out.print("24 is the required age for an instructor: ");
                    age = intSc.nextInt();
                }
                valid = true;
            } catch (Exception e) {
                System.out.print("Please enter an integer: ");
                intSc.next();
            }
        }
        System.out.println("Processing...\n");
        delayInSeconds(1);
        System.out.println("--------------------------------");
        System.out.println("      Update successful");
        System.out.println("--------------------------------");
        tAge.set(index, age);
    }

    @Override
    public void editSub() {
        @SuppressWarnings("resource")
        Scanner newSc = new Scanner(System.in);
        System.out.print("""
            ==============
            Pick an option 
            ==============
            1. re-assign subject.
            2. remove assigned subject.
            
            Enter here: """);
        int deci = 0;
        boolean valid = false;
        while (!valid) {
            try {
                deci = newSc.nextInt();
                while (deci<1||deci>2) {
                    System.out.print("Input is invalid, try again: ");
                    deci = newSc.nextInt();
                }
                valid = true;
            } catch (Exception e) {
                System.out.print("Please enter a numerical value: ");
                newSc.next();
            }
        }

        switch (deci) {
            case 1:
                System.out.println("\nwhich instructor would you like to edit");
                int index = iterator(tNames);
        
                for (int i = 0; i < subjects.size(); i++) {
                    System.out.println((1+i)+" "+subjects.get(i));
                }
                System.out.print("\npick a subject to replace the previous: ");
                boolean valid1 = false;
                int sub = -1;
                while (!valid1) {
                    try {
                        sub = newSc.nextInt();
                        while (sub < 0||sub > subjects.size()) {
                            System.out.print("input is invalid, try again: ");
                            sub = newSc.nextInt();
                        }
                        valid1 = true;
                    } catch (Exception e) {
                        System.out.println("Please enter a numerical value: ");
                        newSc.next();
                    }
                }
                if (instructorSubjects.containsKey(tNames.get(index))) {
                    instructorSubjects.replace(tNames.get(index), subjects.get(sub-1));
                }else{
                    instructorSubjects.put(tNames.get(index), subjects.get(sub-1));
                }
                System.out.println("\nProcessing...");
                delayInSeconds(1);
                System.out.println("------------------------------------");
                System.out.println("Successfully re-Assigned the subject");
                System.out.println("------------------------------------");
                break;
            case 2:
                System.out.println("\nwhich instructor would you like to remove assigned subject");
                int index2 = iterator(tNames);

                if (instructorSubjects.containsKey(tNames.get(index2))) {
                    instructorSubjects.remove(tNames.get(index2));
                }else{
                    System.out.println("=================================================");
                    System.out.println("That instructor does not have an assigned subject");
                    System.out.println("=================================================");
                    editSub();
                }
                break; 
            default:
                break;
        }
    }

}

class Student extends Person {
    private static List<String> sNames = new ArrayList<>(List.of("RODRIGUEZ, JOHN","ANDERSON, GERALD","DAIZ, MARCO","CORAZON, IVAN","GARCIA, MARTIN"));
    private static List<Integer> sAge = new ArrayList<>(List.of(18,19,17,18,20));
    public static List<String> studentID = new ArrayList<>(List.of("ST24-1291","ST24-2101","ST24-9381","ST24-8981","ST24-6381"));
    public static List<String> sublist = new ArrayList<>();
    public static Map<String, List<String>> enrolledSubject = new HashMap<>();
    public static List<String> sadList = new ArrayList<>(List.of("p6 parang","p7 bagong bayan","p2 motherload","p3 bagong silang","p1 plaridel"));

    public void add(String name, int age, String id, String add){
        sNames.add(name);
        sAge.add(age);
        studentID.add(id);
        sadList.add(add);

        System.out.println("Adding information....");
        delayInSeconds(2);
        System.out.println("\n=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=");
        System.out.println("SUCCESFULLY ADDED "+name+" with the student id of "+id);
        System.out.println("=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=");

        enrollSubjects(name);
    }

    public List<String> getname(){
        return sNames;
    }
    public List<String> getIDS(){
        return studentID;
    }
    public List<Integer> getAges(){
        return sAge;
    }

    public void edit(){
        int action = editOption();

        switch (action) {
            case 1:
                changeName();
                break;
            case 2:
                changeAge();
                break;
            case 3:
                remove();
                break;
            case 4:
                editSub();
                break;
            case 5:
                changeAddress();
                break;
            default:
                break;
        }
    }

    public void displayStudents(List<String> students, List<Integer> ages, List<String> ids){
        for (int i = 0; i < students.size(); i++) {
            System.out.println("_______________________________________________________");
            System.out.println("  Name: "+students.get(i)+"           "+ids.get(i)+"");
            System.out.println("  Age: "+ages.get(i)+"");
            System.out.println("  Address "+sadList.get(i));
            System.out.println("_______________________________________________________");
            System.out.println("subjects\n");
            printEnrolledSubjects(students.get(i));
            System.out.println("_______________________________________________________");
        }
    }

    public void displaySearch(int index){
        System.out.println("=================================================================");
        System.out.println(sNames.get(index)+"                     "+studentID.get(index));
        System.out.println("  Address "+sadList.get(index));
        System.out.println("=================================================================");
        printEnrolledSubjects(sNames.get(index));
        System.out.println("=================================================================\n");
    }

    public void enrollSubjects(String name) {
        // Check if the student is already in the system
        if (!sNames.contains(name)) {
            System.out.println("Student not found!");
            return;
        }
    
        System.out.println("\nAvailable subjects:");
        for (int i = 0; i < subjects.size(); i++) {
            System.out.println((i + 1) +" subject ID "+subID.get(i) + ":. " + subjects.get(i));
        }

        // Maximum number of subjects allowed to enroll
        int maxSubjects = subjects.size(); 
        System.out.println("You can enroll in a maximum of " + maxSubjects + " subjects.");
    
        boolean validInput = false;
        int numSubjects = 0;

        @SuppressWarnings("resource")
        Scanner sc = new Scanner(System.in);

        System.out.print("\nHow many subjects do you want to enroll in? ");
        while (!validInput) {
            try {
                numSubjects = sc.nextInt();
    
                if (numSubjects <= 0 || numSubjects > maxSubjects) {
                    throw new IllegalArgumentException("Invalid number of subjects!");
                }
    
                validInput = true;
            } catch (Exception e) {
                System.out.print("Invalid input. Please enter a valid number: ");
                sc.next();
            }
        }
    
        List<String> subjectsList = new ArrayList<>();
        for (int i = 0; i < numSubjects; i++) {
            boolean validSubject = false;
            while (!validSubject) {
                try {
                    System.out.print("Enter subject " + (i + 1) + ": ");
                    int subject = sc.nextInt();
    
                    if (subject==0) {
                        throw new IllegalArgumentException("there is no option for 0");
                    }
    
                    // Check if the subject is already enrolled
                    if (subjectsList.contains(subjects.get(subject-1))) {
                        throw new IllegalArgumentException("You are already enrolled in " + subjects.get(subject-1) + "!");
                    }
                    // adds the subject to the subject list
                    subjectsList.add(subjects.get(subject-1));
                    validSubject = true;
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
        }
    
        enrolledSubject.put(name, subjectsList);
        System.out.println("\n=========================");
        System.out.println(" Enrollment successful!");
        System.out.println("=========================");
        mc.returner();
    }  
    
    public void printEnrolledSubjects(String name) {
        if (enrolledSubject.containsKey(name)) {
            int i = 0;
            List<String> subjectsList = enrolledSubject.get(name);
            System.out.println("Enrolled subjects for " + name + ":");
            for (String subject : subjectsList) {
                System.out.println(subID.get(i)+" : "+subject);
                i++;
            }
        } else {
            System.out.println(name + " is not enrolled in any subjects.");
        }
    }

    @SuppressWarnings("resource")

    @Override
    public void changeName() {
        int index = iterator(sNames);
        Scanner scan2 = new Scanner(System.in);
        System.out.println("\nYou have chosen "+sNames.get(index));

        System.out.print("\nplease enter first name: ");
        String fname = scan2.nextLine().trim();
        System.out.print("\nplease enter your surname: ");
        String lname = scan2.nextLine().trim();
        System.out.print("\nplease enter your middle name: ");
        String mname = scan2.nextLine().trim();

        while (fname == ""|| lname==""||mname=="") {
            System.out.println("\n<======( please dont leave the areas blank, try again )======>");
            System.out.println("\nrestarting");
            delayInSeconds(1);
            changeName();
        }

        String name = (lname+", "+fname+" "+mname).trim();

        while (sNames.contains(name)) {
            System.out.println("that name already exists");
            System.out.println("\nRestarting");
            delayInSeconds(1);
            changeName();
        }

        Subject.loading();

        sNames.set(index, name.toUpperCase());
        System.out.println("-----------------------------------");
        System.out.println("<===( succesfully change name )===>");
        System.out.println("-----------------------------------");
        mc.returner();
    }

    @Override
    public void remove() {
        int index = iterator(sNames);

        enrolledSubject.remove(sNames.get(index));
        sAge.remove(index);
        studentID.remove(index);
        sNames.remove(index);
        sadList.remove(index);
        System.out.println("\nPROCESSING\n");
        Subject.loading();

        System.out.println("--------------------------------");
        System.out.println("Successfully remove information.");
        System.out.println("--------------------------------");
    }

    public void changeAddress(){
        @SuppressWarnings("resource")
        Scanner scan = new Scanner(System.in);
        int index = iterator(sNames);
        System.out.println("\nEnter the new Address: ");
        String newAdd = scan.nextLine();

        sadList.set(index, newAdd);
    }

    @Override
    public void changeAge() {

        @SuppressWarnings("resource")
        Scanner scn = new Scanner(System.in);
        int index = iterator(sNames);

        System.out.print("\nenter the new age/updated age: ");
        int age = 0;
        boolean valid = false;
        while (!valid) {
            try {
                age = scn.nextInt();
                while (age<24) {
                    System.out.print("24 is the required age for an instructor: ");
                    age = scn.nextInt();
                }
                valid = true;
            } catch (Exception e) {
                System.out.print("Please enter an integer: ");
                scn.next();
            }
        }
        System.out.println("Processing...");
        delayInSeconds(1);
        System.out.println("--------------------------------");
        System.out.println("       Update successful");
        System.out.println("--------------------------------");
        sAge.set(index, age);
    }

    @Override
    public void editSub() {
        @SuppressWarnings("resource")
        Scanner sc = new Scanner(System.in);
        
        System.out.print("""
            ==============
            Pick an option 
            ==============
            1. enroll in more subject.
            2. drop a subject.
            
            Enter here: """);
        int deci = 0;
        boolean valid = false;
        while (!valid) {
            try {
                deci = sc.nextInt();
                while (deci<1||deci>2) {
                    System.out.print("Input is invalid, try again: ");
                    deci = sc.nextInt();
                }
                valid = true;
            } catch (Exception e) {
                System.out.print("Please enter a numerical value: ");
                sc.next();
            }
        }

        switch (deci) {
            case 1:
                System.out.println("\nwhich student would you like to edit");
                int index = iterator(sNames);
        
                for (int i = 0; i < subjects.size(); i++) {
                    System.out.println((1+i)+" "+subjects.get(i));
                }

                System.out.print("\npick a subject to add: ");

                int subIndex = 0;
                boolean validSubject = false;
                while (!validSubject) {
                    try {
                        subIndex = sc.nextInt();
                        while (subIndex<0||subIndex>Person.subjects.size()) {
                            System.out.print("that is not an option, try again: ");
                            subIndex = sc.nextInt();
                        }
                        validSubject = true;
                    } catch (Exception e) {
                        System.out.print("Please enter a number: ");
                        sc.next();
                    }
                }
                

                if (enrolledSubject.containsKey(sNames.get(index))) {
                    List<String> sublist = enrolledSubject.get(sNames.get(index));    
                    sublist.add(subjects.get(subIndex-1));
                    enrolledSubject.replace(sNames.get(index), sublist);
                }else{
                    List<String> newList = new ArrayList<>();
                    newList.add(Person.subjects.get(subIndex-1));
                    enrolledSubject.put(sNames.get(index), newList);
                }
                System.out.println("\nProcessing...");
                delayInSeconds(1);
                System.out.println("------------------------------------");
                System.out.println("  Successfully added the subject");
                System.out.println("------------------------------------");
                break;
            case 2:
                System.out.println("\nwhich student would you like to drop a subject");
                int index2 = iterator(sNames);

                if (enrolledSubject.containsKey(sNames.get(index2))) {
                    List<String> sublist1 = enrolledSubject.get(sNames.get(index2));

                    System.out.println("\nPick a subject to drop");
                    for (int i = 0; i < sublist1.size(); i++) {
                        System.out.println((1+i+". ")+sublist1.get(i));
                    }

                    boolean valid3 = false;
                    int drop = -1;
                    while (!valid3) {
                        try {
                            drop = sc.nextInt();
                            while (drop < 1||drop>sublist1.size()) {
                                System.out.print("Input is invalid, try again: ");
                                drop = sc.nextInt();
                            }
                            valid3 = true;
                        } catch (Exception e) {
                            System.out.print("Please enter a number: ");
                            sc.next();
                        }
                    }
                    sublist1.remove(drop);

                    System.out.println("\nProcessing...\n");
                    System.out.println("--------------------------------");
                    System.out.println("    Successfully dropped");
                    System.out.println("--------------------------------");

                }else{
                    System.out.println("=================================================");
                    System.out.println("That student does not have any enrolled subjects");
                    System.out.println("=================================================");
                    editSub();
                }
                break; 
            default:
                break;
        }
    }
}