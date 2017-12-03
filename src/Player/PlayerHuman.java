package Player;
import java.util.ArrayList;

import Card.Card;
import Card.Deck;
import Card.SpecialFunction;
import Logic.Logic;
import Runner.Controller;


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
	
	public void cardAction(int cardIndex, Logic logic, SpecialFunction specialFunction, Controller controller, Deck deck ) {
		boolean valid;
		if (cardIndex > 0 ) {
			controller.setCardPlayed(hand.get(cardIndex-1));
			valid = logic.isValid(controller.getTopCard().get(0),controller.getCardPlayed().get(0));
			if (valid) {							
				specialFunction.SpecialFunc(controller.getCardPlayed().get(0), deck, logic, controller);
				System.out.println(controller.getCardPlayed().get(0));
				controller.setTopCard(controller.getCardPlayed().get(0));	
				hand.remove(cardIndex-1);
				controller.clearCardPlayed();
				logic.gameState();
				logic.numOfCards(controller.getPlayer1(), controller.getPlayer2(), controller.getPlayer3(), controller.getPlayer4());
				System.out.println("Turn" + logic.getPlayerTurn());
			}	
		}else if (cardIndex == 0) {
			deck.draw(1, hand);
		}
		if (hand.size() == 0) {
			//win
		}
	}
}