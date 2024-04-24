import java.util.List;
import java.util.ArrayList;
import cards.*;

public class cards_test {

    /**This is a script to test the implementations of card and deck objects
     * in the package 'cards'.
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        String end_of_line = String.format("%n");
        
        //test Rank and Suit
        System.out.println("All Ranks:");
        for (Rank rk : Rank.sortvalues()){
           System.out.print(rk.name() + " ");
        }
        System.out.print(end_of_line);
        
        System.out.println("All Suits:");
        for (Suit st : Suit.sortvalues()){
           System.out.print(st.name() + " ");
        }
        System.out.print(end_of_line);
           
        //test PlayCard
        PlayCard a_card = new PlayCard(Rank.ACE,Suit.HEARTS);
        System.out.print("This card is ");
        System.out.println(a_card.print());
        if (a_card.get_deck() == null){System.out.println("No deck");}
        
        //test Deck
        Deck a_deck = new Deck(0);
        int deck_id = a_deck.get_id();
        System.out.print(String.format("Deck id: %d%n",deck_id));
        deck_id = a_deck.get_cards().get(1).get_deck().get_id();
        System.out.print(String.format("Deck id of this card is: %d%n",deck_id));
        
        a_deck.myId = 2;
        deck_id = a_deck.get_id();
        System.out.print(String.format("Deck id: %d%n",deck_id));
        deck_id = a_deck.get_cards().get(1).get_deck().get_id();
        System.out.print(String.format("Deck id of this card is: %d%n",deck_id));
        
        PlayCard deck_card = a_deck.get_cards().get(4);
        System.out.print("This deck card is ");
        System.out.println(deck_card.print());
        
        //test GameDeck
        GameDeck gmd1 = new GameDeck(a_deck);
        System.out.println("Deal cards from game deck 1");
        for (int i=0;i<10;i++){
            System.out.println(gmd1.deal_card().print());
        }    
        System.out.println("Number of remaining cards");
        System.out.println(gmd1.myCards.size());
        System.out.print("Last card of deck is:");
        System.out.println(a_deck.get_cards().
                           get(a_deck.get_cards().size()-1).print());
        
        List<Deck> test_decks = new ArrayList<>();
        test_decks.add(a_deck);
        for(int i=0;i<2;i++){test_decks.add(new Deck(i+4));}
        
        GameDeck gmd2 = new GameDeck(test_decks);
        
        for (int i=0;i<3;i++){
            gmd2.shuffle();
            System.out.println("Shuffle game deck 2");
            System.out.println("The 23rd card is:");
            System.out.println(gmd2.myCards.get(23).print());
        }
     
        System.out.println(String.format("The number of cards is: %d",
                                         gmd2.myCards.size()));
        
        for (int i=0;i<3;i++){
            System.out.println("Pick random card from game deck 2");
            System.out.println(gmd2.pick_random_card().print());
            System.out.println(String.format("The number of cards is: %d",
                                         gmd2.myCards.size()));
        }  
        
    }
       
}
