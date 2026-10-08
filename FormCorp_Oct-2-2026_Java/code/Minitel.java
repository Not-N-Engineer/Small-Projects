import java.util.Scanner;
import java.util.ArrayList;


public class Minitel {
    // Variables to store the user's name, ID, amount of money, and the date upon logging in
    private static String name;
    private static int idNumber;
    private static String date;
    private static double money = 18.34;
    
    // Variables to store whether or not each form is completed or being completed
    private static int formState = 0; // 0 means not completed, 1 means is being compeled, and 2 means completed. 
    private static int paymentFormState = 0;
    private static int complicatedLegalFormState = 0;
    private static int dogWashingFormState = 0;
    private static int breathingFormState = 0;
    private static boolean allFormsComplete = false; 
    private static boolean firstTimeOnHomePage = true;
  
    // Array to store the lines of the screen
    private static ArrayList<String> linesList = new ArrayList<String>();



    /*
     * This method takes the user's name, id number, and the date for later verification when filling out forms
     */
    public static void userLogIn(Scanner input) {
        linesList.add("+================================================================================+");     // 0
        linesList.add("|                         _     _          _    _   _   _                        |");     // 1
        linesList.add("|                   \\/\\/ |- |_ (_ () |\\/| |-   |-{ |-| (_ |< !                   |");  // 2
        linesList.add("|                         ‾                ‾    ‾                                |");     // 3
        linesList.add("|--------------------------------------------------------------------------------|");     // 4
        linesList.add("| Please log in:                                                                 |");     // 5
        linesList.add("|                                                                                |");     // 6
        linesList.add("| Name:       _______________                                                    |");     // 7
        linesList.add("| ID Number:  _______________                                                    |");     // 8
        linesList.add("| Date:       _______________                                                    |");     // 9
        linesList.add("|                                                                                |");     // 10
        linesList.add("|                                                                                |");     // 11
        linesList.add("|                                                                                |");     // 12
        linesList.add("|                                                                                |");     // 13
        linesList.add("|                                                                                |");     // 14
        linesList.add("|                                                                                |");     // 15
        linesList.add("|                                                                                |");     // 16
        linesList.add("|                                                                                |");     // 17
        linesList.add("|                                                                                |");     // 18
        linesList.add("|                                                                                |");     // 19
        linesList.add("|                                                                                |");     // 20
        linesList.add("|                                                                                |");     // 21
        linesList.add("|                                                                                |");     // 22
        linesList.add("|                                                                                |");     // 23
        linesList.add("+================================================================================+");     // 24
        Screen.printScreen(linesList);
        Screen.pause(2000);
        System.out.println("");
        System.out.println("Hmmm... what was your name again?");
        name = input.nextLine();
        // Verify that name exists
        while(name.isEmpty()) {
            System.out.println("You don't remember your name?");
            Screen.pause(2000);
            System.out.println("Whatever, it's probably Nothing. ");
            name = "Nothing";
        }
        
        linesList.set(7, Screen.createLine("Name:       "+name));
        Screen.clearScreen();
        Screen.printScreen(linesList);
        Screen.pause(2000);
        System.out.println("");
        System.out.println("Alright, now your 8-digit ID number that you got when you first started working: ");
        idNumber = input.nextInt();
        input.nextLine(); // To fix the nextLine thing being skipped
    
        linesList.set(8, Screen.createLine("ID Number:  "+idNumber));
        Screen.clearScreen();
        Screen.printScreen(linesList);
        Screen.pause(2000);
        System.out.println("");
        System.out.println("Ok, now the easy part: ");
        date = input.nextLine();
        // Verify that date exists
        while(date.isEmpty()) {
            System.out.println("Seriously?");
            date = "November 8, 2018";
        }
    
        linesList.set(9, Screen.createLine("Date:       "+date));
        Screen.clearScreen();
        Screen.printScreen(linesList);
        linesList.clear();
    }
  
  
  
    public static void printTerminalHome(Scanner input) {
        linesList.add("+================================================================================+");     // 0
        linesList.add("|                _                                                               |");     // 1
        linesList.add("|   |-| () |\\/| |-                                  You have: $"+String.format("%-18s", String.format("%.2f", money))+"|");     // 2
        linesList.add("|                ‾                                                               |");     // 3
        linesList.add("|--------------------------------------------------------------------------------|");     // 4
        linesList.add("| " + String.format("%-79s", "Hello "+name+",") + "|");                                              // 5
        linesList.add("| You have indicated that the following forms are due or past due:               |");     // 6
        linesList.add("|                                                                                |");     // 7
        
        // Checking if each form is done and adding a strikethrough if so (there is definitely a better way of doing this, but I am too lazy to find it)
        if(formState == 0) {linesList.add("| - Form (Tutorial).                                                             |");}     // 8
        else {linesList.add("| - "+"F\u0336o\u0336r\u0336m\u0336 \u0336(\u0336T\u0336u\u0336t\u0336o\u0336r\u0336i\u0336a\u0336l\u0336)\u0336.\u0336"+"                                                             |");}     // 8
        
        if(paymentFormState == 0) {linesList.add("| - Payment Form to Johnathaniel Baker for $32.99 for muffin this morning.       |");}     // 9
        else {linesList.add("| - "+"P\u0336a\u0336y\u0336m\u0336e\u0336n\u0336t\u0336 \u0336F\u0336o\u0336r\u0336m\u0336 \u0336t\u0336o\u0336 \u0336J\u0336o\u0336h\u0336n\u0336a\u0336t\u0336h\u0336a\u0336n\u0336i\u0336e\u0336l\u0336 \u0336B\u0336a\u0336k\u0336e\u0336r\u0336 \u0336f\u0336o\u0336r\u0336 \u0336$\u03363\u03362\u0336.\u03369\u03369\u0336 \u0336f\u0336o\u0336r\u0336 \u0336m\u0336u\u0336f\u0336f\u0336i\u0336n\u0336 \u0336t\u0336h\u0336i\u0336s\u0336 \u0336m\u0336o\u0336r\u0336n\u0336i\u0336n\u0336g\u0336.\u0336"+"       |");}     // 9
        
        if(complicatedLegalFormState == 0) {linesList.add("| - Complicated Legal Form that you should probably just skim over.              |");}     // 10
        else {linesList.add("| - "+"C\u0336o\u0336m\u0336p\u0336l\u0336i\u0336c\u0336a\u0336t\u0336e\u0336d\u0336 \u0336L\u0336e\u0336g\u0336a\u0336l\u0336 \u0336F\u0336o\u0336r\u0336m\u0336 \u0336t\u0336h\u0336a\u0336t\u0336 \u0336y\u0336o\u0336u\u0336 \u0336s\u0336h\u0336o\u0336u\u0336l\u0336d\u0336 \u0336p\u0336r\u0336o\u0336b\u0336a\u0336b\u0336l\u0336y\u0336 \u0336j\u0336u\u0336s\u0336t\u0336 \u0336s\u0336k\u0336i\u0336m\u0336 \u0336o\u0336v\u0336e\u0336r\u0336.\u0336"+"              |");}     // 10
        
        if(dogWashingFormState == 0) {linesList.add("| - Dog Washing Form for Barksley.                                               |");}     // 11
        else {linesList.add("| - "+"D\u0336o\u0336g\u0336 \u0336W\u0336a\u0336s\u0336h\u0336i\u0336n\u0336g\u0336 \u0336F\u0336o\u0336r\u0336m\u0336 \u0336f\u0336o\u0336r\u0336 \u0336B\u0336a\u0336r\u0336k\u0336s\u0336l\u0336e\u0336y\u0336.\u0336"+"                                               |");}     // 11
        
        if(breathingFormState == 0) {linesList.add("| - Breathing Form for tomorrow's alottled 20,000 breaths!                       |");}     // 12
        else {linesList.add("| - "+"B\u0336r\u0336e\u0336a\u0336t\u0336h\u0336i\u0336n\u0336g\u0336 \u0336F\u0336o\u0336r\u0336m\u0336 \u0336f\u0336o\u0336r\u0336 \u0336t\u0336o\u0336m\u0336o\u0336r\u0336r\u0336o\u0336w\u0336'\u0336s\u0336 \u0336a\u0336l\u0336o\u0336t\u0336t\u0336l\u0336e\u0336d\u0336 \u03362\u03360\u0336,\u03360\u03360\u03360\u0336 \u0336b\u0336r\u0336e\u0336a\u0336t\u0336h\u0336s\u0336!\u0336"+"                       |");}     // 12
        
        linesList.add("|                                                                                |");     // 13
        
        // Checking if all forms are done to end program. 
        if(formState == 2 && paymentFormState == 2 && dogWashingFormState == 2 && breathingFormState == 2) {
            allFormsComplete = true;
            linesList.add("| Unfortunately, there are no more forms to fill out.                            |");     // 14
            linesList.add("| Go to sleep now.                                                               |");     // 15
            linesList.add("|                                                                                |");     // 16
            linesList.add("|                                                                                |");     // 17
            linesList.add("|                                                                                |");     // 18
            linesList.add("|                                                                                |");     // 19
            linesList.add("|                                                                                |");     // 20
            linesList.add("|                                                                                |");     // 21
            linesList.add("|                                                                                |");     // 22
            linesList.add("|                                                                                |");     // 23
            linesList.add("+================================================================================+");     // 24
            Screen.printScreen(linesList);
            Screen.pause(2000);
            return;
        }
        else {
            linesList.add("| Which would you like to do first?:                                             |");     // 14
            linesList.add("| > ▮                                                                            |");     // 15
            linesList.add("|                                                                                |");     // 16
            linesList.add("|                                                                                |");     // 17
            linesList.add("|                                                                                |");     // 18
            linesList.add("|                                                                                |");     // 19
            linesList.add("|                                                                                |");     // 20
            linesList.add("|                                                                                |");     // 21
            linesList.add("|                                                                                |");     // 22
            linesList.add("|                                                                                |");     // 23
            linesList.add("+================================================================================+");     // 24
            Screen.printScreen(linesList);
            Screen.pause(2000);
            System.out.println("");
            if(firstTimeOnHomePage) {
                System.out.println("Wow, a lot to do today! What FUN! I'm SO EXCITED! WHAT DO WE DO FIRST!?!");
                firstTimeOnHomePage = false;
            }
            else {
                System.out.println("What next?");
            }
            String form = input.nextLine();
            
            while(!((form.toLowerCase().equals("form") && formState != 2)
                 || (form.toLowerCase().equals("payment form") && paymentFormState != 2) 
                 || (form.toLowerCase().equals("complicated legal form") && complicatedLegalFormState != 2)
                 || (form.toLowerCase().equals("dog washing form") && dogWashingFormState != 2)
                 || (form.toLowerCase().equals("breathing form") && breathingFormState != 2))) {
                System.out.println("Hmmmmmm... I don't think that's one of my forms left for today...");
                Screen.pause(2000);
                System.out.println("Try again?");
                form = input.nextLine();
            }
            
            linesList.set(15, Screen.createLine("> "+form+"▮"));
            Screen.clearScreen();
            Screen.printScreen(linesList);
            linesList.clear();
            
            if(form.toLowerCase().equals("form")) { formState = 1; }
            else if(form.toLowerCase().equals("payment form")) { paymentFormState = 1; }
            else if(form.toLowerCase().equals("complicated legal form")) { complicatedLegalFormState = 1; }
            else if(form.toLowerCase().equals("dog washing form")) { dogWashingFormState = 1; }
            else if(form.toLowerCase().equals("breathing form")) { breathingFormState = 1; }
            
            return;
        }
    }
    
    
  
    public static void main(String[] args) {
        // Creates a Scanner object - feel free to delete if not using!
        Scanner input = new Scanner(System.in);
    
        // Starts the user's karma, so they can be arrested if they break too many rules
        int karma = 15;
    
        //Intro
        System.out.println("You get back home after a long day of working at the FormCorp® facilities.");
        Screen.pause(2000);
        System.out.println("You log on to your FormCorp® Minitel to relax and fill out some forms. \n");
        Screen.pause(2000);
        userLogIn(input);
        Screen.pause(2000);
        
        while(!allFormsComplete) {
            if(karma == 0) { break; }
            
            Screen.clearScreen();
            printTerminalHome(input);
            Screen.pause(2000);
            Screen.clearScreen();
            
            // Allowing the user to fill out the form that they chose
            if(formState == 1) { karma = Form.fillForm(input, name, idNumber, date, karma); formState = 2; money -= Form.getLastProcessingFee(); }
            else if(paymentFormState == 1) { karma = PaymentForm.fillForm(input, name, idNumber, date, karma); paymentFormState = 2; money -= PaymentForm.getLastProcessingFee(); }
            else if(complicatedLegalFormState == 1) { karma = ComplicatedLegalForm.fillForm(input, name, idNumber, date, karma); complicatedLegalFormState = 2; money -= ComplicatedLegalForm.getLastProcessingFee(); }
            else if(dogWashingFormState == 1) { karma = DogWashingForm.fillForm(input, name, idNumber, date, karma); dogWashingFormState = 2; money -= DogWashingForm.getLastProcessingFee(); }
            else if(breathingFormState == 1) { karma = BreathingForm.fillForm(input, name, idNumber, date, karma); breathingFormState = 2; money -= BreathingForm.getLastProcessingFee(); }
        }
        
        Screen.pause(2000);
        Screen.clearScreen();
        Screen.printBlackScreen();
        Screen.pause(2000);
        System.out.println("");
        System.out.println("...");
        Screen.pause(2000);
        Screen.clearScreen();
    
    
        // Closes the Scanner object
        input.close();
    }
}