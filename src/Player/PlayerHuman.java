package Player;
import java.util.ArrayList;

import Card.Card;
import Card.Deck;
import Card.SpecialFunction;
import Logic.Logic;
import Runner.Controller;


/**
 * Player class that handles human input. Handles updating of game variables once a correct card is played
 * Each player class has an instance variable of a hand of cards
 */
public class PlayerHuman {
	private ArrayList <Card> hand = new ArrayList <Card>();
	
	private static final int DEFAULT_HUMAN_HAND_SIZE = 7;
	
	/*
	 * INTENTIONAL PRIVACY LEAKS: All references relating to hand ArrayLists and card objects within hand ArrayLists must be shared. Various classes edit/create
	 * artificial card objects and remove/add new card objects,
	 */
	
	/**
	 * Default constructor for PlayerHuman when instantiating in other class
	 */
	public PlayerHuman() {
	}
	
	/**
	 * Copy Constructor for PlayerHuman to stop a privacy leak
	 * @param toCopy of type PlayerHuman for stopping privacy leak
	 * INTENTIONAL PRIVACY LEAK: Hand references must be shared
	 */
	public PlayerHuman(PlayerHuman toCopy) {
		this.hand=toCopy.hand;
	}
	/**
	 * Used to draw cards to the player's hand at the start of the game
	 * @param deck of type Deck
	 */
	public void initialize(Deck deck) {
		deck.draw(DEFAULT_HUMAN_HAND_SIZE ,this.hand);
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
	
	/** Called by controller.play()
	 * Method that handles attempted input from the player and calls methods that adjust the state of the game. Calls logic.isValid() to check if the card is valid, 
	 * calls specialFunction.specialFunc() to enact functionality for special cards. Updates cardPlayed and topCard when a correct card is played. Calls 
	 * logic.gameState() to update playerTurn. Also handles win condition.
	 * @param cardIndex The index in the player hand arrayList corresponding to the card the player wants to play
	 * @param logic instance of type Logic
	 * @param specialFunction instance of type SpecialFunction
	 * @param controller instance of type Controller
	 * @param deck instance of type Deck
	 */
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
			}	
		}else if (cardIndex == 0) {
			deck.draw(1, hand);
		}
		if (hand.size() == 0) {
			controller.displayWinner("You Win!");
		}
	}
}