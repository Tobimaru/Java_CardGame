import com.github.Tobimaru.cards.BlackJack;
import java.util.Scanner;

public class PlayBlackJack {

    /**PlayBlackJack is an application to play the BlackJack game.
     * It uses the implementations from the package 'cards'.
     * @param args no arguments needed
     */
    public static void main(String[] args) throws InterruptedException {
       
        BlackJack.print_info();
         
        int nmb_decks = 0;
        boolean is_set = false;

        Scanner player_input = new Scanner(System.in);
        System.out.print(String.format("%n")); 
        System.out.print("How many decks do you want in the game? ");

        while (!is_set){ 

            try {   nmb_decks = Integer.parseInt(player_input.next());
                    is_set = true;
            } catch (NumberFormatException e) {
                    System.out.println("Argument must be an integer.");  
            }
        }

        BlackJack BJ_game = new BlackJack(nmb_decks);
        BJ_game.play_game(5000);
        System.exit(0);
       
    }
    
}
