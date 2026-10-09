#include <iostream>
#include <cstdlib>
#include <string>
#include <unordered_map>
#include <vector>
#include <algorithm>
#include <random>

//The struct for a card data type
struct Card {
    std::string rank;
    int value;
    std::string suit;
};

class DeckHelper {
    private:
        // ANSI escape codes for colors
        const static std::string RESET;
        const static std::string BOLD;
        const static std::string WHITE;
        static std::unordered_map<std::string, std::string> SUIT_BACKGROUNDS;
        static bool FANCY_MODE;
    
        // Helpers to define what each card is or can be
        static std::vector<std::string> RANKS;
        static std::vector<std::string> SUITS;
        static std::unordered_map<std::string, int> RANK_VALUES; 
    public:
        /*
         * Method to create a new deck. 
         * Outputs an std::vector<Card> of all possible cards given the ranks and suits in RANKS[] and SUITS[]. 
         */
        static std::vector<Card> createDeck();
        
        /*
         * Method to shuffle a deck. 
         * Takes an std::vector<Card> (the deck). 
         * Outputs the shuffled deck. 
         */
        static std::vector<Card> shuffleDeck(std::vector<Card> cards);
        
    
        /*
         * Helper method to return the name of a rank. 
         * Takes in an int r corresponding to the value stored in deck[]. 
         * Outputs an std::string corresponding to the name of the rank entered. 
         */
        static int rankToValue(std::string rank);
        
        /*
         * Helper method to return a string to represent the card. 
         * Takes in an int r corresponding to the value stored in deck[]. 
         * Outputs an std::string corresponding to the name of the rank entered. 
         */
        static std::string formatCard(Card card);
        
        /*
         * Helper method to format a list of cards. 
         * Takes in an std::vector<Card>. 
         * Outputs an std::string corresponding to the name of the rank entered. 
         */
        static std::string formatCards(std::vector<Card> cards);
};