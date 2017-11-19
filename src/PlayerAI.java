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
	
	/*
	 * Current AI priority: From left to right: SpecialCards > NumberCards
	 * AI Functionality: WildCard: Changes the color to the most common color in it's own hand
	 */
	public Card cardAI(Deck deck,Logic logic, Card topCard) {
		ArrayList <Card> validChoices = new ArrayList <Card>();
		ArrayList <Card> specialChoices = new ArrayList <Card>();
		Card cardPlayed;
		//Adds valid cards in hand to validChoices list
		while (validChoices.size() == 0) {
			for (int index1 = 0;index1<hand.size();index1++) {
				//DO NOT REMOVE THIS PRINT STATEMENT
				System.out.println(hand);
				if (logic.isValid(topCard,hand.get(index1))) {
					validChoices.add(hand.get(index1));
				}
			}
			if (validChoices.size() == 0) {
				deck.draw(1,this.hand);
			}
		}
		//Adds special cards in validChoices to specialChoices list
		for (int index2 = 0 ; index2 < validChoices.size() ; index2++) {
			if (validChoices.get(index2).getNumber().equals("Skip") || validChoices.get(index2).getNumber().equals("Reverse") || validChoices.get(index2).getNumber().equals("Draw 2") || validChoices.get(index2).getNumber().equals("Wild") || validChoices.get(index2).getNumber().equals("Wild Draw 4")) {
				specialChoices.add(validChoices.get(index2));
			}
		}
		if (specialChoices.size() > 0) {
			cardPlayed = specialChoices.get(0);
		} else {
			cardPlayed = validChoices.get(0);
		}
		removeCard(cardPlayed);
		if (cardPlayed.getNumber().equals("Wild") || cardPlayed.getNumber().equals("Wild Draw 4")){
			cardPlayed = new SpecialCard(handEvaluator(hand),-1);
		}
		return cardPlayed;
	}
	
	public String handEvaluator(ArrayList<Card> hand) {
		int numOfReds = 0;
		int numOfBlues = 0;
		int numOfGreens = 0;
		int numOfYellows = 0;
		String color = "Red";
		for (Card aCard : hand) {
			if (aCard.getColor().equals("Red ")){
				numOfReds += 1;
			}
			else if (aCard.getColor().equals("Blue ")){
				numOfBlues += 1;
			}
			else if (aCard.getColor().equals("Green ")){
				numOfGreens += 1;
			}
			else if (aCard.getColor().equals("Yellow ")){
				numOfYellows += 1;
			}
		System.out.println("Reds:" + numOfReds + "Blues:" + numOfBlues + "Greens:" + numOfGreens + "Yellows:" + numOfYellows);
		}
		int mostColor = numOfReds;
		if (numOfBlues > mostColor) {
			mostColor = numOfBlues;
			color = "Blue ";
		}
		if (numOfGreens > mostColor) {
			mostColor = numOfGreens;
			color = "Green ";
		}
		if (numOfYellows > mostColor) {
			mostColor = numOfYellows;
			color = "Yellow ";
		}
		return color;
	}
}