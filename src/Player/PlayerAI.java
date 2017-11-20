package Player;
import java.util.ArrayList;

import Card.Card;
import Card.Deck;
import Card.SpecialCard;
import Logic.Logic;

public class PlayerAI {
	private ArrayList <Card> hand = new ArrayList <Card>();
	
	public ArrayList<Card> getHand(){
		return hand;
	}
	
	/**
	 * Used to draw cards to the player's hand at the start of the game
	 * @param deck of type Deck
	 */
	public void initialize(Deck deck) {
		deck.draw(7,this.hand);
	}
	
	public void setHand(ArrayList<Card> aHand) {
		this.hand = aHand;
	}
	
	/**
	 * Used to remove a Card that was played from the player's hand
	 * @param playedCard of type Card
	 */
	public void removeCard(Card playedCard) {
		this.hand.remove(hand.indexOf(playedCard));
	}
	
	/**
	 * AI method: determines what card the AI will play based on the conditions of it's hand.
	 * Current AI priority: From left to right: SpecialCards > NumberCards
	 * AI Functionality: WildCard: Changes the color to the most common color in it's own hand
	 * @param takes an instance of the Deck, Logic, and a Card topCard
	 * @return returns the Card that the AI plays
	 */
	public Card cardAI(Deck deck,Logic logic, Card topCard) {
		ArrayList <Card> validChoices = new ArrayList <Card>();
		ArrayList <Card> specialChoices = new ArrayList <Card>();
		Card cardPlayed;
		//Adds valid cards in hand to validChoices list
		while (validChoices.size() == 0) {
			for (int index1 = 0;index1<hand.size();index1++) {
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
			cardPlayed = new SpecialCard(handEvaluator(),-1);
		}
		return cardPlayed;
	}
	
	
	/**
	 * Method that iterates through the player's hand and determines what color is the most common.
	 * Used to determine what color to set WildCards to when the AI decides to play one
	 * @return String color
	 */
	public int handEvaluator() {
		int numOfReds = 0;
		int numOfBlues = 0;
		int numOfGreens = 0;
		int numOfYellows = 0;
		int color = 0;
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
		}
		int mostColor = numOfReds;
		if (numOfBlues > mostColor) {
			mostColor = numOfBlues;
			color = 1;
		}
		if (numOfGreens > mostColor) {
			mostColor = numOfGreens;
			color = 3;
		}
		if (numOfYellows > mostColor) {
			mostColor = numOfYellows;
			color = 2;
		}
		return color;
	}
}