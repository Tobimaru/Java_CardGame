package com.github.Tobimaru.cards;

public enum Rank {
    ACE (1),
    TWO (2),
    THREE (3),
    FOUR (4),
    FIVE (5),
    SIX (6),
    SEVEN (7),
    EIGHT (8),
    NINE (9),
    TEN (10),
    JACK (11),
    QUEEN (12),
    KING (13);
    
    public final int nmb_id;
   
    //constructor of enum must be defined private or package-private
    Rank(int id_numb){
        this.nmb_id = id_numb;
    }
}
    
