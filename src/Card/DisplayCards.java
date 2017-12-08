package Card;
import java.util.ArrayList;
import java.util.Scanner;

public class DisplayCards{
	
	
    /**
     * Method that displays the cards in the player's hand
     * Used to get the index of hand ArrayList for the card the player wishes to play 
     * @param ArrayList of type Card containing the current topCard
     * @param ArrayList of type Card containing the hand of the player
     * @return index of the card played + 1, type int
     */
    public int displayDeck(ArrayList<Card>topCard,ArrayList<Card>player){
    	int numberOfPlayedCard;
        System.out.println("The card on the table is: " + ((Card) topCard.get(0)) + "\n");
        System.out.println("These are your cards:");
 		for (int i = 0; i < player.size(); i++){
            System.out.print(String.valueOf(i + 1) + ". " + 
            ((Card)player.get(i)) + "\n");
		}
		System.out.println("0: To draw card");
        System.out.println("What card do you want to play?");
      	Scanner keyboard = new Scanner(System.in);
        numberOfPlayedCard = keyboard.nextInt();
        return numberOfPlayedCard;
    }
    
    /**
     * Method used to return the cardPlayed as a list containing just the cardPLayed
     * Uses the numberOfPlayedCard returned by displayDeck()
     * @param numberOfPlayedCard, int
     * @param playerHand, type ArrayList<Card>
     * @return
     */
    public ArrayList<Card> getCardPlayed(int numberOfPlayedCard, ArrayList<Card> playerHand){
    	Card cardPlayedAsObject = playerHand.get(numberOfPlayedCard-1);
    	ArrayList<Card> cardPlayedAsList = new ArrayList<Card>();
    	cardPlayedAsList.add(cardPlayedAsObject);
    	return cardPlayedAsList;   
    }
    
    /**
     * Method that prints how many cards each player has
     * @param player1
     * @param player2
     * @param player3
     * @param player4
     */
    public void howManyCards(ArrayList<Card> player1, ArrayList<Card> player2,ArrayList<Card> player3,ArrayList<Card> player4){
        System.out.println("Player 1 has : " + player1.size() + " Cards");
        System.out.println("Player 2 has : " + player2.size() + " Cards");
        System.out.println("Player 3 has : " + player3.size() + " Cards");
        System.out.println("Player 4 has : " + player4.size() + " Cards");
    } 
}