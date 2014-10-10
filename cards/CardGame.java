package cards;

import java.util.List;
import java.util.ArrayList;

public class CardGame {
    
    protected List<Deck> myDecks;
    protected GameDeck myGameDeck;
    
    public CardGame(int nmb_decks){ 
        
        this.myDecks = new ArrayList<>();
        
        for (int i = 0;i < nmb_decks;i++){
            this.myDecks.add(new Deck(i));
        }
       
        this.myGameDeck = new GameDeck(this.myDecks);
        
    }
}
