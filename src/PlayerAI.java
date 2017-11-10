import java.util.ArrayList;


public class PlayerAI {
	private ArrayList <Card> hand = new ArrayList <Card>();
	private Logic logic = new Logic();
	
	public void removeCard(Card playedCard) {
		this.hand.remove(hand.indexOf(playedCard));
	}
	
	public Card cardAI(Card topCard) {
		ArrayList <Card> validChoices = new ArrayList <Card>();
		for (Card aCard : hand) {
			if (logic.isValid(aCard, topCard)) {
				validChoices.add(aCard);
				for (Card aCard2 : validChoices) {
					if (aCard2.getNumber().equals("Skip") || aCard2.getNumber().equals("Reverse") || aCard2.getNumber().equals("Draw 2") || aCard2.getNumber().equals("") || aCard2.getNumber().equals("Wild Draw 4")) {
						return aCard2;
					}
				}
			}
		}
		return validChoices.get(0);
	}
}
