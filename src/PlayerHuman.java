import java.util.ArrayList;


public class PlayerHuman {
	private ArrayList <Card> hand = new ArrayList <Card>();
	
	public void removeCard(Card playedCard) {
		this.hand.remove(hand.indexOf(playedCard));
	}
}
