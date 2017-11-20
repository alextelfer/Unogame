package Player;
import java.util.ArrayList;

import Card.Card;
import Card.Deck;


public class PlayerHuman {
	private ArrayList <Card> hand = new ArrayList <Card>();
	
	/**
	 * Default constructor for PlayerHuman when instantiating in other class
	 */
	public PlayerHuman() {
	}
	
	/**
	 * Copy Constructor for PlayerHuman to stop a privacy leak
	 * @param toCopy of type PlayerHuman for stopping privacy leak
	 */
	public PlayerHuman(PlayerHuman toCopy) {
		this.hand=toCopy.hand;
	}
	/**
	 * Used to draw cards to the player's hand at the start of the game
	 * @param deck of type Deck
	 */
	public void initialize(Deck deck) {
		deck.draw(7 ,this.hand);
	}
	
	//PRIVACY LEAK: FIX LATER, REMOVE() USES THIS GETTER TO EDIT
	public ArrayList<Card> getHand(){
		return hand;
	}
	
	/**
	 * Used to remove a Card that was played from the player's hand
	 * @param playedCard of type Card
	 */
	public void removeCard(Card playedCard) {
		this.hand.remove(hand.indexOf(playedCard));
	}
}