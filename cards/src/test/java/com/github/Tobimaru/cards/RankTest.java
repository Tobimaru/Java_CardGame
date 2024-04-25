package com.github.Tobimaru.cards;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import com.github.Tobimaru.cards.Rank;

public class RankTest {
    @Test
    public void testRankSort(){
        var ranks = Rank.values();

        Rank[] expectedRanks = 
            {Rank.ACE, Rank.TWO, Rank.THREE, Rank.FOUR, Rank.FIVE, Rank.SIX,
                Rank.SEVEN, Rank.EIGHT, Rank.NINE, Rank.TEN, 
                Rank.JACK, Rank.QUEEN, Rank.KING};

        assertArrayEquals(expectedRanks, ranks);
    }
}