package com.github.Tobimaru.cards;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import com.github.Tobimaru.cards.Suit;

public class SuitTest {
    @Test
    public void testSuitSort(){
        var suits = Suit.values();

        Suit[] expectedSuits = {Suit.SPADES, Suit.HEARTS, 
                                        Suit.DIAMONDS, Suit.CLUBS};

        assertArrayEquals(expectedSuits, suits);
    }
}