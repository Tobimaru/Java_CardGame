package cards;

public final class PlayCard {
    
    public final Rank myRank; //indicates rank of card
    public final Suit mySuit; //indicates symbol of card
    
    //myDeck is package private
    private Deck myDeck; 
    
    public PlayCard(Rank new_rank,Suit new_suit){
        this.myRank = new_rank;
        this.mySuit = new_suit;
        this.myDeck = null;
    }
    
    public final Deck get_deck(){
       //Should return the parent deck as immutable object 
       return this.myDeck;
    }
    
    void set_deck(Deck new_deck){
        
        this.myDeck = new_deck; 
    }
    
    public String print(){
        
        String print_str = this.myRank.toString() + "(" +
                           this.mySuit.toString() + ")";
        return(print_str);
        
    }
    
}
