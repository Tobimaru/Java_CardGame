package com.github.Tobimaru.cards;
    
public enum Suit{
    SPADES (0),
    HEARTS (13),
    DIAMONDS (26),
    CLUBS (39);
        
    public final int nmb_id;
        
    Suit(int id_numb){
        this.nmb_id = id_numb;
    }
    
    public static Suit[] sortvalues(){
        /**This function returns the enumeration values 
         * in sorted ascending order.
         */
        Suit[] sort_suits = {SPADES, HEARTS, DIAMONDS, CLUBS};
        return sort_suits;
    }
        
}
   
