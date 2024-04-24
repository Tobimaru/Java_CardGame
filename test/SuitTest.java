import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import cards.Suit;

public class SuitTest {
    @Test
    public void testSuitSort(){
        var sortedSuits = Suit.sortvalues();

        Suit[] expectedSortedSuits = {Suit.SPADES, Suit.HEARTS, 
                                        Suit.DIAMONDS, Suit.CLUBS};

        assertArrayEquals(expectedSortedSuits, sortedSuits);
    }
}