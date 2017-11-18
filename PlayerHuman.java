import java.util.ArrayList;


public class PlayerHuman {
	private ArrayList <Card> hand = new ArrayList <Card>();
	
	public void initialize(Deck deck) {
		deck.draw(7 ,this.hand);
	}
	
	//PRIVACY LEAK: FIX LATER, REMOVE() USES GETTER TO EDIT
	public ArrayList<Card> getHand(){
		return hand;
	}
	public void removeCard(Card playedCard) {
		this.hand.remove(hand.indexOf(playedCard));
	}
}
