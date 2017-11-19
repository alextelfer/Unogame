import java.util.ArrayList;
import java.util.Scanner;

public class DisplayCards{
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
    
    public ArrayList<Card> getCardPlayed(int numberOfPlayedCard, ArrayList<Card> playerHand){
    	Card cardPlayedAsObject = playerHand.get(numberOfPlayedCard-1);
    	ArrayList<Card> cardPlayedAsList = new ArrayList<Card>();
    	cardPlayedAsList.add(cardPlayedAsObject);

    	return cardPlayedAsList;   
    }
    
    public void howManyCards(ArrayList<Card> player1, ArrayList<Card> player2,ArrayList<Card> player3,ArrayList<Card> player4){
        int player1Cards = player1.size();
        int player2Cards = player2.size();
        int player3Cards = player3.size();
        int player4Cards = player4.size();
        System.out.println("Player 1 has : " + player1Cards + " Cards");
        System.out.println("Player 2 has : " + player2Cards + " Cards");
        System.out.println("Player 3 has : " + player3Cards + " Cards");
        System.out.println("Player 4 has : " + player4Cards + " Cards");
    } 
}
