import java.util.ArrayList;


public class PlayerAI {
	private ArrayList <Card> hand = new ArrayList <Card>();
	
	public ArrayList<Card> getHand(){
		return hand;
	}
	
	public void initialize(Deck deck) {
		deck.draw(7,this.hand);
	}
	
	public void removeCard(Card playedCard) {
		this.hand.remove(hand.indexOf(playedCard));
	}
	
	public Card cardAI(Deck deck,Logic logic, Card topCard) {
		ArrayList <Card> validChoices = new ArrayList <Card>();
		Card cardPlayed;
		while (validChoices.size() == 0) {
			for (int index1 = 0;index1<hand.size();index1++) {
				//DO NOT REMOVE THIS PRINT STATEMENT
				System.out.println(hand);
				if (logic.isValid(topCard,hand.get(index1))) {
					validChoices.add(hand.get(index1));
					/** Special card AI priority
					if (validChoices.size() >= 1) {
						for (int index2 = 0;index2<hand.size();index2++) {
							if (hand.get(index2).getNumber().equals("Skip") || hand.get(index2).getNumber().equals("Reverse") || hand.get(index2).getNumber().equals("Draw 2") || hand.get(index2).getNumber().equals("") || hand.get(index2).getNumber().equals("Wild Draw 4")) {
								System.out.println("here2");
								cardPlayed = hand.get(index2);
							}
						}
					}*/
				}
			}
			if (validChoices.size() == 0) {
				deck.draw(1,this.hand);
			}
		}
		cardPlayed = validChoices.get(0);
		return cardPlayed;
	}
}
