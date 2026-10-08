import java.util.Scanner;
import java.util.ArrayList;

public class Form {
    // Variables to store to values filled in to this form
    private String signer;
    private int signerIDNum;
    private String dateOfSignature;
    private String reason;
    private boolean givePermission;
    private double processingFee;
    
    // Static lastProcessingFee variable so that payment can be taken from forms filled through the user interface rather than initialized in the program. 
    private static double lastProcessingFee;
  
    // Array to store the lines of the screen
    protected static ArrayList<String> linesList = new ArrayList<String>();
  
    /*
     * This is the default constructor for this Form class. 
     */
    public Form() {
        this("John Doe", 12345678, "October 3, 2004", "N/A", true);
    }
    /*
     * This is a parameterized constructor for this Form class. 
     */
    public Form(String signer, int signerIDNum, String dateOfSignature) {
        this(signer, signerIDNum, dateOfSignature, "N/A", true);
    }
    /*
     * This is the fully parameterized constructor for this Form class. 
     */
    public Form(String signer, int signerIDNum, String dateOfSignature, String reason, boolean givePermission) {
        this.signer = signer;
        this.signerIDNum = signerIDNum;
        this.dateOfSignature = dateOfSignature;
        this.reason = reason;
        this.givePermission = givePermission;
        this.processingFee = calculateProcessingFee(signer, reason);
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
        linesList.add("| Form: FORM                                                                     |");  // 1
        linesList.add("|--------------------------------------------------------------------------------|");  // 2
        linesList.add("|                                                                                |");  // 3
        linesList.add("| Signer:     _______________                                                    |");  // 4
        linesList.add("| ID Number:  _______________                                                    |");  // 5
        linesList.add("| Date:       _______________                                                    |");  // 6
        linesList.add("|                                                                                |");  // 7
        linesList.add("|  Why did you fill out this form?                                               |");  // 8
        linesList.add("|  ____________________________________________________________________________  |");  // 9
        linesList.add("|  ____________________________________________________________________________  |");  // 10
        linesList.add("|  ____________________________________________________________________________  |");  // 11
        linesList.add("|                                                                                |");  // 12
        linesList.add("| Give FormCorp full permission to                                               |");  // 13
        linesList.add("| access all information about you? (y/n): ____                                  |");  // 14
        linesList.add("|                                                                                |");  // 15
        linesList.add("| Processing Fee:  ________________________                                      |");  // 16
        linesList.add("|                                                                                |");  // 17
        linesList.add("|                                                                                |");  // 18
        linesList.add("|                                                                                |");  // 19
        linesList.add("|                                                                                |");  // 20
        linesList.add("|                                                                                |");  // 21
        linesList.add("|                                                                                |");  // 22
        linesList.add("|                                                                                |");  // 23
        linesList.add("+================================================================================+");  // 24
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
        
        /* reason */
        Screen.pause(2000);
        System.out.println("");
        System.out.println("You enter the reason:");
        String reasonText = input.nextLine().trim();
        ArrayList<String> reasonLines = Screen.wrapText(reasonText, 76);
        // Verify that reason exists
        while(reasonText.isEmpty()) {
            System.out.println("The reason cannot be blank.");
            Screen.pause(2000);
            System.out.println("Try again: ");
            reasonText = input.nextLine().trim();
            reasonLines = Screen.wrapText(reasonText, 76);
        }
        linesList.set(9, Screen.createLine(" "+reasonLines.get(0)));
        if(reasonLines.size() > 1) { linesList.set(10, Screen.createLine(" "+reasonLines.get(1))); } else { linesList.set(10, Screen.createLine(" "+reasonLines.get(0))); linesList.set(9, Screen.createLine(" ")); }
        if(reasonLines.size() > 2) { linesList.set(11, Screen.createLine(" "+reasonLines.get(2))); } else { linesList.set(11, Screen.createLine(" ")); }
        Screen.clearScreen();
        Screen.printScreen(linesList);
    
        /* permission */
        Screen.pause(2000);
        System.out.println("");
        System.out.println("You type y to give permission: ");
        String permission = input.nextLine().toLowerCase();
        // Verify permission is given
        while(!(permission.equals("y") || permission.equals("yes"))) {
            if(permission.equals("n") || permission.equals("no")) {
                if(karma <= 0) {arrest(); return 0;}
                anger("What is the meaning of this?", "We have been nothing but kind and trustwothy to you since the dawn of time. ", "Try again.");
                karma--;
            }
            else {
                System.out.println("You type y to give permission: ");
            }
            permission = input.nextLine().toLowerCase();
        }
        linesList.set(14, Screen.createLine("access all information about you? (y/n): OF COURSE, I NEVER DOUBT FORMCORP®"));
        Screen.clearScreen();
        Screen.printScreen(linesList);
        
        /* processing fee */
        lastProcessingFee = calculateProcessingFee(signer, reasonText);
        Screen.pause(2000);
        System.out.println("");
        System.out.println("You enter the processing fee of $"+lastProcessingFee+": ");
        double fee = input.nextDouble();
        input.nextLine();
        // Verify that fee is correct
        if(fee != lastProcessingFee) {
            anger("You didn't seriously think you could get away with that, right?", "We take fraud very seriously. ", "No more second chances. ");
            arrest();
            return 0;
        }
        linesList.set(16, Screen.createLine("Processing Fee:  $" + lastProcessingFee));
        Screen.clearScreen();
        Screen.printScreen(linesList);
        linesList.clear();
        
        return karma;
    }

    /*
     * This method is used to calculate the processing fee for the form. 
     */
    public static double calculateProcessingFee(String signer, String reason) {
        return (double)(signer.length() + reason.length()/10) + 19.99;
    }

    /*
     * These methods are the getter methods for all the important attributes of this class. 
     */
    public String getSigner() {
        return signer;
     }
    public int getSignerIDNum() {
        return signerIDNum;
    }
    public String getDateOfSignature() {
        return dateOfSignature;
    }
    public String getReason() {
        return reason;
    }
    public double getProcessingFee() {
        return processingFee;
    }
    public boolean getGivePermission() {
        return givePermission;
    }
    public static double getLastProcessingFee() {
        return lastProcessingFee;
    }

    /*
     * This is the toString() method for this class, which prints out all the fields of the class in a readable format (other than givePermission, which is given to be true since it is required for the form to be accepted). 
     */
    public String toString() {
        return "This form was filled out and signed by " + signer + " with ID#" + signerIDNum + " on " + dateOfSignature + " with a $" + processingFee + " processing fee. The reasoning given for why this form was signed was \"" + reason + "\". ";
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