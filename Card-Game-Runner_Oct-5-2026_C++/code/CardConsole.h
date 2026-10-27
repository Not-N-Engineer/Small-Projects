#include <iostream>
#include <string>
#include <vector>
#include <string_view>
#include <ranges>

class CardConsole {
    private:
        
        
    public:
        /*
         * This is a method that is called when the user chooses to open the Console. 
         * It contains a loop that repeatedly takes commands. 
         */
        static void openConsole();
           
        /*
         * This is a method that processes the string the user enters into a series of commands.   
         * It takes in a string which is the command and calls the correct static method. 
         * It outputs a vector that contains a vector for each command that user entered on the line. 
         */
        static std::vector<std::vector<std::string>> processInput(std::string input);
             
        /*
         * This is a method that performs the command the user enters.  
         * It takes in a string which is the command and calls the correct static method. 
         */
        static bool performCommand(std::vector<std::string> command);
};