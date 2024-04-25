package com.github.Tobimaru.cards;

import java.util.List;
import java.util.ArrayList;
import java.util.EmptyStackException;
import java.util.Collections;
import java.util.Random;

public class GameDeck implements DeckMethods {
    
    public List<PlayCard> myCards; //array of cards  
    
    private void add_from_deck(Deck a_deck){    
        for (PlayCard card : a_deck.get_cards()){
            this.myCards.add(card);
        }
    }
    
    public GameDeck(){
        this.myCards = new ArrayList<>();
    }
    
    public GameDeck(Deck a_deck){
        this();
        this.add_from_deck(a_deck);
    }
    
    public GameDeck(List<Deck> some_decks){
        this.myCards = new ArrayList<>();
        some_decks.stream().forEach((dk) -> {this.add_from_deck(dk);});
    }
    
    @Override
    public void shuffle(){
        Collections.shuffle(this.myCards);
    }
    
    @Override
    public PlayCard deal_card(){ 
        
        PlayCard sample_card = null;
        
        try{
            sample_card = this.myCards.remove(this.myCards.size()-1);
        }catch (EmptyStackException e){
            System.err.println("No more cards left!!");
        }
        
        return(sample_card);
    }
    
    @Override
    public final PlayCard pick_card(int index){
        PlayCard sample_card = null;
        
        try{
            sample_card = this.myCards.remove(index);
        }catch (EmptyStackException e){
            System.err.println("No more cards left!!");
        }
        
        return(sample_card);
    }
    
    @Override
    public PlayCard pick_random_card(){
        Random rand = new Random();
        int randomInt = rand.nextInt(this.myCards.size());
        return(this.myCards.remove(randomInt));
    }
}
