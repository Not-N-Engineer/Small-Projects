import java.util.Scanner;
import java.util.ArrayList;

public class ComplicatedLegalForm extends Form {
    // Variables that a legal form has on top of everything a regular Form has
    private boolean waiveRights;
    private boolean swearAllegiance;
    private boolean releaseAllProperty;
    private boolean agreeToAll;
    
    // Static lastProcessingFee variable so that payment can be taken from forms filled through the user interface rather than initialized in the program. 
    private static double lastProcessingFee;
  
    // Array to store the lines of the screen
    private static ArrayList<String> linesList = new ArrayList<String>();
  
    /*
     * This is the default constructor for this Form class. 
     */
    public ComplicatedLegalForm() {
        this("John Doe", 12345678, "October 3, 2004", "N/A", true, true, true, true, true);
    }
    /*
     * This is a parameterized constructor for this Form class. 
     */
    public ComplicatedLegalForm(String signer, int signerIDNum, String dateOfSignature, boolean givePermission) {
        this(signer, signerIDNum, dateOfSignature, "N/A", givePermission, true, true, true, true);
    }
    /*
     * This is the fully parameterized constructor for this Form class. 
     */
    public ComplicatedLegalForm(String signer, int signerIDNum, String dateOfSignature, String reason, boolean givePermission, boolean clause1, boolean clause2, boolean clause3, boolean clause4) {
        super(signer, signerIDNum, dateOfSignature, reason, givePermission);
        this.waiveRights = clause1;
        this.swearAllegiance = clause2;
        this.releaseAllProperty = clause3;
        this.agreeToAll = clause4;
    }

    /*
     * This is the method that prompts the user to give all the information required to fill the form. 
     * This method requires a Scanner object to take input, as well as the signer's name, ID number, and the date to verify that no information is entered incorrectly. 
     * Since name, ID number, and date were already given at the start of the program, requiring the user to fill out the info again is just to double check and let the user know to restart if anything was entered wrong. 
     * This method also takes the user's karma and returns their knew karma after the form is finished.
     */
    public static int fillForm(Scanner input, String signer, int signerIDNum, String dateOfSignature, int karma) {
        linesList.clear();
        linesList.add("+================================================================================+");  // 0
        linesList.add("| Form: COMPLICATED LEGAL FORM                                                   |");  // 1
        linesList.add("|--------------------------------------------------------------------------------|");  // 2
        linesList.add("|                                                                                |");  // 3
        linesList.add("| Signer:     _______________                                                    |");  // 4
        linesList.add("| ID Number:  _______________                                                    |");  // 5
        linesList.add("| Date:       _______________                                                    |");  // 6
        linesList.add("|                                                                                |");  // 7
        
        ArrayList<String> legalLines = Screen.wrapText("WHEREAS the party of the first part, the signer, acknowledges that the party of the second part, FormCorp®, is never wrong, and WHEREAS this agreement is binding in perpetuity, and WHEREAS it is hereby agreed that this contract constitutes the final and absolute expression of the parties' intentions, the signer hereby initials the following clauses:", 78);
        for(String legalLine : legalLines) {                                          
            linesList.add(Screen.createLine(" " + legalLine));
        }
        
        linesList.add("|                                                                                |");  // 14
        linesList.add("| 1. Waive all rights, past, present, and future? (y/n): ____                    |");  // 15
        linesList.add("| 2. Swear eternal allegiance to FormCorp®? (y/n): ____                          |");  // 16
        linesList.add("| 3. Release all of your property to FormCorp®? (y/n): ____                      |");  // 17
        linesList.add("| 4. Agree to all of the above and anything added later? (y/n): ____             |");  // 18
        linesList.add("|                                                                                |");  // 19
        linesList.add("| Give FormCorp full permission to                                               |");  // 20
        linesList.add("| access all information about you? (y/n): ____                                  |");  // 21
        linesList.add("|                                                                                |");  // 22
        linesList.add("| Processing Fee:  ________________________                                      |");  // 23
        linesList.add("|                                                                                |");  // 24
        linesList.add("+================================================================================+");  // 25
        Screen.printScreen(linesList);
        
        /* name */
        Screen.pause(2000);
        System.out.println("");
        System.out.println("You enter your name:");
        String name = input.nextLine();
        // Verify that name is correct
        while(!name.equals(signer)) {
            if(karma <= 0) {arrest(); return 0;}
            anger("You wouldn't be lying to the company...", "Right?", "Try again.");
            karma--;
            name = input.nextLine();
        }
        linesList.set(4, Screen.createLine("Signer:     "+signer));
        Screen.clearScreen();
        Screen.printScreen(linesList);
        
        /* id */
        Screen.pause(2000);
        System.out.println("");
        System.out.println("You enter your ID number: ");
        int idNumber = input.nextInt();
        input.nextLine(); // To fix the nextLine thing being skipped
        // Verify that id is correct
        while(idNumber != signerIDNum) {
            if(karma <= 0) {arrest(); return 0;}
            anger("You wouldn't be lying to the company...", "Right?", "Try again.");
            karma--;
            idNumber = input.nextInt();
            input.nextLine();
        }
        linesList.set(5, Screen.createLine("ID Number:  "+signerIDNum));
        Screen.clearScreen();
        Screen.printScreen(linesList);
        
        /* date */
        Screen.pause(2000);
        System.out.println("");
        System.out.println("You enter the date: ");
        String date = input.nextLine();
        // Verify that date is correct
        while(!date.equals(dateOfSignature)) {
            if(karma <= 0) {arrest(); return 0;}
            anger("You wouldn't be lying to the company...", "Right?", "Try again.");
            karma--;
            date = input.nextLine();
        }
        linesList.set(6, Screen.createLine("Date:       "+dateOfSignature));
        Screen.clearScreen();
        Screen.printScreen(linesList);
        
        /* agreeing to all the clauses */
        Screen.pause(2000);
        System.out.println("");
        System.out.println("Yeah: ");
        String agree = input.nextLine().toLowerCase();
        // Verify that permission is correct
        while(!(agree.equals("y") || agree.equals("yes"))) {
            if(agree.equals("n") || agree.equals("no")) {
                if(karma <= 0) {arrest(); return 0;}
                anger("What is the meaning of this?", "We have been nothing but kind and trustwothy to you since the dawn of time. ", "Try again.");
                karma--;
            }
            else {
                System.out.println("Please answer y or n:");
            }
            agree = input.nextLine().toLowerCase();
        }
        linesList.set(14, Screen.createLine("1. Waive all rights, past, present, and future? (y/n): ye"));
        Screen.clearScreen();
        Screen.printScreen(linesList);
        System.out.println("");
        System.out.println("Sure: ");
        agree = input.nextLine().toLowerCase();
        // Verify that permission is correct
        while(!(agree.equals("y") || agree.equals("yes"))) {
            if(agree.equals("n") || agree.equals("no")) {
                if(karma <= 0) {arrest(); return 0;}
                anger("What is the meaning of this?", "We have been nothing but kind and trustwothy to you since the dawn of time. ", "Try again.");
                karma--;
            }
            else {
                System.out.println("Please answer y or n:");
            }
            agree = input.nextLine().toLowerCase();
        }
        linesList.set(15, Screen.createLine("2. Swear eternal allegiance to FormCorp®? (y/n): y"));
        Screen.clearScreen();
        Screen.printScreen(linesList);
        System.out.println("");
        System.out.println("Whatever that means, yes: ");
        agree = input.nextLine().toLowerCase();
        // Verify that permission is correct
        while(!(agree.equals("y") || agree.equals("yes"))) {
            if(agree.equals("n") || agree.equals("no")) {
                if(karma <= 0) {arrest(); return 0;}
                anger("What is the meaning of this?", "We have been nothing but kind and trustwothy to you since the dawn of time. ", "Try again.");
                karma--;
            }
            else {
                System.out.println("Please answer y or n:");
            }
            agree = input.nextLine().toLowerCase();
        }
        linesList.set(16, Screen.createLine("3. Release all of your property to FormCorp®? (y/n): /"));
        Screen.clearScreen();
        Screen.printScreen(linesList);
        System.out.println("");
        System.out.println("Ugh, why isn't there just an agree all button? Who cares to read all this stuff?: ");
        agree = input.nextLine().toLowerCase();
        // Verify that permission is correct
        while(!(agree.equals("y") || agree.equals("yes"))) {
            if(agree.equals("n") || agree.equals("no")) {
                if(karma <= 0) {arrest(); return 0;}
                anger("What is the meaning of this?", "We have been nothing but kind and trustwothy to you since the dawn of time. ", "Try again.");
                karma--;
            }
            else {
                System.out.println("Please answer y or n:");
            }
            agree = input.nextLine().toLowerCase();
        }
        linesList.set(17, Screen.createLine("4. Agree to all of the above and anything added later? (y/n): /"));
        Screen.clearScreen();
        Screen.printScreen(linesList);
    
        /* permission */
        Screen.pause(2000);
        System.out.println("");
        System.out.println("You give permission: ");
        String permission = input.nextLine().toLowerCase();
        // Verify that permission is correct
        while(!(permission.equals("y") || permission.equals("yes"))) {
            if(permission.equals("n") || permission.equals("no")) {
                if(karma <= 0) {arrest(); return 0;}
                anger("What is the meaning of this?", "We have been nothing but kind and trustwothy to you since the dawn of time. ", "Try again.");
                karma--;
            }
            else {
                System.out.println("Please answer y or n:");
            }
            permission = input.nextLine().toLowerCase();
        }
        linesList.set(20, Screen.createLine("access all information about you? (y/n): yes"));
        Screen.clearScreen();
        Screen.printScreen(linesList);
        
        /* processing fee */
        lastProcessingFee = 19.99;
        Screen.pause(2000);
        System.out.println("");
        System.out.println("You the processing fee of $"+lastProcessingFee+": ");
        double fee = input.nextDouble();
        input.nextLine();
        // Verify that fee is correct
        if(fee != lastProcessingFee) {
            anger("You didn't seriously think you could get away with that, right?", "We take fraud very seriously", "No more second chances. ");
            arrest();
            return 0;
        }
        linesList.set(22, Screen.createLine("Processing Fee:  $" + lastProcessingFee));
        Screen.clearScreen();
        Screen.printScreen(linesList);
        linesList.clear();
        
        return karma;
    }

    /*
     * These methods are the getter methods for all the important attributes of this class. 
     */
    public boolean getWaiveRights() {
        return waiveRights;
     }
    public boolean getSwearAllegiance() {
        return swearAllegiance;
    }
    public boolean getReleaseAllProperty() {
        return releaseAllProperty;
    }
    public boolean getAgreeToAll() {
        return agreeToAll;
    }
    public static double getLastProcessingFee() {
        return lastProcessingFee;
    }
    
    /*
     * This is the toString() method for this class, which prints out all the fields of the class in a readable format (other than givePermission, which is given to be true since it is required for the form to be accepted). 
     */
    public String toString() {
        return super.toString() + "They agreed, if specified as true, to the following clauses: the \"Waive Rights\" clause, "+waiveRights+";the \"Swear Allegiance\" clause, "+swearAllegiance+"; the \"Release Property\" clause, "+releaseAllProperty+"; the \"Agree to Everything\" clause, "+agreeToAll+". ";
    }

    /*
     * Two methods for animation that triggers when a special event happens, like something is filled out wrong or the user makes too many mistakes. 
     */
    public static void anger(String message1, String message2, String message3) {
        Screen.clearScreen();
        Screen.printEyeScreen();
        System.out.println("");
        System.out.println(message1);
        
        Screen.pause(2000);
        Screen.clearScreen();
        Screen.printAngryEyeScreen();
        System.out.println("");
        System.out.println(message2);
        
        Screen.pause(2000);
        Screen.clearScreen();
        Screen.printScreen(linesList);
        System.out.println("");
        System.out.println(message3);
    }
    public static void arrest() {
        Screen.clearScreen();
        Screen.printAngryEyeScreen();
        
        Screen.pause(2000);
        Screen.clearScreen();
        Screen.printBlackScreen();
        System.out.println("");
        System.out.println("You hear sirens outside of your window. ");
    }  
}