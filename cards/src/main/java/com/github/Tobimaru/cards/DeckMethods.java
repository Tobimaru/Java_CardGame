package com.github.Tobimaru.cards;

public interface DeckMethods {
    
    void shuffle();
    PlayCard pick_card(int index);
    PlayCard pick_random_card();
    PlayCard deal_card();
    
}

