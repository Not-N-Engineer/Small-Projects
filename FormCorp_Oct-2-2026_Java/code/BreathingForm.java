import java.util.Scanner;
import java.util.ArrayList;

public class BreathingForm extends Form {
    // Variables that a payment form has on top of everything a regular Form has
    private int maxAllottedBreaths;
    private int breathsTaken;
    private double taxesOwed;
    private double rebate;
    
    // Static lastProcessingFee variable so that payment can be taken from forms filled through the user interface rather than initialized in the program. 
    private static double lastProcessingFee;
  
    // Array to store the lines of the screen
    private static ArrayList<String> linesList = new ArrayList<String>();
  
    /*
     * This is the default constructor for this Form class. 
     */
    public BreathingForm() {
        this("John Doe", 12345678, "October 3, 2004", "N/A", true, 20000, 19500, 19.99);
    }
    /*
     * This is a parameterized constructor for this Form class. 
     */
    public BreathingForm(String signer, int signerIDNum, String dateOfSignature, int breathsTaken) {
        this(signer, signerIDNum, dateOfSignature, "N/A", true, 20000, breathsTaken, 19.99);
    }
    /*
     * This is the fully parameterized constructor for this Form class. 
     */
    public BreathingForm(String signer, int signerIDNum, String dateOfSignature, String reason, boolean givePermission, int maxAllottedBreaths, int breathsTaken, double taxesOwed) {
        super(signer, signerIDNum, dateOfSignature, reason, givePermission);
        this.maxAllottedBreaths = maxAllottedBreaths;
        this.breathsTaken = breathsTaken;
        this.taxesOwed = taxesOwed;
        this.rebate = (maxAllottedBreaths - breathsTaken)*0.005;
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
        linesList.add("| Form: Breathing FORM                                                           |");  // 1
        linesList.add("|--------------------------------------------------------------------------------|");  // 2
        linesList.add("|                                                                                |");  // 3
        linesList.add("| Signer:     _______________                                                    |");  // 4
        linesList.add("| ID Number:  _______________                                                    |");  // 5
        linesList.add("| Date:       _______________                                                    |");  // 6
        linesList.add("|                                                                                |");  // 7
        linesList.add("| Allotted Breaths: ____________________                                         |");  // 8
        linesList.add("| Taken Breaths:    ____________________                                         |");  // 9
        linesList.add("| Taxes Owed:       ____________________                                         |");  // 10
        linesList.add("| Rebate Amount:    ____________________                                         |");  // 11
        linesList.add("|                                                                                |");  // 12
        linesList.add("|  Why did you fill out this form?                                               |");  // 13
        linesList.add("|  ____________________________________________________________________________  |");  // 14
        linesList.add("|  ____________________________________________________________________________  |");  // 15
        linesList.add("|  ____________________________________________________________________________  |");  // 16
        linesList.add("|                                                                                |");  // 17
        linesList.add("| Give FormCorp full permission to                                               |");  // 18
        linesList.add("| access all information about you? (y/n): ____                                  |");  // 19
        linesList.add("|                                                                                |");  // 20
        linesList.add("| Processing Fee:  ________________________                                      |");  // 21
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
        
        /* allotted breaths */
        Screen.pause(2000);
        System.out.println("");
        System.out.println("You enter number of breathes you are normally allowed to take: ");
        int allottedBreaths = input.nextInt();
        input.nextLine();
        // Verify that breath count is correct
        while(allottedBreaths != 20000) {
            if(karma <= 0) {arrest(); return 0;}
            anger("You wouldn't be lying to the company...", "Right?", "Try again.");
            karma--;
            allottedBreaths = input.nextInt();
            input.nextLine();
        }
        linesList.set(8, Screen.createLine("Allotted Breaths: 20,000"));
        Screen.clearScreen();
        Screen.printScreen(linesList);
        
        /* breaths taken */
        Screen.pause(2000);
        System.out.println("");
        System.out.println("You really aren't suppoed to fill these form in advance, since it'll be fraud if you take more than the amount of breaths you give yourself...");
        System.out.println("but a little bit of rebate money can't hurt: ");
        int takenBreaths = input.nextInt();
        input.nextLine();
        // Verify that breaths taken is possible
        while(takenBreaths <= 15000) {
            System.out.println("There's no way you can survive taking so few breaths, not with your warehouse job. ");
            System.out.println("Try a different number: ");
            takenBreaths = input.nextInt();
            input.nextLine();
        }
        while(takenBreaths > 20000) {
            System.out.println("Thats way too many breaths.");
            System.out.println("Try a different number: ");
            takenBreaths = input.nextInt();
            input.nextLine();
        }
        linesList.set(9, Screen.createLine("Taken Breaths:    "+String.valueOf(takenBreaths).substring(0, 2)+","+String.valueOf(takenBreaths).substring(2)));
        Screen.clearScreen();
        Screen.printScreen(linesList);
        
        /* taxes owed */
        double taxesOwed = allottedBreaths*0.0001;
        Screen.pause(2000);
        System.out.println("");
        System.out.println("You enter the taxes owed of $"+taxesOwed+": ");
        double tax = input.nextDouble();
        input.nextLine();
        // Verify that tax is correct
        if(tax != taxesOwed) {
            anger("Taxes oil the gears of our FormCorp® machine. ", "We take tax fraud very seriously. ", "We're a family here. ");
            arrest();
            return 0;
        }
        linesList.set(10, Screen.createLine("Taxes Owed:       $" + taxesOwed));
        Screen.clearScreen();
        Screen.printScreen(linesList);
        
        /* rebate */
        double rebate = (allottedBreaths - takenBreaths)*0.005;
        Screen.pause(2000);
        System.out.println("");
        System.out.println("You enter your rebate of $"+rebate+": ");
        double rebateEntered = input.nextDouble();
        input.nextLine();
        // Verify that rebate is correct
        if(rebateEntered != rebate) {
            System.out.println("Try entering it correctly: ");
            rebateEntered = input.nextDouble();
            input.nextLine();
        }
        linesList.set(11, Screen.createLine("Rebate Amount:    $" + rebate));
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
        linesList.set(14, Screen.createLine(" "+reasonLines.get(0)));
        if(reasonLines.size() > 1) { linesList.set(15, Screen.createLine(" "+reasonLines.get(1))); } else { linesList.set(15, Screen.createLine(" "+reasonLines.get(0))); linesList.set(14, Screen.createLine(" ")); }
        if(reasonLines.size() > 2) { linesList.set(16, Screen.createLine(" "+reasonLines.get(2))); } else { linesList.set(16, Screen.createLine(" ")); }
        Screen.clearScreen();
        Screen.printScreen(linesList);
    
        /* permission */
        Screen.pause(2000);
        System.out.println("");
        System.out.println("You give permission: ");
        String permission = input.nextLine().toLowerCase();
        // Verify permission is given
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
        linesList.set(19, Screen.createLine("access all information about you? (y/n): Yes"));
        Screen.clearScreen();
        Screen.printScreen(linesList);
        
        /* processing fee */
        lastProcessingFee = calculateProcessingFee(signer, reasonText);
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
        linesList.set(21, Screen.createLine("Processing Fee:  $" + lastProcessingFee));
        lastProcessingFee -= rebate;
        Screen.clearScreen();
        Screen.printScreen(linesList);
        linesList.clear();
        
        return karma;
    }

    /*
     * These methods are the getter methods for all the important attributes of this class. 
     */
    public int getMaxAllottedBreaths() {
        return maxAllottedBreaths;
     }
    public int getBreathsTaken() {
        return breathsTaken;
    }
    public double getTaxesOwed() {
        return taxesOwed;
    }
    public double getRebateAmount() {
        return rebate;
    }
    public static double getLastProcessingFee() {
        return lastProcessingFee;
    }
    
    /*
     * This is the toString() method for this class, which prints out all the fields of the class in a readable format (other than givePermission, which is given to be true since it is required for the form to be accepted). 
     */
    public String toString() {
        return super.toString() + "The signer claimed an allotted number of breaths of "+maxAllottedBreaths+" and having taken " + breathsTaken + " of them, oweing a $" + taxesOwed + " tax and recieving a $"+rebate+" rebate.";
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