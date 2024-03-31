package main;

import java.util.Scanner;

public class MethodCaller extends MidtermProject {

    public static Scanner sc = new Scanner(System.in);
    static boolean run = true;
    static int decision;
    static Instructor inst = new Instructor(); 
    static Student st = new Student();
    static SearchFor search = new SearchFor();
    
    public void methodcaller(int dec){
        switch (dec) {
            case 1:
                decider();
                if (decision==1) {
                    inst.add(decision);
                }else if(decision == 2){
                    st.add(decision);
                }else if (decision == 3) {
                    Subject.addSubToList();
                }       
                returner();
                break;
            case 2:
                decider();
                edit(decision);
                returner();
                break;
            case 3:
                search.searchSub();
                returner();
                break;
            case 4:
                search.searchINST();
                returner();
                break;
            case 5:
                search.searchST();
                returner();
                break;
            case 6:
                decider();
                Person.display(decision);
                returner();
                break;
            case 7:
                goodBye();
                break;
            default:
                break;
        }
    }

    public static void decider(){
        System.out.print("Pick one of the options displayed. \n1. teacher. \n2. students. \n3. subject");
        boolean valid = false;
        while (!valid) {
            try {
                System.out.print("\nEnter Here: ");
                decision = sc.nextInt();
                while (decision < 1|| decision>3) {
                    System.out.print("out of bounds, try again: ");
                    decision = sc.nextInt();
                }   
                valid = true;
            } catch (Exception e) {
                System.out.println("input is invalid, try again.");
                sc.next();
            }
        } 
    }

    public void returner(){
        decision =0;
        boolean valid = false;
        int dec;
        System.out.print("\nwould you like to return to home screen? \n[1] yes\n[2] no\nENTER HERE: ");
        while (!valid) {
            try {
                dec = sc.nextInt();
                while (dec<1||dec>2) {
                    System.out.print("that is not an option, try again: ");
                    dec = sc.nextInt();
                }
                if (dec == 1) {
                    optionHandler();
                }else{
                    goodBye();
                }
            } catch (Exception e) {
                System.out.print("PLease enter a numerical value: ");
                sc.next();
            }  
        }
    }

    public static void edit(int deci){
        switch (deci) {
            case 1:
                inst.edit();
                break;
            case 2:
                st.edit();
                break;
            case 3:
                Subject.edit();
                break;
            default:
                break;
        }
    }

    public void goodBye(){
        System.out.println("""
                
    ▀█▀ █░█ ▄▀█ █▄░█ █▄▀   █▄█ █▀█ █░█   █▀▀ █▀█ █▀█   █░█ █▀ █ █▄░█ █▀▀   █▀█ █░█ █▀█   █▀█ █▀█ █▀█ █▀▀ █▀█ ▄▀█ █▀▄▀█
    ░█░ █▀█ █▀█ █░▀█ █░█   ░█░ █▄█ █▄█   █▀░ █▄█ █▀▄   █▄█ ▄█ █ █░▀█ █▄█   █▄█ █▄█ █▀▄   █▀▀ █▀▄ █▄█ █▄█ █▀▄ █▀█ █░▀░█

    ▄▀█ █▄░█ █▀▄   █░█ ▄▀█ █░█ █▀▀   ▄▀█   █▄░█ █ █▀▀ █▀▀   █▀▄ ▄▀█ █▄█
    █▀█ █░▀█ █▄▀   █▀█ █▀█ ▀▄▀ ██▄   █▀█   █░▀█ █ █▄▄ ██▄   █▄▀ █▀█ ░█░
                """);
    }
}
