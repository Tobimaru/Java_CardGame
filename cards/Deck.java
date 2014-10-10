package cards;

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

public final class Deck {
    
    private final List<PlayCard> myCards = new ArrayList<>();
    private final List<Suit> mySuits = new ArrayList<>();
    private final List<Rank> myRanks = new ArrayList<>();
    
    public Integer myId; //number to id deck
    
    private void create_deck(){
       for (Suit st : Suit.sortvalues()){
           for (Rank rk : Rank.sortvalues()){
               this.myCards.add(new PlayCard(rk,st));
               this.myCards.get(this.myCards.size()-1).set_deck(this);
               this.mySuits.add(st);
               this.myRanks.add(rk);
           }
       } 
    }
    
    public Deck(Integer new_id){
        this.myId = new_id; 
        this.create_deck();
    }
    
    public int get_id(){
        return(this.myId);
    }
    
    public List<PlayCard> get_cards(){
        return(Collections.unmodifiableList(this.myCards));
    }
    
    public List<Suit> get_suits(){   
        return(Collections.unmodifiableList(this.mySuits));
    }
    
    public List<Rank> get_ranks(){   
        return(Collections.unmodifiableList(this.myRanks));
    }
}
