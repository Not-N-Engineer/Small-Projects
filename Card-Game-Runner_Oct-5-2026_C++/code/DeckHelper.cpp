#include "DeckHelper.h"

#include <iostream>
#include <cstdlib>
#include <string>
#include <vector>

//The struct for a card data type
struct Card {
    std::string rank;
    int value;
    std::string suit;
};

const static std::string DeckHelper::RESET = "\033[0m";
const static std::string DeckHelper::BOLD = "\033[1m";
const static std::string DeckHelper::WHITE = "\033[97m";
static unordered_map<std::string, std::string> DeckHelper::SUIT_BACKGROUNDS = {
    {"spades", "\033[40m"},    // black
    {"hearts", "\033[41m"},    // red
    {"clubs", "\033[44m"},     // blue
    {"diamonds", "\033[43m"},  // yellow
};
static boolean DeckHelper::FANCY_MODE = true;
    
// Helpers to define what each card is or can be
static std::vector<std::string> DeckHelper::RANKS = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K", "A"};
static std::vector<std::string> DeckHelper::SUITS = {"spades", "hearts", "clubs", "diamonds"};
static unordered_map<std::string, int> DeckHelper::RANK_VALUES = {{"J", 10}, {"Q", 10}, {"K", 10}, {"A", 11}}; 

/*
 * Method to create a new deck. 
 * Outputs an std::vector<Card> of all possible cards given the ranks and suits in RANKS[] and SUITS[]. 
 */
static std::vector<Card> DeckHelper::createDeck() {
    std::vector<Card> cards;
    for(std::string rank : DeckHelper::RANKS) {
        for(std::string suit : DeckHelper::SUITS) {
            Card new_card;
            new_card.rank = rank;
            new_card.value = DeckHelper::rankToValue(rank);
            new_card.suit = suit;
            cards.push_back(new_card);
        }
    }
    return cards;
}
        
/*
 * Method to shuffle a deck. 
 * Takes an std::vector<Card> (the deck). 
 * Outputs the shuffled deck. 
 */
static std::vector<card> DeckHelper::shuffleDeck(std::vector<card> cards) {
    if (cards.size() <= 1) { return cards; }
    else { return sample(cards); }
}
        
    
/*
 * Helper method to return the name of a rank. 
 * Takes in an int r corresponding to the value stored in deck[]. 
 * Outputs an std::string corresponding to the name of the rank entered. 
 */
static int DeckHelper::rankToValue(std::string rank) {
    if(std::atoi(rank)) {
        return DeckHelper::RANK_VALUES.at(rank);
    }
    else {
        return std::atoi(rank);
    }
}
        
/*
 * Helper method to return a string to represent the card. 
 * Takes in an int r corresponding to the value stored in deck[]. 
 * Outputs an std::string corresponding to the name of the rank entered. 
 */
static std::string DeckHelper::formatCard(Card card) {
    if(DeckHelper::FANCY_MODE) {
        return DeckHelper::BOLD+DeckHelper::WHITE+DeckHelper::SUIT_BACKGROUNDS.at(card.suit)+"["+card.rank+"]"+DeckHelper::RESET;
    }
    else {
        return card.rank + " of " + card.suit;
    }
}
        
/*
 * Helper method to format a list of cards. 
 * Takes in an std::vector<Card>. 
 * Outputs an std::string corresponding to the name of the rank entered. 
 */
static std::string DeckHelper::formatCards(std::vector<Card> cards) {
    std::string formatted_cards = formatCard(cards.at(0));
    for(Card card : cards) {
        formatted_cards += ", " + DeckHelper::formatCard(card);
    }
    return formatted_cards;
}