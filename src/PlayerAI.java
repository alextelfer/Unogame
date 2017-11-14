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
	
	public Card cardAI(Logic logic, Card topCard) {
		ArrayList <Card> validChoices = new ArrayList <Card>();
		for (int index1 = 0;index1<hand.size();index1++) {
			Card aCard = this.hand.get(index1);
			System.out.println(hand);
			System.out.println(aCard.getColor());
			System.out.println(aCard.getNumber());
			if (logic.isValid(topCard,hand.get(0))) {
				validChoices.add(hand.get(index1));
				if (validChoices.size() >= 1) {
					for (int index2 = 0;index2<hand.size();index2++) {
						if (hand.get(index2).getNumber().equals("Skip") || hand.get(index2).getNumber().equals("Reverse") || hand.get(index2).getNumber().equals("Draw 2") || hand.get(index2).getNumber().equals("") || hand.get(index2).getNumber().equals("Wild Draw 4")) {
							return hand.get(index2);
						}
					}
				}
			}
		}
		System.out.println(validChoices.size());
		return validChoices.get(0);
	}
}
