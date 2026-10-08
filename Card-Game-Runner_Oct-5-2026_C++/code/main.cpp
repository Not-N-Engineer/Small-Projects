#include <iostream>
#include <cstdlib>
#include <string>
#include <vector>

// Prototypes
struct Card;

class DeckHelper {
    public:
        const static std::vector<Card> createDeck();
        const static std::vector<card> shuffleDeck(std::vector<card> cards);
        const static int rankToValue(std::string rank);
        const static std::string formatCard(Card card);
        const static std::string formatCards(std::vector<Card> cards)
}

int main()
{
    // Deck
    vector<Card> deck = DeckHelper::createDeck();
    
    return 0;
}