package com.github.Tobimaru.cards;

import java.util.List;
import java.util.ArrayList;
import java.util.Deque;
import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Scanner;

public class BlackJack extends CardGame {
    
    public static void print_info(){
        
        System.out.println("Welcome to Yet Another Casino!");
        System.out.println("This is the BlackJack table with "
                           + "the following rules:");
        System.out.println("Stand on all 17, no hole, one split.");
    }
    
    private final static int BJ_BUST_VAL = 21;
    private final static int BJ_DEALER_THRESH = 17;
    
    private static enum BJ_STATUS{
        VALID(1,""),
        BUST(0,"BUST"),
        TWONE(2,""),
        BLACKJACK(3,"blackjack");
        
        public final String message;
        public final int rank;
    
        //constructor of enum must be defined private or package-private
        BJ_STATUS(int rk,String message_str){
            this.rank = rk;
            this.message = message_str;
        }
    };
    
    private static class BJValue{
        
        int soft;
        int hard;
        
        BJValue(){
            this.soft = 0;
            this.hard = 0;
            
        }
        
        void add(final BJValue a_val){
            this.soft += a_val.soft;
            this.hard += a_val.hard;
        }       
        
    }
    
    private static final HashMap<Rank,BJValue> BJ_CARD_VALUES = new HashMap<Rank,BJValue>();
    
    private static void assign_card_values(){
       if (BJ_CARD_VALUES.isEmpty()){
            for (Rank rk : Rank.values()){
                BJValue a_val = new BJValue();
                
                switch (rk){
                    case ACE: {
                        a_val.soft = 1;
                        a_val.hard = 11; 
                        break;}
                    case TWO: {
                        a_val.soft = 2;
                        a_val.hard = 2;
                        break;}
                    case THREE: {
                        a_val.soft = 3;
                        a_val.hard = 3;
                        break;}
                    case FOUR: {
                        a_val.soft = 4;
                        a_val.hard = 4;
                        break;}
                    case FIVE: {
                        a_val.soft = 5;
                        a_val.hard = 5;
                        break;}
                    case SIX: {
                        a_val.soft = 6;
                        a_val.hard = 6;
                        break;}
                    case SEVEN: {
                        a_val.soft = 7;
                        a_val.hard = 7;
                        break;}
                    case EIGHT: {
                        a_val.soft = 8;
                        a_val.hard = 8;
                        break;}
                    case NINE: {
                        a_val.soft = 9;
                        a_val.hard = 9;
                        break;}        
                    case TEN: 
                    case JACK:
                    case QUEEN:
                    case KING: a_val.soft = 10;
                               a_val.hard = 10;
                               break; 
                }
                
                BJ_CARD_VALUES.put(rk, a_val);
            } 
       }
    }
    
    private class BJHand{
        
        BJValue myValue;
        Deque<PlayCard> myCards;
        BJ_STATUS mySoftStatus;
        BJ_STATUS myHardStatus;
        BJ_STATUS myStatus;
        int myHandValue; 
        String myWinStatus;
        boolean alsoSoft;
        boolean canSplit;
        
        BJHand(){
            this.myValue = new BJValue();
            this.myCards = new ArrayDeque<>();
            this.myHardStatus = BJ_STATUS.VALID;
            this.mySoftStatus = this.myHardStatus;
            this.myStatus = this.myHardStatus;
            this.myHandValue = 0;
            myWinStatus = " ";
            this.alsoSoft = false;
            this.canSplit = false;
            
        }
        
        private void update_hand(final PlayCard new_card){
        
            this.check_splittable(new_card);
            this.myCards.add(new_card);
            this.myValue.add(BJ_CARD_VALUES.get(new_card.myRank));
            this.check_status();

        }   
        
        private void check_splittable(final PlayCard new_card){
            
            if (this.myCards.size() == 1){ 
               this.canSplit = this.myValue.hard == 
                               BJ_CARD_VALUES.get(new_card.myRank).hard;
            }else{
                this.canSplit = false;
            }
        }
        
        private BJ_STATUS check_bust_or_twone(final int a_val){
            
            //check if values are busted or 21
            
            BJ_STATUS new_status = BJ_STATUS.VALID;
            
            if (a_val >= BJ_BUST_VAL){ 
                if (a_val > BJ_BUST_VAL){new_status = BJ_STATUS.BUST;}
                else{new_status = BJ_STATUS.TWONE;}                    
            }
            
            return(new_status); 
        
        }
        
        private void check_status(){
            //check if the soft value is different
            if (this.myValue.hard != this.myValue.soft){this.alsoSoft = true;} 
            
            if (this.myCards.size() == 2){ 
                
               if (this.myValue.hard == BJ_BUST_VAL){
                   this.myHardStatus = BJ_STATUS.BLACKJACK;
               }
               this.myStatus = this.myHardStatus;
               this.myHandValue = this.myValue.hard;
               
            }else if(this.myCards.size() > 2){
                this.myHardStatus = this.check_bust_or_twone(this.myValue.hard);
                
                if (this.alsoSoft){
                    this.mySoftStatus = 
                            this.check_bust_or_twone(this.myValue.soft);
                }else{
                    this.mySoftStatus = this.myHardStatus;
                }
                
                if (this.myHardStatus == BJ_STATUS.BUST){
                  this.myStatus = this.mySoftStatus;
                  this.myHandValue = this.myValue.soft;
                }else{
                  this.myStatus = this.myHardStatus;
                  this.myHandValue = this.myValue.hard;  
                }
            }
        }
        
        public String print_cards(){
            String hand_str = "";
            for (PlayCard card : this.myCards){
                hand_str += card.print() + " ";
            }
            return(hand_str);
        }
        
        public String print(){
            
            String value_str = this.print_cards();
            value_str += String.format(" %d ",this.myValue.hard);
            value_str += this.myHardStatus.message;
            value_str += "  ";
            if (this.alsoSoft){
                value_str+= String.format(" %d(soft) ",this.myValue.soft);
                value_str += this.mySoftStatus.message;
            }
            
            return(value_str);
        }
    } 
    
    private BJHand dealer_hand; 
    private List<BJHand> player_hand;
    private int player_curr_hand;
    private final int player_max_split = 1;
    private int player_nmb_split;  
    private Scanner player_reader;
    
    public BlackJack(int nmb_decks){
        
        super(nmb_decks); //calling parent constructor CardGame(nmb_decks)
        
        assign_card_values();
        
        this.dealer_hand = new BJHand();
        
        this.player_hand = new ArrayList<>();
        this.player_hand.add(new BJHand());
        
        this.player_curr_hand = 0;
        //this.player_max_split = 1;
        this.player_nmb_split = 0;
        this.player_reader = new Scanner(System.in);
       
    }
    
    private boolean check_dealer_status(){
        //Dealer stands on all 17
        boolean continue_flag = this.dealer_hand.myValue.hard < BJ_DEALER_THRESH;
        if (!continue_flag){
            continue_flag = this.dealer_hand.myValue.soft < BJ_DEALER_THRESH;
        }
        return(continue_flag);
    }
    
    private boolean check_player_hand_status(){
        //false if busted, blackjack or 21
        BJHand curr_hand = this.player_hand.get(this.player_curr_hand);
        
        boolean continue_flag = false; 
                
        if(!(curr_hand.myStatus == BJ_STATUS.BLACKJACK)){ 
           continue_flag = curr_hand.myStatus == BJ_STATUS.VALID;
        }else{         
           if (this.player_hand.size() > 1){
               //BlackJack in split hands counts as regular 21
                curr_hand.myStatus = BJ_STATUS.TWONE;  
           }
        }
        
        return(continue_flag);
    }
    
    private boolean check_player_status(){
        
        boolean keep_going = false;
        for (BJHand hand : this.player_hand){
            if (hand.myStatus != BJ_STATUS.BUST){
                keep_going = true;
                break;
            } 
        }
        return(keep_going);
    }
    
    public void display_status(final boolean win_opt){
        //add option that selects to also display winning or losing status
        
        String dealer_str = String.format("%nDealer:%n");
        String player_str = String.format("Player:%n");
        
        //print dealer hand and value
        dealer_str += this.dealer_hand.print();
        dealer_str += String.format("%n");
        
        if (!win_opt){    
            //print player hands and values      
            for (int i=0;i<this.player_hand.size();i++){
               player_str += String.format("Hand %d: ",i+1);
               player_str += this.player_hand.get(i).print();
               player_str += String.format("%n");
            }
        }else{
            //print player win status      
            for (int i=0;i<this.player_hand.size();i++){
               player_str += String.format("Hand %d: ",i+1);
               player_str += this.player_hand.get(i).print_cards();
               player_str += this.player_hand.get(i).myWinStatus;
               player_str += String.format("%n");
            }
        }
        
        System.out.print(dealer_str);
        System.out.print(player_str);
           
    }
    
    public void initiate_game(){
        
        for (int i=0;i<3*this.myDecks.size();i++){
             this.myGameDeck.shuffle();
        }
   
        //Player receives two consecutive cards for first (and only) hand.
        for (int i=0;i<2;i++){
            this.player_hand.get(0).update_hand(this.myGameDeck.deal_card());
        }
        //Dealer receives only first card. This is a no-hole card game
        this.dealer_hand.update_hand(this.myGameDeck.deal_card());
     
        this.display_status(false);
     
    }
    
    public boolean player_continue(){
        
        if (this.check_player_hand_status()){
        
            BJHand curr_hand = this.player_hand.get(this.player_curr_hand);
            boolean can_split = curr_hand.canSplit &&
                                (this.player_nmb_split < this.player_max_split);
            
            String choices_str = "hit or stand?";
            if (can_split){choices_str = "hit, stand or split?";}
            System.out.println(String.format("Enter choice for hand %d: ",
                                            this.player_curr_hand+1));
            System.out.println(choices_str);
 
            String player_decision = this.player_reader.next();

            //TO DO: make case insensitive
            switch(player_decision) {
                case "hit": this.player_hand.get(this.player_curr_hand).
                            update_hand(this.myGameDeck.deal_card());
                            if (!this.check_player_status()){
                              this.player_curr_hand += 1;
                            }
                            break;

                case "stand": this.player_curr_hand += 1;
                              break;

                case "split": if (!curr_hand.canSplit){
                                    System.out.println("You cannot split!!");
                              }else if (this.player_nmb_split ==
                                        this.player_max_split) {

                                    System.out.println(
                                        "You already have maximum number "
                                                + "of splits allowed");

                              }else{
                                    //remove last card from hand and create
                                    //new hand
                                    PlayCard split_card = curr_hand.
                                                          myCards.removeLast();
                                    int next_spot = this.player_curr_hand+1;
                                    this.player_hand.add(next_spot,new BJHand());
                                    this.player_hand.get(next_spot).
                                                        update_hand(split_card);
                                    
                                    //remove last card and recreate 
                                    //current hand (to reset value).
                                    split_card = curr_hand.myCards.removeLast();
                                    this.player_hand.remove(this.player_curr_hand);
                                    this.player_hand.add(this.player_curr_hand,
                                                         new BJHand());
                                    curr_hand = this.player_hand.
                                                get(this.player_curr_hand);
                                    curr_hand.update_hand(split_card);
                                    
                                    //Deal additional cards for both hands
                                    curr_hand.update_hand(
                                              this.myGameDeck.deal_card());
                                    this.player_hand.get(next_spot).
                                        update_hand(this.myGameDeck.deal_card());
                                    this.player_nmb_split += 1;        
                              }
                              break;  
                default: System.out.println("Please select valid option!!");
            }

            this.display_status(false);
            
        }else{this.player_curr_hand += 1;}
        
        //This should mean that next hand index is outside list of hands, so stop.
        return(!(this.player_curr_hand == this.player_hand.size()));
        
    }
    
    public boolean dealer_continue(){
        
        this.dealer_hand.update_hand(this.myGameDeck.deal_card());
        this.display_status(false);
        return(this.check_dealer_status());
    }
    
    public void print_game_outcome(){
        
        for (BJHand hand : this.player_hand){
           
           hand.myWinStatus = "LOST!";
           
           if (hand.myStatus.rank > this.dealer_hand.myStatus.rank){
               hand.myWinStatus = "WON!";  
               
           }else if (hand.myStatus == this.dealer_hand.myStatus){
          
               if (hand.myStatus == BJ_STATUS.VALID){
                  if (hand.myHandValue > this.dealer_hand.myHandValue){
                     hand.myWinStatus = "WON!"; 
                  }else if(hand.myHandValue == this.dealer_hand.myHandValue){
                     hand.myWinStatus = "PUSH!"; 
                  }
               }else{
                   hand.myWinStatus = "PUSH!";
               }
           }
        }
        
        
        
        this.display_status(true); 
    }
    
    public void play_game(int pause_time) throws InterruptedException{
       
        //initiate game
        this.initiate_game();
        
        boolean keep_going = true;
        
        //Keep playing until all player hands have been played.
        do {keep_going = this.player_continue();
           // Thread.sleep(pause_time);
        }while(keep_going);
        
        Thread.sleep(pause_time/2);
        
        if (this.check_player_status()){
            System.out.print(String.format("%n")); 
            System.out.println("Dealer's turn ..."); 
            do {keep_going = this.dealer_continue();
                Thread.sleep(pause_time); 
            }while(keep_going);
        }
        
        this.print_game_outcome();
        
    }

}
