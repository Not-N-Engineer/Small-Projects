#include "CardConsole.h"

#include <iostream>
#include <string>
#include <vector>
#include <string_view>
#include <ranges>

/*
 * This is a method that is called when the user chooses to open the Console. 
 * It contains a loop that repeatedly takes commands. 
 */
void CardConsole::openConsole() {
    bool quit = false;
    std::string input;
    std::vector<std::vector<std::string>> commands;
    
    while(!quit) {
        std::cout<<"> ";
        std::cin>>input;
        commands = processInput(input);
        
        for(std::vector<std::string> command : commands) {
            quit = performCommand(command);
            if(quit) { return; }
        }
    }
}
   
/*
 * This is a method that processes the string the user enters into a series of commands.   
 * It takes in a string which is the command and calls the correct static method. 
 * It outputs a vector that contains a vector for each command that user entered on the line. 
 */
std::vector<std::vector<std::string>> CardConsole::processInput(std::string input) {
    std::vector<std::string> raw_commands;
    std::vector<std::vector<std::string>> commands;
    
    // split text into each command, seperated by ";"
    std::string_view text{input};
    for(auto&& subrange : text | std::views::split(text, ';')) {
        raw_commands.emplace_back(subrange.begin(), subrange.end());
    }
    
    // split each command into the word components of the commands
    for(std::string command : raw_commands) {
        std::vector<std::string> words;
        std::string_view text{command};
        for(auto&& subrange : text | std::views::split(text, ' ')) {
            words.emplace_back(subrange.begin(), subrange.end());
        }
        commands.push_back(words);
    }
    
    return commands;
}
     
/*
 * This is a method that performs the command the user enters.  
 * It takes in a string which is the command and calls the correct static method. 
 */
bool CardConsole::performCommand(std::vector<std::string> command) {
    return false;
}