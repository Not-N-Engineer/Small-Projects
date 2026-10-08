package code;
import java.util.Scanner;

public class CellAutomatonTester
{
    //ANSI Color Codes
    static String ANSI_RESET = "\u001B[0m";
    static String ANSI_RED = "\u001B[41m";
    static String ANSI_GREEN = "\u001B[42m";
    static String ANSI_WHITE = "\u001B[97m";
    
    //Game Variables
    static Scanner input = new Scanner(System.in);
    static int[] cursorPosition = {0, 0};
    static int dimensions;
    
    
    //Method to display a new state
    public static String display(String comment)
    {
        String currentLine = "";
        String state = "";
        
        System.out.println("-----" + comment + "-----");
        for(int i = 0; i < dimensions; i++)
        {
            for(int j = 0; j < dimensions; j++)
            {
                if(cursorPosition[0] == i && cursorPosition[1] == j)
                {
                    if(Cell.cells.get(i*dimensions + j).state)
                    {
                        currentLine += ANSI_GREEN + ANSI_WHITE + "[O]" + ANSI_RESET;
                    }
                    else
                    {
                        currentLine += ANSI_RED + ANSI_WHITE + "[O]" + ANSI_RESET;
                    }
                }
                else
                {
                    if(Cell.cells.get(i*dimensions + j).state)
                    {
                        currentLine += ANSI_GREEN + "[O]" + ANSI_RESET;
                    }
                    else
                    {
                        currentLine += ANSI_RED + "[O]" + ANSI_RESET;
                    }
                }
            }
            
            System.out.println(currentLine);
            state += "\n" + currentLine;
            currentLine = "";
        }
        
        String series = "";
        for(int i = 0; i < comment.length(); i++)
        {
            series += "-";
        }
        System.out.println("-----" + series + "-----");
        
        return state;
    }
    
    
    //Method to determine where a user wants a live cell to be
    public static void cursor(String movement, String command)
    {
        char currentChar;
        for(int i = 0; i < movement.length(); i++)
        {
            currentChar = movement.charAt(i);
            
            if(currentChar == '+' && cursorPosition[0] > 0)
            {
                cursorPosition[0]--;
            }
            else if(currentChar == '-' && cursorPosition[0] < dimensions-1)
            {
                cursorPosition[0]++;
            }
            else if(currentChar == '<' && cursorPosition[1] > 0)
            {
                cursorPosition[1]--;
            }
            else if(currentChar == '>' && cursorPosition[1] < dimensions-1)
            {
                cursorPosition[1]++;
            }
            else
            {
                System.out.println("Out of Bounds");
                break;
            }
            
            if(command.equals("setAliveInMovement"))
            {
                Cell.cells.get(cursorPosition[0]*dimensions + cursorPosition[1]).setState(true);
            }
            else if(command.equals("setDeadInMovement"))
            {
                Cell.cells.get(cursorPosition[0]*dimensions + cursorPosition[1]).setState(false);
            }
        }
        
        if(command.equals("setAlive"))
        {
            Cell.cells.get(cursorPosition[0]*dimensions + cursorPosition[1]).setState(true);
        }
        else if(command.equals("setDead"))
        {
            Cell.cells.get(cursorPosition[0]*dimensions + cursorPosition[1]).setState(false);
        }
        else if(command.equals("setAllAlive"))
        {
            Cell.setAllStates(true);
        }
        else if(command.equals("setAllDead"))
        {
            Cell.setAllStates(false);
        }
    }
    
    
    
    
    //Helper Functions
    //Method to delay the running of the program
    public static void delay(int milliseconds)
    {
        try 
        {
            Thread.sleep(milliseconds);
        } 
        catch (InterruptedException e) 
        {
            Thread.currentThread().interrupt();
            System.err.println("Thread was interrupted: " + e.getMessage());
        }

    }
    
    
    //Tests if a string can be turned into an integer
    public static boolean isInteger(String str) {
        try 
        {
            Integer.parseInt(str);
            return true;
        } 
        catch (NumberFormatException e) 
        {
            return false;
        } 
        catch (NullPointerException e) 
        {
            return false;
        }
    }
    
    
    //Splits a cursor input into a movement and a command
    public static String[] splitCommand(String str, String cursor)
    {
        char currentChar;
        String[] outputArr = {"", ""};
        
        //If no movement is entered
        if(!(str.charAt(0) == '+' || str.charAt(0) == '-' || str.charAt(0) == '<' || str.charAt(0) == '>'))
        {
            outputArr[0] = "";
            outputArr[1] = str;
            return outputArr;
        }
        
        for(int i = 0; i < str.length(); i++)
        {
            currentChar = str.charAt(i);
            
            if(!Character.isWhitespace(currentChar))
            {
                outputArr[0] += currentChar;
            }
            else
            {
                outputArr[1] = str.substring(i+1, str.length());
                return outputArr;
            }
        }
        return outputArr;
    }
    public static String[] splitCommand(String str)
    {
        char currentChar;
        String[] outputArr = {"", ""};
        
        for(int i = 0; i < str.length(); i++)
        {
            currentChar = str.charAt(i);
            
            if(!Character.isWhitespace(currentChar))
            {
                outputArr[0] += currentChar;
            }
            else
            {
                outputArr[1] = str.substring(i+1, str.length());
                return outputArr;
            }
        }
        return outputArr;
    }
    
    
    
    
    public static void main(String[] args)
    {
        //Setting up
        System.out.println("Program Started!\n---------------------------------");
        System.out.println("Set the dimensions of the square screen. Enter one integer. ");
        while(true)
        {
            String temp = input.nextLine();
            if(isInteger(temp))
            {
                dimensions = Integer.parseInt(temp);
                
                for(int i = 0; i < dimensions; i++)
                {
                    for(int j = 0; j < dimensions; j++)
                    {
                        String name = "Cell" + i + j;
                        Cell newCell = new Cell(name, i, j);
                        newCell.findNumAliveNeighbors(); // So it stops saying there is an error -- Added Oct 7, 2026
                    }
                }
                break;
            }
            else
            {
                System.out.println("\nNot an integer. Try again:");
                continue;
            }
        }
        display("Before Initialization");
        
        
        
        
        //Setting up rules
        System.out.println("Set up the rules you like. Type \"help\" if you need help. Type \"stop\" when you are satisfied.");
        String rule;
        String newValue;
        String[] tempArr1 = new String[2];
        while(true)
        {
            System.out.println("Command: ");
            rule = input.nextLine();
            
            if(rule.equals("help"))
            {
                System.out.println("\nSend commands in the format: [rule] + [new value]. Ex: \" AffectedRadius 3\". ");
                System.out.println("Rules:");
                System.out.println("\"AffectRadius\" is the radius which each cell checks for living neighbors. Default is 1. Takes any integer. ");
                System.out.println("\"CheckDiagonals\" dicates whether or not the affected radius affects diagonals. Default is true. Takes any boolean. \n");
                continue;
            }
            else if(rule.equals("stop"))
            {
                break;
            }
            else if(rule.equals(""))
            {
                System.out.println("\nNo input given. Try again.");
                continue;
            }
            
            //Checking validity and cleaning up input
            tempArr1 = splitCommand(rule);
            rule = tempArr1[0];
            newValue = tempArr1[1].replace(" ", "");
            if(rule.equals("AffectRadius") && isInteger(newValue))
            {
                int value = Integer.parseInt(newValue);
                Cell.setAllRadii(value);
            }
            else if(rule.equals("CheckDiagonals") && newValue instanceof String)
            {
                if(newValue.equals("true") || newValue.equals("True"))
                {
                    Cell.setAllIfDiagonals(true);
                }
                else if(newValue.equals("false") || newValue.equals("False"))
                {
                    Cell.setAllIfDiagonals(false);
                }
                else
                {
                    System.out.println("\nInvalid input: " + rule + ", " + newValue + " Try again.");
                    continue;
                }
            }
            else
            {
                System.out.println("\nInvalid input: \"" + rule + "\", \"" + newValue + "\". Try again.");
                continue;
            }
        }
        
        
        
        
        //Initializing states
        System.out.println("\nSet up the starting state. Type \"help\" if you need help. Type \"stop\" when you are satisfied.");
        String command;
        String movement;
        String[] tempArr = new String[2];
        while(true)
        {
            System.out.println("Command: ");
            command = input.nextLine();
            
            if(command.equals("help"))
            {
                System.out.println("\nSend commands in the format: [movement] + space + [command]. Ex: \"<<<++ setAlive\". ");
                System.out.println("You can also type just a movement or just a command. ");
                System.out.println("Movement: A string of any number length of the characters < > + -");
                System.out.println("'<' moves the cursor to the left.");
                System.out.println("'>' moves the cursor to the right.");
                System.out.println("'+' moves the cursor up.");
                System.out.println("'-' moves the cursor down.");
                System.out.println("Commands: \"setAlive\" \"setDead\" \"setAliveInMovement\" \"setDeadInMovement\" \"setAllAlive\" \"setAllDead\"");
                System.out.println("The cursor's position is indicated by the cell highlighted in white. \n");
                continue;
            }
            else if(command.equals("stop"))
            {
                break;
            }
            else if(command.equals(""))
            {
                System.out.println("\nNo input given. Try again.");
                continue;
            }
            
            //Checking validity and cleaning up input
            tempArr = splitCommand(command, "poodles");
            command = tempArr[1].replace(" ", "");
            movement = tempArr[0];
            if(!(command.equals("setAlive") || command.equals("setDead") || command.equals("setAliveInMovement") || command.equals("setDeadInMovement") || command.equals("setAllAlive") || command.equals("setAllDead") || command.equals("")))
            {
                System.out.println("\nInvalid command: \"" + command + "\". Try again.");
                continue;
            }
            cursor(movement, command);
            display("New State");
        }
        
        display("Start State");
        
        
        
        
        //Running States
        String previousState = "";
        String currentState = "";
        int stateNum = 0;
        
        delay(1000);
        Cell.runAllOnce();
        stateNum++;
        previousState = display("State " + stateNum);
        
        while(true)
        {
            String repeat;
            System.out.println("Run again? Enter the number of steps you want to take or type \"stop\": ");
            repeat = input.nextLine();
            if(!repeat.equals("stop") && !isInteger(repeat))
            {
                System.out.println("Not an integer. Try again.");
                continue;
            }
            
            if(repeat.equals("stop"))
            {
                System.out.println("---------------------------------\nProgram Ended.");
                break;
            }
            else
            {
                for(int i = 0; i < Integer.parseInt(repeat); i++)
                {
                    delay(1000);
                    Cell.runAllOnce();
                    stateNum++;
                    currentState = display("State " + stateNum);
                    
                    if(currentState.equals(previousState))
                    {
                        System.out.println("---------------------------------\nProgram Ended. Final State Reached.");
                        return;
                    }
                    else
                    {
                        previousState = currentState;
                    }
                }
            }
        }
    }
}